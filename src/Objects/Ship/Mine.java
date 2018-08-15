/**
 * Created by William Davis on 20/02/2016.
 */
package Objects.Ship;

import Components.Texture;
import Data.Boundary;
import Data.Config;
import Data.Vector2D;
import Objects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

public class Mine extends GenericShip
{
    /**
     * The sensor class variable is used to store the boundarys for the ranged collision of the mine.
     */
    private static Boundary[] sensor = {
        Boundary.load("ships", "mineSensor", true, new Vector2D(-49, -49)),
        Boundary.load("ships", "mineSensor", false, new Vector2D(-49, -49))
    };

    /**
     * The Mine constructor is used to create a mine.
     * @param position - The position of the mine.
     */
    public Mine(Vector2D position)
    {
        super(position, new Vector2D(), new Vector2D(), 1, null, -1, new int[]{1, 0}, new Texture[]{Texture.shipTextures[1][0]}, Texture.shipBulletSpawnOffsets[1]);
    }

    /**
     * The Mine constructor is used to copy a mine.
     * @param mine - The mine to copy.
     */
    public Mine(Mine mine)
    {
        this(mine.getPosition());
    }

    /**
     * The triggerHit instance method is used to trigger hits based on the object type hit.
     * @param item - The object hit.
     * @return - The score gained.
     */
    @Override
    public int triggerHit(GameObject item)
    {
        if(item instanceof PlayerShip && this.explodeState <= 0)
        {
            this.hit();
            return 0;
        }
        else
        {
            return super.triggerHit(item);
        }
    }

    /**
     * The getBounds instance method is used to get the mines boundary.
     * @param object - The object being checked to the mine.
     * @param simple - If the check is a simple collision.
     * @return - The boundary data.
     */
    @Override
    public List<Boundary> getBounds(GameObject object, boolean simple)
    {
        if(object instanceof PlayerShip && this.explodeState <= 0)
        {
            int i = 1;
            if(simple)
            {
                i = 0;
            }
            Boundary hold = new Boundary(sensor[i]);
            hold.setRotation(this.direction.angle() + Math.PI / 2);
            hold.addOffset(this.position);
            List<Boundary> temp = new LinkedList<Boundary>();
            temp.add(hold);
            return temp;
        }
        else
        {
            return super.getBounds(object, simple);
        }
    }

    /**
     * The draw instance method is used to draw the ship to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(Config.STATIC_POSITION.getX(), Config.STATIC_POSITION.getY());
        g.translate(this.position.getX(), this.position.getY());
        double rot = this.direction.angle() + Math.PI / 2;
        g.rotate(rot);
        if(this.explodeState < 2)
        {
            for(Texture t : this.textures)
            {
                t.draw(g);
            }
        }
        if(this.lives == 0 && !this.isDead())
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
