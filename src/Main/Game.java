/**
 * Created by William Davis on 10/02/2016.
 */
package Main;

import Controls.Keyboard;
import Controls.State;
import Data.Config;
import Data.Vector2D;
import Objects.GameObject;
import Objects.Ship.PlayerShip;
import Objects.Static.Cover;
import Objects.Static.Menu.EnterScoreMenu;
import Objects.Static.Menu.HighMenu;
import Objects.Static.Menu.MainMenu;
import Objects.Static.Overlay;

import java.util.LinkedList;
import java.util.List;

public class Game
{
    /**
     * The running instance variable is used to set if the game is running.
     */
    private boolean running;
    /**
     * The level instance variable is used to store the current game level.
     */
    private Level level;
    /**
     * The levelBackup instance variable is used to store a backup of level for player deaths.
     */
    private Level levelBackup;
    /**
     * The cover instance variable is used to store the loading screen.
     */
    private Cover cover;
    /**
     * The overlay instance variable is used to store the game interface.
     */
    private Overlay overlay;
    /**
     * The main instance variable is used to store the main menu.
     */
    private MainMenu main;
    /**
     * The keyboard instance variable is used to store the keyboard controller.
     */
    private Keyboard keyboard;
    /**
     * The ship instance variable is used to store the player ship.
     */
    private PlayerShip ship;
    /**
     * The hm instance variable is used to store the high score screen.
     */
    private HighMenu hm;
    /**
     * The esm instance variable is used to store the enter new high score screen.
     */
    private EnterScoreMenu esm;
    /**
     * The levelLoadWaiting instance variable is used to store if a new level is waiting for loading.
     */
    private boolean levelLoadWaiting;
    /**
     * The levelReloadWaiting instance variable is used to store if level needs restoring from the backup.
     */
    private boolean levelReloadWaiting;
    /**
     * The levelLaunchWaiting instance variable is used to store is a new level is waiting to start being played.
     */
    private boolean levelLaunchWaiting;
    /**
     * The nextLevelChange instance variable is used to set the level change.
     */
    private int nextLevelChange;

    /**
     * The Game constructor is used to create a new game.
     */
    public Game()
    {
        this.running = false;
        this.keyboard = new Keyboard();
        this.ship = new PlayerShip(this.keyboard);
        this.level = new Level(this.ship);
        this.levelBackup = new Level(this.level);
        this.cover = new Cover();
        this.overlay = new Overlay();
        this.main = new MainMenu();
        this.hm = new HighMenu();
        this.esm = new EnterScoreMenu(this.level);
        this.levelLoadWaiting = false;
        this.levelReloadWaiting = false;
        this.levelLaunchWaiting = false;
        this.nextLevelChange = 0;
    }

    /**
     * The getKeyboard instance method is used to get the keyboard controller.
     * @return - The keyboard controller.
     */
    public Keyboard getKeyboard()
    {
        return this.keyboard;
    }

    /**
     * The start instance method is used to start the games run loop.
     * @param view - The display manager.
     */
    public void start(View view)
    {
        this.running = true;
        int skips = 0;
        while(this.running)
        {
            long now = System.currentTimeMillis();
            if(now >= Config.UPDATE_TIME)
            {
                Config.UPDATE_NOW = now;
                Config.UPDATE_TIME = now + (int)(Config.UPDATE_PER_SECOND * 1000);
                this.update();
                now = System.currentTimeMillis();
                if(skips > Config.UPDATE_MAX_SKIP || now < Config.UPDATE_TIME)
                {
                    view.repaint();
                    skips = 0;
                }
                else
                {
                    ++skips;
                }
            }
            else
            {
                long sleep = Config.UPDATE_TIME - now;
                if(sleep > 0)
                {
                    try
                    {
                        Thread.sleep(sleep);
                    }
                    catch(InterruptedException ex)
                    {}
                }
            }
        }
    }

    /**
     * The stop instance method is used to stop the games run loop, allowing the game to close.
     */
    public void stop()
    {
        this.running = false;
    }

    /**
     * The getObjects instance method is used to get a list of all objects.
     * @return - The list of objects.
     */
    public List<GameObject> getObjects()
    {
        List<GameObject> objects = new LinkedList<GameObject>();
        objects.add(this.level.getLevelBackground());
        if(this.level.getLevel() == 0)
        {
            objects.add(this.main);
        }
        else if(this.level.getLevel() == -5)
        {
            objects.add(this.hm);
        }
        else if(this.level.getLevel() == -6)
        {
            objects.add(this.esm);
        }
        else
        {
            objects.addAll(this.level.getStationObjects());
            objects.addAll(this.level.getBullets());
            objects.addAll(this.level.getEnemyObjects());
            objects.add(this.ship);
            objects.add(this.overlay);
        }
        objects.add(this.cover);
        return objects;
    }

