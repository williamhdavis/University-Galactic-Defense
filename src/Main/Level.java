/**
 * Created by William Davis on 10/02/2016.
 */
package Main;

import Audio.BackgroundMusic;
import Controls.AI.BasicAI;
import Controls.AI.TurretAI;
import Data.Config;
import Data.Vector2D;
import Objects.Asteroid.AsteroidBelt;
import Objects.Bullet.BulletPickup;
import Objects.Bullet.GenericBullet;
import Objects.GameObject;
import Objects.Powerup.Powerup;
import Objects.Powerup.PowerupData;
import Objects.Ship.*;
import Objects.Static.Background;
import Objects.Station.StationSectionData;
import Objects.Station.StationSection;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Level
{
    /**
     * The playerShip instance variable is used to store the player ship.
     */
    private PlayerShip playerShip;
    /**
     * The level instance variable is used to store the current level number.
     */
    private int level;
    /**
     * The difficulty instance variable is used to store the current difficulty level.
     */
    private int difficulty;
    /**
     * The score instance variable is used to store the current score.
     */
    private int score;
    /**
     * The background instance variable is used to store the current background data.
     */
    private Background background;
    /**
     * The stationParts instance variable is used to store the station parts on the current level.
     */
    private List<StationSection> stationParts;
    /**
     * The belts instance variable is used to store the asteroid belts on the current level.
     */
    private List<AsteroidBelt> belts;
    /**
     * The ships instance variable is used to store the ships on the current level.
     */
    private List<GenericShip> ships;
    /**
     * The powers instance variable is used to store the power ups on the current level.
     */
    private List<Powerup> powers;
    /**
     * The bPicks instance variable is used to store the bullet power ups on the current level.
     */
    private List<BulletPickup> bPicks;
    /**
     * The bullets instance variable is used to store the bullets on the current level.
     */
    private List<GenericBullet> bullets;
    /**
     * The lastBack instance variable is used to store the end colour used on the last background.
     */
    private Color lastBack;
    /**
     * The music instance variable is used to store the background music manger.
     */
    private BackgroundMusic music;

    /**
     * The Level constructor is used to create a new level manager.
     * @param playerShip - The player ship.
     */
    public Level(PlayerShip playerShip)
    {
        this.playerShip = playerShip;
        this.level = 0;
        this.difficulty = 1;
        this.score = 0;
        this.background = new Background(10, Color.black);
        this.stationParts = new LinkedList<StationSection>();
        this.belts = new LinkedList<AsteroidBelt>();
        this.ships = new LinkedList<GenericShip>();
        this.powers = new LinkedList<Powerup>();
        this.bPicks = new LinkedList<BulletPickup>();
        this.bullets = new LinkedList<GenericBullet>();
        Config.STATIC_POSITION.set(0, 0);
        Config.STATIC_VELOCITY.set(-100, 0);
        this.lastBack = Color.black;
        this.music = new BackgroundMusic();
        this.music.start();
    }

    /**
     * The Level constructor is used to copy a level managers data.
     * @param levelData - The level manager to copy from.
     */
    public Level(Level levelData)
    {
        this.playerShip = levelData.playerShip;
        this.level = levelData.getLevel();
        this.difficulty = levelData.difficulty;
        this.score = levelData.getScore();
        this.background = levelData.getLevelBackground();
        this.stationParts = new LinkedList<StationSection>();
        for(StationSection s: levelData.getStationObjects())
        {
            this.stationParts.add(new StationSection(s));
        }
        this.belts = new LinkedList<AsteroidBelt>();
        for(AsteroidBelt a: levelData.belts)
        {
            this.belts.add(new AsteroidBelt(a));
        }
        this.ships = new LinkedList<GenericShip>();
        for(GenericShip t: levelData.getAllEnemyShips())
        {
            if(t instanceof Mine)
            {
                this.ships.add(new Mine((Mine)t));
            }
            else if(t instanceof Enemy1)
            {
                this.ships.add(new Enemy1((Enemy1)t));
            }
            else if(t instanceof Turret)
            {
                this.ships.add(new Turret((Turret)t));
            }
        }
        this.powers = new LinkedList<Powerup>();
        for(Powerup p: levelData.powers)
        {
            this.powers.add(new Powerup(p));
        }
        this.bPicks = new LinkedList<BulletPickup>();
        for(BulletPickup p: levelData.bPicks)
        {
            this.bPicks.add(new BulletPickup(p));
        }
        this.bullets = new LinkedList<GenericBullet>();
        this.lastBack = levelData.getColor();
        this.music = levelData.music;
    }

    /**
     * The getLevel instance method is used to get the current level number.
     * @return - The current level number.
     */
    public int getLevel()
    {
        return this.level;
    }

    /**
     * The getScore instance method is used to get the current player score.
     * @return - The current score.
     */
    public int getScore()
    {
        return this.score;
    }

    /**
     * The getColor instance method is used to get the last background end colour.
     * @return - The colour.
     */
    public Color getColor()
    {
        return this.lastBack;
    }

    /**
     * The addScore instance method is used to add score to the players score.
     * @param points - The score to add.
     */
    public void addScore(int points)
    {
        this.score += points * ((this.difficulty - 1) * 0.25) + points;
    }

    /**
     * The genLevel instance method is used to generate a new level based using randomness for many aspects.
     */
    private void genLevel()
    {
        this.stationParts.clear();
        this.belts.clear();
        this.ships.clear();
        this.powers.clear();
        this.bPicks.clear();
        this.bullets.clear();
        if(this.level <= 0)
        {
            this.lastBack = Color.black;
            Config.STATIC_VELOCITY.set(-100, 0);
            this.background = new Background(10, this.lastBack);
            if(this.music.bossActive())
            {
                this.music.deactivateBoss();
            }
        }
        else
        {
            if(this.level == 1)
            {
                this.difficulty = 1;
                this.score = 0;
            }
            Config.STATIC_VELOCITY.set(0, 0);
            Random ra = new Random();

            int screens = Config.BACKGROUND_NUMBER_DEFAULT + (((this.level - 1) / Config.STATION_RATE) * 2);
            screens += ra.nextInt(Config.BACKGROUND_NUMBER_FUZZING * 2) - Config.BACKGROUND_NUMBER_FUZZING;

            if(this.level > 1)
            {
                this.addScore(100);
            }

            if((this.level + 1) % Config.STATION_RATE == 0)
            {
                int r = ra.nextInt(50);
                int g = ra.nextInt(50);
                int b = ra.nextInt(50);
                this.background = new Background(screens, this.lastBack, new Color(r, g, b));
                this.lastBack = new Color(r, g, b);
            }
            else if((this.level - 1) % Config.STATION_RATE == 0)
            {
                this.background = new Background(screens, this.lastBack, Color.black);
                this.lastBack = Color.black;
            }
            else
            {
                this.background = new Background(screens, this.lastBack);
            }

            if(this.level % Config.STATION_RATE == 0 && this.level > 0)
            {
                this.music.activateBoss();
            }
            else if(this.music.bossActive())
            {
                this.music.deactivateBoss();
            }

            if(this.level % Config.STATION_RATE == 0)
            {
                int pos = this.spawnStation(Config.SEGMENT_WIDTH * 4);
                pos += Config.SEGMENT_WIDTH * 4;
                while(pos < this.background.getWidth() - (Config.WIDTH - Config.SEGMENT_WIDTH * 2))
                {
                    pos = this.spawnStation(pos);
                    pos += Config.SEGMENT_WIDTH * 4;
                }
                ++this.difficulty;
            }
            else
            {
                int powCount = 0;
                int pos = Config.SEGMENT_WIDTH * 4;
                int length = (int)(((this.background.getWidth() - Config.WIDTH) / (double)Config.WIDTH) * Config.SEGMENT_WIDTH);
                if(length < 500)
                {
                    length = 500;
                }
                while(pos < this.background.getWidth() - (Config.WIDTH - Config.SEGMENT_WIDTH * 2))
                {
                    int select = ra.nextInt(100);
                    int emptyProb = this.level * -1 + 25;
                    if(select < emptyProb)
                    {
                        pos += length;
                    }
                    else
                    {
                        int sel = ra.nextInt(100);
                        if(sel < 30)
                        {
                            // Minefield
                            pos = this.spawnMinefield(pos, length);
                        }
                        else if(sel < 60)
                        {
                            // Asteroids
                            pos = this.spawnAsteroidBelt(pos, length);
                        }
                        else if(sel < 75 && this.level > 1 && powCount < this.level / Config.STATION_RATE + 1)
                        {
                            // Powerup
                            int pow = ra.nextInt(BulletPickup.TOTAL_TYPES + PowerupData.all[0].length);
                            if(pow < BulletPickup.TOTAL_TYPES)
                            {
                                this.bPicks.add(new BulletPickup(new Vector2D(pos, ra.nextInt(Config.HEIGHT - Config.SEGMENT_HEIGHT * 2) + Config.SEGMENT_HEIGHT), pow));
                            }
                            else
                            {
                                this.powers.add(new Powerup(new Vector2D(pos, ra.nextInt(Config.HEIGHT - Config.SEGMENT_HEIGHT * 2) + Config.SEGMENT_HEIGHT), pow - BulletPickup.TOTAL_TYPES));
                            }
                            ++powCount;
                        }
                        else if(this.level > 1)
                        {
                            // Enemy
                            pos += length;
                            int selc = ra.nextInt(2);
                            if(selc == 0)
                            {
                                this.ships.add(new Enemy1(new BasicAI(this.playerShip), new Vector2D(pos, 200), this.difficulty, 0, 1));
                                this.ships.add(new Enemy1(new BasicAI(this.playerShip), new Vector2D(pos, Config.HEIGHT / 2), this.difficulty, 0, 1));
                                this.ships.add(new Enemy1(new BasicAI(this.playerShip), new Vector2D(pos, Config.HEIGHT - 200), this.difficulty, 0, 1));
                            }
                            else
                            {
                                this.ships.add(new Enemy1(new BasicAI(this.playerShip), new Vector2D(pos, 200), this.difficulty, 0, 1));
                                this.ships.add(new Enemy1(new BasicAI(this.playerShip), new Vector2D(pos, Config.HEIGHT - 200), this.difficulty, 0, 1));
                            }
                        }
                        pos += 200;
                    }
                }
            }
        }
    }

    /**
     * The spawnStation instance method is used to generate a new station the length of the level.
     * @param xStart - The starting x position of the station.
     * @return - The end x position of the station.
     */
    private int spawnStation(int xStart)
    {
        return this.spawnStation(xStart, 0);
    }

    /**
     * The spawnStation instance method is used to generate a new station.
     * @param xStart - The starting x position of the station.
     * @param maxWidth - The maximum width the station should be.
     * @return - The end x position of the station.
     */
    private int spawnStation(int xStart, int maxWidth)
    {
        int maxLevelWidth = this.background.getWidth() - (Config.SEGMENT_WIDTH * 2);
        int width;
        if(maxWidth < maxLevelWidth && maxWidth != 0)
        {
            width = maxWidth;
        }
        else
        {
            width = maxLevelWidth;
        }
        Vector2D location = new Vector2D(xStart, 0);
        StationSection last = StationSectionData.pick(this.playerShip, new Vector2D(location), new int[]{0, 0}, this.difficulty);
        this.stationParts.add(last);
        while(last.getExit()[0] != 0 && last.getExit()[1] != 0)
        {
            location.add(last.getWidth(), 0);
            if(width - location.getX() < Config.SEGMENT_WIDTH * 4)
            {
                last = StationSectionData.pick(this.playerShip, new Vector2D(location), last.getExit(), this.difficulty, "exit");
            }
            else
            {
                last = StationSectionData.pick(this.playerShip, new Vector2D(location), last.getExit(), this.difficulty, "exit", false);
            }
            for(GameObject o: last.getObjects())
            {
                if(o instanceof GenericShip)
                {
                    this.ships.add((GenericShip)o);
                }
                else if(o instanceof Powerup)
                {
                    this.powers.add((Powerup)o);
                }
                else if(o instanceof BulletPickup)
                {
                    this.bPicks.add((BulletPickup)o);
                }
            }
            this.stationParts.add(last);
        }
        return (int)location.getX();
    }

    /**
     * The spawnMinefield instance method is used to generate a new minefield.
     * @param xStart - The starting x position of the minefield.
     * @param maxWidth - The maximum width the minefield should be.
     * @return - The end x position of the minefield.
     */
    private int spawnMinefield(int xStart, int maxWidth)
    {
        int maxLevelWidth = (int)(this.background.getWidth() - (Config.WIDTH / 2));
        int width;
        if(maxWidth < maxLevelWidth && maxWidth != 0)
        {
            width = maxWidth;
        }
        else
        {
            width = maxLevelWidth - xStart;
        }
        int change = Config.MINE_PATH_CHANGE;

        Random r = new Random();
        Vector2D location = new Vector2D(xStart, r.nextInt(Config.HEIGHT - Config.MINE_PATH_EDGE_INSET * 2) + Config.MINE_PATH_EDGE_INSET);
        Vector2D nextLocation = new Vector2D(xStart + change, r.nextInt(Config.HEIGHT - Config.MINE_PATH_EDGE_INSET * 2) + Config.MINE_PATH_EDGE_INSET);

        while(location.getX() < width + xStart)
        {
            double m = (nextLocation.getY() - location.getY())/(nextLocation.getX() - location.getX());
            double c = location.getY() - (location.getX() * m);
            double cU = c + Config.MINE_PATH_WIDTH;
            double cL = c - Config.MINE_PATH_WIDTH;

            int x = (int)location.getX();
            while(x < nextLocation.getX())
            {
                int y = 20;
                while(y < Config.HEIGHT)
                {
                    double xM = x + r.nextInt(Config.MINE_DEGRID * 2) - Config.MINE_DEGRID;
                    double yM = y + r.nextInt(Config.MINE_DEGRID * 2) - Config.MINE_DEGRID;
                    if(yM > m * xM + cU || m * xM + cL > yM)
                    {
                        this.ships.add(new Mine(new Vector2D(xM, yM)));
                    }
                    y += Config.MINE_DISTRIBUTION;
                }
                x += Config.MINE_DISTRIBUTION;
            }

            location = new Vector2D(nextLocation.add((Config.MINE_DISTRIBUTION / 4) * 3, 0));
            nextLocation = new Vector2D(location.getX() + change, r.nextInt(Config.HEIGHT - 200) + 100);
        }

        return (int)nextLocation.getX() + 1;
    }

    /**
     * The spawnAsteroidBelt instance method is used to spawn a new asteroid belt.
     * @param xStart - The starting x position of the asteroid belt.
     * @param maxWidth - The maximum width the asteroid belt should be.
     * @return - The end x position of the asteroid belt.
     */
    private int spawnAsteroidBelt(int xStart, int maxWidth)
    {
        int maxLevelWidth = (int)(this.background.getWidth() - (Config.WIDTH / 2));
        int width;
        if(maxWidth < maxLevelWidth && maxWidth != 0)
        {
            width = maxWidth;
        }
        else
        {
            width = maxLevelWidth - xStart;
        }
        this.belts.add(new AsteroidBelt(xStart, width));
        return xStart + width;
    }

    /**
     * The getLevelBackground instance method is used to get the current background data.
     * @return - The background data.
     */
    public Background getLevelBackground()
    {
        return this.background;
    }

    /**
     * The shiftLevel instance method is used to change the current level number.
     * @param change - The number to change the level by.
     */
    public void shiftLevel(int change)
    {
        this.level += change;
        this.genLevel();
    }

    /**
     * The getStationObjects instance method is used to get the list of station parts on the level.
     * @return - The list of station parts.
     */
    public List<StationSection> getStationObjects()
    {
        return this.stationParts;
    }

    /**
     * The getBullets instance method is used to get the list of bullets on the level.
     * @return - The list of bullets.
     */
    public List<GenericBullet> getBullets()
    {
        return this.bullets;
    }

    /**
     * The getAllEnemyShips instance method is used to get the list of enemy ships on the level.
     * @return - The list of enemy ships.
     */
    public List<GenericShip> getAllEnemyShips()
    {
        return this.ships;
    }

    /**
     * The getEnemyObjects method is used to get the list of enemy objects on the level.
     * @return - The list of enemy objects.
     */
    public List<GameObject> getEnemyObjects()
    {
        List<GameObject> hold = new LinkedList<GameObject>();
        for(AsteroidBelt a: this.belts)
        {
              hold.addAll(a.getAsteroids());
        }
        for(GenericShip obj: this.ships)
        {
            if(obj.getPosition().getX() + Config.STATIC_POSITION.getX() >= Config.SEGMENT_WIDTH * -3 && obj.getPosition().getX() + Config.STATIC_POSITION.getX() <= Config.WIDTH + Config.SEGMENT_WIDTH * 3)
            {
                hold.add(obj);
            }
        }
        for(Powerup obj: this.powers)
        {
            if(obj.getPosition().getX() + Config.STATIC_POSITION.getX() >= Config.SEGMENT_WIDTH * -3 && obj.getPosition().getX() + Config.STATIC_POSITION.getX() <= Config.WIDTH + Config.SEGMENT_WIDTH * 3)
            {
                hold.add(obj);
            }
        }
        for(BulletPickup obj: this.bPicks)
        {
            if(obj.getPosition().getX() + Config.STATIC_POSITION.getX() >= Config.SEGMENT_WIDTH * -3 && obj.getPosition().getX() + Config.STATIC_POSITION.getX() <= Config.WIDTH + Config.SEGMENT_WIDTH * 3)
            {
                hold.add(obj);
            }
        }
        return hold;
    }

    /**
     * The update method is used to update all the objects in the level data.
     */
    public void update()
    {
        for(AsteroidBelt a: this.belts)
        {
            a.update();
        }
        List<GenericShip> obs = new LinkedList<GenericShip>();
        for(GenericShip o: this.ships)
        {
            this.bullets.addAll(o.getBullets());
            if(!o.isDead())
            {
                obs.add(o);
            }
        }
        this.ships.clear();
        this.ships.addAll(obs);

        List<Powerup> obsP = new LinkedList<Powerup>();
        for(Powerup o: this.powers)
        {
            if(!o.isDead())
            {
                obsP.add(o);
            }
        }
        this.powers.clear();
        this.powers.addAll(obsP);

        List<BulletPickup> obsBP = new LinkedList<BulletPickup>();
        for(BulletPickup o: this.bPicks)
        {
            if(!o.isDead())
            {
                obsBP.add(o);
            }
        }
        this.bPicks.clear();
        this.bPicks.addAll(obsBP);

        this.bullets.addAll(this.playerShip.getBullets());

        List<GenericBullet> obsB = new LinkedList<GenericBullet>();
        for(GenericBullet b: this.bullets)
        {
            obsB.addAll(b.getSubBullets());
            if(!b.isDead())
            {
                obsB.add(b);
            }
        }
        this.bullets.clear();
        this.bullets.addAll(obsB);

        if(this.playerShip.getPosition().getX() > this.getLevelBackground().getWidth() - Config.WIDTH / 2)
        {
            if(this.music.bossActive())
            {
                this.music.deactivateBoss();
            }
        }
        else if(!this.music.bossActive())
        {
            if(this.level % Config.STATION_RATE == 0 && this.level != 0)
            {
                this.music.activateBoss();
            }
        }
    }
}
