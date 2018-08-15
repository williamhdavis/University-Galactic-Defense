/**
 * Created by William Davis on 20/02/2016.
 */
package Objects.Bullet;

import Components.Texture;
import Data.Config;
import Data.Vector2D;
import Objects.Asteroid.Asteroid;
import Objects.GameObject;
import Objects.Ship.GenericShip;
import Objects.Station.StationSection;

import java.util.LinkedList;
import java.util.List;

public abstract class GenericBullet extends GameObject
{
    /**
     * The nextUpdate instance variable is used to store the next update time.
     */
    protected long nextUpdate;
    /**
     * The player instance variable is used to store if this bullet was fired by the player.
     */
    protected boolean player;
    /**
     * The subBullets instance variable is used to store any bullets spawned by this bullet.
     */
    protected List<GenericBullet> subBullets;
    /**
     * The nearPos instance variable is used to store the last position of the bullet.
     */
    protected Vector2D nearPos;
    /**
     * The medPos instance variable is used to store the last position before nearPos.
     */
    protected Vector2D medPos;
    /**
     * The lastPos instance variable is used to store the last postion before medPos.
     */
    protected Vector2D lastPos;

    /**
     * The GenericBullet constructor is used to create a new bullet.
     * @param position - The position the bullet is spawned at.
     * @param velocity - The bullets initial velocity.
     * @param timeToLive - The updates the bullet has to live.
     * @param texture - The texture to use for the bullet.
     * @param playerBullet - If the bullet was fired by the player.
     */
    public GenericBullet(Vector2D position, Vector2D velocity, int timeToLive, Texture texture, boolean playerBullet)
    {
        super(position, velocity, new Vector2D(velocity), timeToLive, new Texture[]{texture});
        this.direction.normalise();
        this.nextUpdate = 0;
        this.player = playerBullet;
        this.subBullets = new LinkedList<GenericBullet>();
        this.nearPos = new Vector2D(position);
        this.medPos = new Vector2D(position);
        this.lastPos = new Vector2D(position);
    }

    /**
     * The canHit instance method is used to check if an object can be hit by bullets.
     * @param object - The object to check.
     * @return - True if the object can hit the bullet. False otherwise.
     */
    public boolean canHit(GameObject object)
    {
        if(object instanceof GenericShip)
        {
            return true;
        }
        else if(object instanceof StationSection)
        {
            return true;
        }
        else if(object instanceof Asteroid)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * The isPlayerBullet instance method is used to check if the bullet was fired by the player.
     * @return - True if the bullet was fired by the player. False otherwise.
     */
    public boolean isPlayerBullet()
    {
        return this.player;
    }

    /**
     * The getLastPos instance method is used to get the position of the bullet three updates ago.
     * @return - The position.
     */
    public Vector2D getLastPos()
    {
        return this.lastPos;
    }

    /**
     * The getSubBullets instance method is used to get any bullets spawned by this bullet.
     * @return - The bullets spawned.
     */
    public List<GenericBullet> getSubBullets()
    {
        List<GenericBullet> hold = new LinkedList<GenericBullet>();
        hold.addAll(this.subBullets);
        this.subBullets.clear();
        return hold;
    }

    /**
     * The triggerHit instance method is used to trigger hits based on the object type hit.
     * @param item - The object hit.
     * @return - The score gained.
     */
    @Override
    public int triggerHit(GameObject item)
    {
        int a = this.hit();
        int b = item.hit();
        if(this.isPlayerBullet())
        {
            return a + b;
        }
        else
        {
            return a;
        }
    }

    /**
     * The hit instance method is used to hit the bullet.
     * @return - The score gained.
     */
    @Override
    public int hit()
    {
        this.lives = 0;
        return 0;
    }

    /**
     * The update instance method is used to update the bullet.
     */
    @Override
    public void update()
    {
        if(Config.UPDATE_NOW > this.nextUpdate)
        {
            --this.lives;
            this.nextUpdate = Config.UPDATE_NOW + Config.BULLET_LIVE_TIME;
        }
        this.lastPos = new Vector2D(this.medPos);
        this.medPos = new Vector2D(this.nearPos);
        super.update();
        this.nearPos = new Vector2D(this.position);
    }
}
