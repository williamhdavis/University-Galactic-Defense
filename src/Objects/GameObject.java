/**
 * Created by William on 10/02/2016.
 */
package Objects;

import Components.Texture;
import Data.Boundary;
import Data.Config;
import Data.Vector2D;
import Objects.Bullet.GenericBullet;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public abstract class GameObject
{
    /**
     * The position instance variable is used to store the objects position.
     */
    protected Vector2D position;
    /**
     * The velocity instance variable is used to store the objects velocity.
     */
    protected Vector2D velocity;
    /**
     * The direction instance variable is used to store the objects direction.
     */
    protected Vector2D direction;
    /**
     * The lives instance variable is used to store the objects lives.
     */
    protected int lives;
    /**
     * The textures instance variable is used to store the objects textures.
     */
    protected Texture[] textures;
    /**
     * The explodeState instance variable is used to store the current explosion frame.
     */
    protected int explodeState;
    /**
     * The iHit instance method is used to store if the object has been hit.
     */
    protected boolean iHit;
    /**
     * The animationUpdate instance method is used to store the next update time for the object.
     */
    protected long animationUpdate;
    /**
     * The hitRate instance variable is used to store the minimum time between hits.
     */
    private long hitRate;

    /**
     * The GameObject constructor is used to create a new game object.
     * @param position - The position of the object.
     * @param velocity - The velocity of the object.
     * @param direction - The direction of the object.
     * @param lives - The lives the object has.
     * @param textures - The textures the object has.
     */
    public GameObject(Vector2D position, Vector2D velocity, Vector2D direction, int lives, Texture[] textures)
    {
        this(position, velocity, direction, lives, textures, false);
    }

    /**
     * The GameObject constructor is used to create a new game object.
     * @param position - The position of the object.
     * @param velocity - The velocity of the object.
     * @param direction - The direction of the object.
     * @param lives - The lives the object has.
     * @param textures - The textures the object has.
     * @param canExplode - If the object is able to explode.
     */
    public GameObject(Vector2D position, Vector2D velocity, Vector2D direction, int lives, Texture[] textures, boolean canExplode)
    {
        this.position = position;
        this.velocity = velocity;
        this.direction = direction;
        this.lives = lives;
        this.textures = textures;
        if(canExplode)
        {
            this.explodeState = 0;
        }
        else
        {
            this.explodeState = -1;
        }
        this.animationUpdate = 0;
        this.iHit = false;
        this.hitRate = 0;
    }

    /**
     * The getPosition instance method is used to get the position of the object.
     * @return - The objects position.
     */
    public Vector2D getPosition()
    {
        return new Vector2D(this.position);
    }

    /**
     * The getVelocity instance method is used to get the velocity of the object.
     * @return - The objects velocity.
     */
    public Vector2D getVelocity()
    {
        return new Vector2D(this.velocity);
    }

    /**
     * The getDirection instance method is used to get the direction of the object.
     * @return - The objects direction.
     */
    public Vector2D getDirection()
    {
        return new Vector2D(this.direction);
    }

    /**
     * The isDead instance method is used to check if the object can be deleted from memory.
     * @return - True if the object can be deleted. False otherwise.
     */
    public boolean isDead()
    {
        if(this.lives == 0 && this.explodeState == -1)
        {
            return true;
        }
        else if(this.lives == 0 && this.explodeState >= Texture.largeExplosion.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * The hit instance method is used to hit the object.
     * @return - The score gained.
     */
    public int hit()
    {
        if(this.lives > 0 && this.hitRate < Config.UPDATE_NOW)
        {
            this.hitRate = Config.UPDATE_NOW + Config.UPDATE_HIT_DELAY;
            --this.lives;
        }
        if(this.lives == 0)
        {
            this.iHit = true;
        }
        return 0;
    }

    /**
     * The isHit instance method is used to check if the object has been hit.
     * @return - True if the object has been hit. False otherwise.
     */
    public boolean isHit()
    {
        return this.iHit;
    }

    /**
     * The canHit instance method is used to check if an object can be hit by gan object.
     * @param object - The object to check.
     * @return - True if the object can hit the bullet. False otherwise.
     */
    public abstract boolean canHit(GameObject object);

    /**
     * The triggerHit instance method is used to trigger hits based on the object type hit.
     * @param item - The object hit.
     * @return - The score gained.
     */
    public int triggerHit(GameObject item)
    {
        int a = this.hit();
        int b = item.hit();
        return a + b;
    }

    /**
     * The intersectCheck class method is used to check if two line segments are intersecting.
     * @param v1 - Line one, point one.
     * @param v2 - Line one, point two.
     * @param w1 - Line two, point one.
     * @param w2 - Line two, point two.
     * @return - True if the lines intersect, false otherwise.
     */
    private static boolean intersectCheck(Vector2D v1, Vector2D v2, Vector2D w1, Vector2D w2)
    {
        double changeX1 = v1.getX() - v2.getX();
        double changeY1 = v2.getY() - v1.getY();
        double changeX2 = w1.getX() - w2.getX();
        double changeY2 = w2.getY() - w1.getY();
        double res1 = changeY1 * v1.getX() + changeX1 * v1.getY();
        double res2 = changeY2 * w1.getX() + changeX2 * w1.getY();
        if(changeY1 * changeX2 - changeY2 * changeX1 != 0)
        {
            double x = (changeX2 * res1 - changeX1 * res2)/(changeY1 * changeX2 - changeY2 * changeX1);
            double y = (changeY1 * res2 - changeY2 * res1)/(changeY1 * changeX2 - changeY2 * changeX1);
            if(Math.min(v1.getX(), v2.getX()) <= x && x <= Math.max(v1.getX(), v2.getX()) && Math.min(v1.getY(), v2.getY()) <= y && y <= Math.max(v1.getY(), v2.getY()))
            {
                if(Math.min(w1.getX(), w2.getX()) <= x && x <= Math.max(w1.getX(), w2.getX()) && Math.min(w1.getY(), w2.getY()) <= y && y <= Math.max(w1.getY(), w2.getY()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The compareBox class method is used to each line segment of one points list to each line segment of another points list.
     * @param a - The first points list.
     * @param b - The second points list.
     * @return - True if any two line segments intersect. False otherwise.
     */
    private static boolean compareBox(Vector2D[] a, Vector2D[] b)
    {
        int i = 0;
        while(i < a.length)
        {
            Vector2D v1 = a[i];
            Vector2D v2;
            if(i == a.length - 1)
            {
                v2 = a[0];
            }
            else
            {
                v2 = a[i + 1];
            }
            int j = 0;
            while(j < b.length)
            {
                Vector2D v3 = b[j];
                Vector2D v4;
                if(j == b.length - 1)
                {
                    v4 = b[0];
                }
                else
                {
                    v4 = b[j + 1];
                }
                if(intersectCheck(v1, v2, v3, v4))
                {
                    return true;
                }
                ++j;
            }
            ++i;
        }
        return false;
    }

    /**
     * The getBounds instance method is used to get the boundary data for the game object when colliding with a given object.
     * @param object - The object being collided with.
     * @param simple - Is the simple or complex boundary wanted.
     * @return - The list of boundary datas for the object.
     */
    public List<Boundary> getBounds(GameObject object, boolean simple)
    {
        List<Boundary> bounds = new LinkedList<Boundary>();
        if(this.explodeState <= 0)
        {
            if(this.textures != null)
            {
                for(Texture t : this.textures)
                {
                    if(t.getBounds(simple) != null)
                    {
                        Boundary bound = new Boundary(t.getBounds(simple));
                        if(this.direction != null)
                        {
                            bound.setRotation(this.direction.angle() + Math.PI / 2);
                        }
                        bound.addOffset(this.position);
                        bounds.add(bound);
                    }
                }
                if(this instanceof GenericBullet)
                {
                    GenericBullet bul = (GenericBullet)this;
                    if(bul.lives < 95)
                    {
                        Vector2D split = new Vector2D(this.position).subtract(bul.getLastPos()).mult(0.2);
                        List<Boundary> eBounds = new LinkedList<Boundary>();
                        for(Boundary b : bounds)
                        {
                            int i = 0;
                            while(i < 5)
                            {
                                Boundary bou = new Boundary(b);
                                bou.addOffset(new Vector2D(split).mult((i + 1) * -1));
                                eBounds.add(bou);
                                ++i;
                            }
                        }
                        bounds.addAll(eBounds);
                    }
                }
            }
        }
        else if(this.explodeState < Texture.largeExplosion.length && Texture.largeExplosion[this.explodeState].getBounds(simple) != null)
        {
            Boundary b = new Boundary(Texture.largeExplosion[this.explodeState].getBounds(simple));
            if(this.direction != null)
            {
                b.setRotation(this.direction.angle() + Math.PI / 2);
            }
            b.addOffset(this.position);
            bounds.add(b);
        }
        return bounds;
    }

    /**
     * The checkCollision instance method is used to check if an object is colliding with this object.
     * @param item - The object being checked.
     * @return - True if a collision is happening. False otherwise.
     */
    public boolean checkCollision(GameObject item)
    {
        if(this.textures != null && item.textures != null)
        {
            boolean deep = false;
            List<Boundary> b1 = this.getBounds(item, true);
            List<Boundary> b2 = item.getBounds(this, true);
            if(b1.size() > 0 && b2.size() > 0)
            {
                int i = 0;
                while(i < b1.size() && !deep)
                {
                    Vector2D[] a = b1.get(i).get();
                    int j = 0;
                    while(j < b2.size() && !deep)
                    {
                        Vector2D[] b = b2.get(j).get();
                        deep = compareBox(a, b);
                        ++j;
                    }
                    ++i;
                }
            }
            if(deep)
            {
                List<Boundary> b3 = this.getBounds(item, false);
                if(b3.size() > 0)
                {
                    b1 = b3;
                }
                List<Boundary> b4 = item.getBounds(this, false);
                if(b4.size() > 0)
                {
                    b2 = b4;
                }
                int i = 0;
                while(i < b1.size())
                {
                    Vector2D[] a = b1.get(i).get();
                    int j = 0;
                    while(j < b2.size())
                    {
                        Vector2D[] b = b2.get(j).get();
                        if(compareBox(a, b))
                        {
                            return true;
                        }
                        ++j;
                    }
                    ++i;
                }
            }
        }
        return false;
    }

    /**
     * The update instance method is used to update the object.
     */
    public void update()
    {
        this.position.addScaled(this.velocity, Config.UPDATE_PER_SECOND);
    }

    /**
     * The draw instance method is used to draw the object to a graphics object.
     * @param g - The graphics object.
     */
    public abstract void draw(Graphics2D g);
}
