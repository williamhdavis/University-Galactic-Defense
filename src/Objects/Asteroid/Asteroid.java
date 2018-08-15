/**
 * Created by William Davis on 13/03/2016.
 */
package Objects.Asteroid;

import Components.Texture;
import Data.Config;
import Data.Vector2D;
import Objects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Asteroid extends GameObject
{
    /**
     * The size instance variable is used to store the asteroids size.
     */
    private int size;

    /**
     * The Asteroid constructor is used to create a new asteroid game object.
     * @param position - The start position of the asteroid.
     * @param velocity - The speed of the asteroid.
     * @param direction - The direction the asteroid is moving.
     * @param size - The size of the asteroid.
     */
    public Asteroid(Vector2D position, Vector2D velocity, Vector2D direction, int size)
    {
        super(position, velocity, direction, 1, new Texture[]{Texture.asteroidTextures[size]});
        this.size = size;
    }

    /**
     * The Asteroid constructor is used to copy an asteroids data.
     * @param asteroid - The asteroid to copy from.
     */
    public Asteroid(Asteroid asteroid)
    {
        this(asteroid.getPosition(), asteroid.getVelocity(), asteroid.getDirection(), asteroid.size);
    }

    /**
     * The hit instance method is used to hit the asteroid.
     * @return - The points scored.
     */
    @Override
    public int hit()
    {
        return 0;
    }

    /**
     * The canHit instance method is used to check if an object can hit the asteroid.
     * @param object - The object to check.
     * @return - True if the object can hit the asteroid. False otherwise.
     */
    public boolean canHit(GameObject object)
    {
        return true;
    }

    /**
     * The draw instance method is used to draw the asteroid to a graphics object.
     * @param g - The graphics object to draw to.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(Config.STATIC_POSITION.getX(), Config.STATIC_POSITION.getY());
        g.translate(this.position.getX(), this.position.getY());
        g.rotate(this.direction.angle() + Math.PI / 2);
        for(Texture t : this.textures)
        {
            t.draw(g);
        }
        g.setTransform(at);
    }
}
