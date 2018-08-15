/**
 * Created by William Davis on 11/03/2016.
 */
package Objects.Powerup;

import Components.Texture;
import Data.Config;
import Data.Vector2D;
import Objects.GameObject;
import Objects.Ship.PlayerShip;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Powerup extends GameObject
{
    /**
     * The power instance variable is used to store the powerups type.
     */
    private int power;

    /**
     * The Powerup constructor is used to create a new power up.
     * @param position - The position of the power up.
     * @param power - The type of the power up.
     */
    public Powerup(Vector2D position, int power)
    {
        super(position, new Vector2D(), new Vector2D(), 1, new Texture[]{PowerupData.all[0][power].getTextures()[1]});
        this.power = power;
    }

    /**
     * The Powerup constructor is used to copy a power up.
     * @param power - The power up to copy from.
     */
    public Powerup(Powerup power)
    {
        this(power.getPosition(), power.power);
    }

    /**
     * The triggerHit instance method is used to trigger hits based on the object type hit.
     * @param item - The object hit.
     * @return - The score gained.
     */
    @Override
    public int triggerHit(GameObject item)
    {
        if(item instanceof PlayerShip)
        {
            return item.triggerHit(this);
        }
        else
        {
            return 0;
        }
    }

    /**
     * The canHit instance method is used to check if an object can be hit by power ups.
     * @param object - The object to check.
     * @return - True if the object can hit the bullet. False otherwise.
     */
    public boolean canHit(GameObject object)
    {
        return object instanceof PlayerShip;
    }

    /**
     * The getPower instance method is used to get the power up type.
     * @return - The type of the power up.
     */
    public int getPower()
    {
        return this.power;
    }

    /**
     * The draw instance method is used to draw the power up to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(Config.STATIC_POSITION.getX(), Config.STATIC_POSITION.getY());
        g.translate(this.position.getX(), this.position.getY());
        for(Texture t : this.textures)
        {
            t.draw(g);
        }
        g.setTransform(at);
    }
}
