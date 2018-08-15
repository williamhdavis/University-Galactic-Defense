/**
 * Created by William Davis on 11/02/2016.
 */
package Objects.Ship;
import Components.Texture;
import Controls.Controller;
import Controls.State;
import Data.Config;
import Data.Vector2D;
import Objects.Bullet.BulletPickup;
import Objects.GameObject;
import Objects.Powerup.Powerup;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class PlayerShip extends GenericShip
{
    /**
     * The activated instance variable is used to store if the player ship is enabled.
     */
    private boolean activated;
    /**
     * The positionOffset instance variable is used to store the ships x position on the screen.
     */
    private int positionOffset;
    /**
     * The livesGained instance variable is used to store the number of lives gained in the current level.
     */
    private int livesGained;

    /**
     * The PlayerShip constructor is used to create the player ship.
     * @param controls - The controller to use.
     */
    public PlayerShip(Controller controls)
    {
        super(new Vector2D(Config.SHIP_HIDE_BORDER * -1, Config.HEIGHT / 2), new Vector2D(0, 0), new Vector2D(1, 0), Config.SHIP_START_LIVES, controls, 0, new int[]{0, 0}, new Texture[]{Texture.shipTextures[0][0]}, Texture.shipBulletSpawnOffsets[0]);
        this.positionOffset = 0;
        this.activated = false;
        this.livesGained = 0;
    }

    /**
     * The setOffset instance method is used to set the ships screen offset.
     * @param offset - The offset to set.
     */
    public void setOffset(int offset)
    {
        this.positionOffset = offset;
    }

    /**
     * The activate instance method is used to activate the ship.
     */
    public void activate()
    {
        this.reset();
        this.activated = true;
    }

    /**
     * The isActivated instance method is used to check if the ship is activated.
     * @return - True if the ship is activated. False otherwise.
     */
    public boolean isActivated()
    {
        return this.activated;
    }

    /**
     * The canHit instance method is used to check if an object can be hit by the player ship.
     * @param object - The object to check.
     * @return - True if the object can hit the bullet. False otherwise.
     */
    @Override
    public boolean canHit(GameObject object)
    {
        if(super.canHit(object))
        {
            return true;
        }
        else
        {
            return object instanceof Powerup;
        }
    }

    /**
     * The triggerHit instance method is used to trigger hits based on the object type hit.
     * @param item - The object hit.
     * @return - The score gained.
     */
    @Override
    public int triggerHit(GameObject item)
    {
        if(item instanceof Powerup)
        {
            int power = ((Powerup) item).getPower();
            if(power == 0)
            {
                if(this.lives <= 9)
                {
                    ++this.lives;
                    ++this.livesGained;
                }
                else
                {
                    return item.hit() + 500;
                }
            }
            else if(1 <= power && power <= 2)
            {
                return item.hit() + 500;
            }
            else
            {
                if(this.powerup[1] == power)
                {
                    return item.hit() + 50;
                }
                else
                {
                    this.powerup[1] = power;
                }
            }
            return item.hit();
        }
        else if(item instanceof BulletPickup)
        {
            int type = ((BulletPickup)item).getType();
            if(this.bulletType == type)
            {
                return item.hit() + 50;
            }
            else
            {
                this.bulletType = type;
            }
            return item.hit();
        }
        else
        {
            return super.triggerHit(item);
        }
    }

    /**
     * The hit instance method is used to hit the ship.
     * @return - The score gained.
     */
    @Override
    public int hit()
    {
        if(!this.iHit)
        {
            this.iHit = true;
            --this.lives;
        }
        return 0;
    }

    /**
     * The animationDone instance method is used to check if the explosion animation has finished.
     * @return - True if the explosion is finished. False otherwise.
     */
    public boolean animationDone()
    {
        return this.explodeState >= Texture.largeExplosion.length;
    }

    /**
     * The getLives instance method is used to get the number of lives the player has.
     * @return - The number of lives.
     */
    public int getLives()
    {
        return this.lives;
    }

    /**
     * The deactivate instance method is used to deactivate the ship.
     */
    public void deactivate()
    {
        this.activated = false;
    }

    /**
     * The preReset instance method is used to reset the ship after death.
     */
    public void preReset()
    {
        if(this.iHit)
        {
            this.bulletType = 0;
            this.powerup[1] = 0;
            this.lives -= this.livesGained;
        }
        this.livesGained = 0;
        this.iHit = false;
        this.explodeState = 0;
        this.position.set(Config.SHIP_HIDE_BORDER * -1, Config.HEIGHT / 2);
        this.bullets.clear();
    }

    /**
     * The reset method is used to reset the ship before starting a game.
     */
    public void reset()
    {
        this.preReset();
        this.velocity.set(Config.SHIP_START_X_VELOCITY, 0);
        this.direction.set(1, 0);
        this.rotation = 0.0;
    }

    /**
     * The fullReset method is used to reset all the ships data back to default.
     */
    public void fullReset()
    {
        this.lives = Config.SHIP_START_LIVES;
        this.preReset();
    }

    /**
     * The update instance method is used to update the ship.
     */
    public void update()
    {
        if(this.activated && this.lives > 0)
        {
            super.update();
        }
    }

    /**
     * The draw instance method is used to draw the ship to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(this.position.getX() - this.positionOffset, this.position.getY());
        double rot = this.direction.angle() + Math.PI / 2;
        g.rotate(rot);
        if(this.explodeState < 2)
        {
            State conts = this.controls.getState();
            if(conts.thrust == 1)
            {
                Texture.shipTextures[0][1].draw(g);
            }
            else if(conts.thrust == -1)
            {
                Texture.shipTextures[0][2].draw(g);
            }
            if(conts.slide == 1 || conts.turn == -1)
            {
                Texture.shipTextures[0][3].draw(g);
            }
            if(conts.slide == 1 || conts.turn == 1)
            {
                Texture.shipTextures[0][4].draw(g);
            }
            if(conts.slide == -1 || conts.turn == 1)
            {
                Texture.shipTextures[0][5].draw(g);
            }
            if(conts.slide == -1 || conts.turn == -1)
            {
                Texture.shipTextures[0][6].draw(g);
            }

            for(Texture t : this.textures)
            {
                t.draw(g);
            }
        }
        if(this.iHit && this.explodeState < Texture.largeExplosion.length)
        {
            if(this.animationUpdate < Config.UPDATE_NOW)
            {
                this.animationUpdate = Config.UPDATE_NOW + Config.UPDATE_ANIMATION_DELAY;
                ++this.explodeState;
            }
            Texture.largeExplosion[this.explodeState - 1].draw(g);
        }
        g.setTransform(at);
    }
}
