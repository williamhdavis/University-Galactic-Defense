/**
 * Created by William Davis on 20/02/2016.
 */
package Objects.Bullet;

import Components.Texture;
import Data.Config;
import Data.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class StandardBullet extends GenericBullet
{
    /**
     * The StandardBullet constructor is used to create a new standard bullet.
     * @param position - The spawn location of the bullet.
     * @param velocity - The initial velocity of the bullet.
     * @param playerBullet - If the bullet was fired by the player.
     */
    public StandardBullet(Vector2D position, Vector2D velocity, boolean playerBullet)
    {
        this(position, velocity, playerBullet, 100);
    }

    /**
     * The StandardBullet constructor is used to create a new standard bullet.
     * @param position - The spawn location of the bullet.
     * @param velocity - The initial velocity of the bullet.
     * @param timeToLive - The lives the bullet has.
     * @param lastPos - The position of the bullet that spawned this one three updates ago.
     */
    public StandardBullet(Vector2D position, Vector2D velocity, boolean playerBullet, int timeToLive, Vector2D lastPos)
    {
        this(position, velocity, playerBullet, timeToLive);
        this.nearPos = new Vector2D(lastPos);
        this.medPos = new Vector2D(lastPos);
        this.lastPos = new Vector2D(lastPos);
    }

    /**
     * The StandardBullet constructor is used to create a new standard bullet.
     * @param position - The spawn location of the bullet.
     * @param velocity - The initial velocity of the bullet.
     * @param timeToLive - The lives the bullet has.
     */
    public StandardBullet(Vector2D position, Vector2D velocity, boolean playerBullet, int timeToLive)
    {
        super(position, velocity, timeToLive, Texture.bulletTextures[0], playerBullet);
    }

    /**
     * The draw instance method is used to draw the bullet to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(this.position.getX(), this.position.getY());
        g.translate(Config.STATIC_POSITION.getX(), Config.STATIC_POSITION.getY());
        g.rotate(this.direction.angle() + Math.PI / 2);
        for(Texture t: this.textures)
        {
            t.draw(g);
        }
        g.setTransform(at);
    }
}
