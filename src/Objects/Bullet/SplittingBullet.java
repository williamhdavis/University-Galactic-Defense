/**
 * Created by William Davis on 12/03/2016.
 */
package Objects.Bullet;

import Components.Texture;
import Data.Config;
import Data.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SplittingBullet extends GenericBullet
{
    /**
     * The SplittingBullet constructor is used to create a new splitting bullet.
     * @param position - The spawn location of the bullet.
     * @param velocity - The initial velocity of the bullet.
     * @param playerBullet - If the bullet was fired by the player.
     */
    public SplittingBullet(Vector2D position, Vector2D velocity, boolean playerBullet)
    {
        super(position, velocity, 100, Texture.bulletTextures[1], playerBullet);
    }

    /**
     * The update method is used to update the bullet.
     */
    @Override
    public void update()
    {
        if(this.lives < 70 && this.lives > 0)
        {
            this.subBullets.add(new StandardBullet(new Vector2D(this.position), new Vector2D(this.velocity), this.player, this.lives, this.lastPos));
            this.subBullets.add(new StandardBullet(new Vector2D(this.position), new Vector2D(this.velocity).rotate(Math.toRadians(2)), this.player, this.lives, this.lastPos));
            this.subBullets.add(new StandardBullet(new Vector2D(this.position), new Vector2D(this.velocity).rotate(Math.toRadians(-2)), this.player, this.lives, this.lastPos));
            this.lives = 1;
        }
        super.update();
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
