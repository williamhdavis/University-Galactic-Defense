/**
 * Created by William Davis on 13/03/2016.
 */
package Objects.Asteroid;

import Components.Texture;
import Data.Config;
import Data.Vector2D;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AsteroidBelt
{
    /**
     * The asteroids instance variable is used to store the list of asteroids in the asteroid belt.
     */
    private List<Asteroid> asteroids;
    /**
     * The direction instance variable is used to store the direction on the y-axis the asteroids are moving.
     */
    private int direction;
    /**
     * The total instance variable is used to store the maximum number of asteroids allowed.
     */
    private int total;
    /**
     * The width instance variable is used to store the width of the asteroid belt.
     */
    private int width;
    /**
     * The spawn instance variable is used to store the y-axis spawn point of the asteroids.
     */
    private int spawn;
    /**
     * The end instance variable is used to store the y-axis kill point of the asteroids.
     */
    private int end;
    /**
     * The position instance variable is used to store the location of the asteroid belt on the level.
     */
    private Vector2D position;

    /**
     * The AsteroidBelt constructor is used to create a new asteroid belt.
     * @param xStart - The staring x position of the asteroid belt.
     * @param width - The width of the asteroid belt.
     */
    public AsteroidBelt(int xStart, int width)
    {
        this.asteroids = new LinkedList<Asteroid>();
        Random r = new Random();
        this.direction = r.nextInt(2);
        if(this.direction == 0)
        {
            this.direction = -1;
            this.spawn = Config.HEIGHT + Config.SEGMENT_HEIGHT * 2;
            this.end = Config.SEGMENT_HEIGHT * -3;
        }
        else
        {
            this.spawn = Config.SEGMENT_HEIGHT * -2;
            this.end = Config.HEIGHT + Config.SEGMENT_HEIGHT * 3;
        }
        this.total = (r.nextInt(10) + 5) * width / 500;
        this.width = width;
        this.position = new Vector2D(xStart, 0);
        int i = 0;
        while(i < this.total / 2)
        {
            this.spawnAsteroid(true);
            ++i;
        }
    }

    /**
     * The AsteroidBelt constructor is used to copy an asteroid belts data.
     * @param belt - The asteroid belt to copy from.
     */
    public AsteroidBelt(AsteroidBelt belt)
    {
        this.asteroids = new LinkedList<Asteroid>();
        for(Asteroid a: belt.asteroids)
        {
            this.asteroids.add(new Asteroid(a));
        }
        this.direction = belt.direction;
        this.total = belt.total;
        this.width = belt.width;
        this.spawn = belt.spawn;
        this.end = belt.end;
        this.position = new Vector2D(belt.position);

    }

    /**
     * The spawnAsteroid instance method is used to spawn a new asteroid.
     * @param init - Should initialisation offsets be applied.
     */
    private void spawnAsteroid(boolean init)
    {
        Random r = new Random();
        int size;
        int pick = r.nextInt(8);
        if(pick < 4)
        {
            size = 0;
        }
        else if(pick < 7)
        {
            size = 1;
        }
        else
        {
            size = 2;
        }
        Vector2D start = new Vector2D(r.nextInt(width - (Texture.asteroidOffsets[size])) + Texture.asteroidOffsets[size], this.spawn);
        Vector2D end = new Vector2D(r.nextInt(width - (Texture.asteroidOffsets[size])) + Texture.asteroidOffsets[size], this.end);
        double targetAngle = Math.atan2((end.getY() - start.getY()), (end.getX() - start.getX()));
        double vel = r.nextInt(Config.ASTEROID_MAX_SPEED[size] - Config.ASTEROID_MIN_SPEED[size]) + Config.ASTEROID_MIN_SPEED[size];
        Asteroid a = new Asteroid(new Vector2D(start).add(this.position), Vector2D.polar(targetAngle, vel), new Vector2D(1, 0).rotate(Math.toRadians(r.nextInt(360))), size);
        if(init)
        {
            int i = 0;
            while(i < (r.nextInt(250) + 500) * (size + 1))
            {
                a.update();
                ++i;
            }
        }
        this.asteroids.add(a);
    }

    /**
     * The getAsteroids instance method is used to get the list of asteroids in the belt.
     * @return - The list of asteroids.
     */
    public List<Asteroid> getAsteroids()
    {
        return this.asteroids;
    }

    /**
     * The update instance method is used to update all the asteroids in the belt.
     */
    public void update()
    {
        List<Asteroid> alive = new LinkedList<Asteroid>();
        for(Asteroid a: this.asteroids)
        {
            if(!(a.getPosition().getY() > this.end && this.direction == 1) && !(a.getPosition().getY() < this.end && this.direction == -1))
            {
                alive.add(a);
            }
        }
        this.asteroids.clear();
        this.asteroids.addAll(alive);
        if(this.asteroids.size() < this.total)
        {
            Random r = new Random();
            if(r.nextInt(20) < 2)
            {
                int i = 0;
                while(i < r.nextInt(this.total - this.asteroids.size()))
                {
                    this.spawnAsteroid(false);
                    ++i;
                }
            }
        }
    }
}
