/**
 * Created by William Davis on 13/03/2016.
 */
package Objects.Bullet;

import Components.Texture;
import Data.Config;
import Data.Vector2D;
import Objects.GameObject;
import Objects.Ship.PlayerShip;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BulletPickup extends GameObject
{
    /**
     * The TOTAL_TYPES class variable is used to store the number of bullet power ups available.
     */
    public static final int TOTAL_TYPES = 2;
    /**
     * The type instance variable is used to store the power up type.
     */
    private int type;

    /**
     * The BulletPickup constructor is used to create a new bullet pickup.
     * @param position - The position of the pickup.
     * @param type - The type of pickup it is.
     */
    public BulletPickup(Vector2D position, int type)
    {
        super(position, new Vector2D(), new Vector2D(), 1, new Texture[]{Texture.powerupTextures[4]});
        this.type = type;
    }

    /**
     * The BulletPickup constructor is used to copy a bullet pickup.
     * @param bullet - The bullet pickup to copy.
     */
    public BulletPickup(BulletPickup bullet)
    {
        this(bullet.getPosition(), bullet.type);
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
     * The canHit instance method is used to check if an object can hit the bullet pickup.
     * @param object - The object to be checked.
     * @return - True if the object can hit bullet poweups. False otherwise.
     */
    public boolean canHit(GameObject object)
    {
        return object instanceof PlayerShip;
    }

    /**
     * The getType instance method is used to get the bullet powerups type.
     * @return - The powerup type.
     */
    public int getType()
    {
        return this.type;
    }

    /**
     * The draw instance method is used to draw the bullet pickup to a graphics object.
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