    /**
     * The update method is used to update the whole game and all objects within.
     */
    public void update()
    {
        synchronized(Game.class)
        {
            State state = this.keyboard.getState();
            if(this.levelLoadWaiting && this.cover.transitionDone())
            {
                this.level.shiftLevel(this.nextLevelChange);
                this.levelBackup = new Level(this.level);
                this.levelLoadWaiting = false;
                this.nextLevelChange = 0;
                Config.STATIC_POSITION.set(0, 0);
                this.cover.toClear();
                this.levelLaunchWaiting = true;
            }
            else if(this.levelReloadWaiting && this.cover.transitionDone())
            {
                this.ship.preReset();
                this.level = new Level(this.levelBackup);
                this.levelReloadWaiting = false;
                Config.STATIC_POSITION.set(0, 0);
                this.cover.toClear();
                this.levelLaunchWaiting = true;
            }
            else if(this.level.getLevel() <= 0 && this.level.getLevelBackground().getWidth() + Config.STATIC_POSITION.getX() < Config.WIDTH * 1.5)
            {
                this.cover.toBlack();
                this.levelLoadWaiting = true;
                this.nextLevelChange = 0;
            }
            else if(this.level.getLevel() == 0)
            {
                this.ship.fullReset();
                if(state.select)
                {
                    this.cover.toBlack();
                    this.levelLoadWaiting = true;
                    this.nextLevelChange = this.main.getSelected();
                }
                else if(state.slide != 0)
                {
                    this.main.changeSelection(state.slide);
                    state.slide = 0;
                }
            }
            else if(this.level.getLevel() == -5)
            {
                if(state.select)
                {
                    this.cover.toBlack();
                    this.levelLoadWaiting = true;
                    this.nextLevelChange = this.hm.getSelected();
                }
            }
            else if(this.level.getLevel() == -6)
            {
                if(state.select && !this.levelLoadWaiting)
                {
                    this.esm.save();
                    this.hm = new HighMenu();
                    this.cover.toBlack();
                    this.levelLoadWaiting = true;
                    this.nextLevelChange = this.esm.getSelected();
                }
                else if(state.thrust != 0)
                {
                    this.esm.changeSelection(state.thrust);
                    state.thrust = 0;
                }
                else if(state.slide != 0)
                {
                    this.esm.changeLetter(state.slide);
                    state.slide = 0;
                }
            }
            else if(this.levelLaunchWaiting && this.cover.transitionDone())
            {
                this.ship.activate();
                this.levelLaunchWaiting = false;
            }
            else if(this.ship.isHit())
            {
                this.ship.deactivate();
                if(this.ship.animationDone())
                {
                    if(this.ship.getLives() > 0)
                    {
                        this.cover.toBlack();
                        this.levelReloadWaiting = true;
                    }
                    else
                    {
                        this.esm = new EnterScoreMenu(this.level);
                        this.cover.toBlack();
                        this.levelLoadWaiting = true;
                        if(this.level.getScore() >= this.hm.getMinScore())
                        {
                            this.nextLevelChange = this.level.getLevel() * -1 - 6;
                        }
                        else
                        {
                            this.nextLevelChange = this.level.getLevel() * -1;
                        }
                    }
                }
            }
            else if(this.ship.isActivated())
            {
                Vector2D pos = this.ship.getPosition();
                int offset = 0;
                int shift = 0;
                if(this.ship.getPosition().getX() > this.level.getLevelBackground().getWidth() - Config.WIDTH / 2)
                {
                    //Ship at right.
                    offset = this.level.getLevelBackground().getWidth() - Config.WIDTH;
                    shift = offset * -1;
                }
                else if(!(this.ship.getPosition().getX() < Config.WIDTH / 2))
                {
                    // Ship not at left
                    offset = (int) (this.ship.getPosition().getX() - (Config.WIDTH / 2.0));
                    shift = offset * -1;
                }
                this.ship.setOffset(offset);
                Config.STATIC_POSITION.set(shift, 0);

                if(Config.HEIGHT + Config.SHIP_HIDE_BORDER < pos.getY() || pos.getY() < Config.SHIP_HIDE_BORDER * -1 || pos.getX() < Config.SHIP_HIDE_BORDER * -1)
                {
                    this.ship.hit();
                    if(this.ship.getLives() > 0)
                    {
                        this.ship.deactivate();
                        this.cover.toBlack();
                        this.levelReloadWaiting = true;
                    }
                    else
                    {
                        this.ship.deactivate();
                        this.esm = new EnterScoreMenu(this.level);
                        this.cover.toBlack();
                        this.levelLoadWaiting = true;
                        if(this.level.getScore() >= this.hm.getMinScore())
                        {
                            this.nextLevelChange = this.level.getLevel() * -1 - 6;
                        }
                        else
                        {
                            this.nextLevelChange = this.level.getLevel() * -1;
                        }
                    }
                }
                else if(pos.getX() > (this.level.getLevelBackground().getWidth() + Config.SHIP_HIDE_BORDER))
                {
                    this.ship.deactivate();
                    this.cover.toBlack();
                    this.levelLoadWaiting = true;
                    this.nextLevelChange = 1;
                }
            }

            this.level.update();
            this.overlay.setData(this.ship.getLives(), this.level.getScore(), this.ship.getBulletType(), this.ship.getPowerupData());

            Config.STATIC_POSITION.addScaled(Config.STATIC_VELOCITY, Config.UPDATE_PER_SECOND);
            List<GameObject> objects = this.getObjects();
            int i = 0;
            while(i < objects.size())
            {
                GameObject item = objects.get(i);
                item.update();
                int j = i;
                while(j < objects.size())
                {
                    GameObject altItem = objects.get(j);
                    if(!item.getClass().equals(altItem.getClass()))
                    {
                        if(item.canHit(altItem))
                        {
                            if(item.checkCollision(altItem))
                            {
                                this.level.addScore(item.triggerHit(altItem));
                            }
                        }
                    }
                    ++j;
                }
                ++i;
            }
        }
    }
}
