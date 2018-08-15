/**
 * Created by William Davis on 12/02/2016.
 */
package Objects.Ship;

import Components.Texture;
import Controls.Controller;
import Controls.Keyboard;
import Controls.State;
import Data.Config;
import Data.Vector2D;
import Objects.Bullet.GenericBullet;
import Objects.Bullet.StandardBullet;
import Objects.Bullet.SplittingBullet;
import Objects.GameObject;
import Objects.Powerup.PowerupData;
import Objects.Station.StationSection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class GenericShip extends GameObject
{
    /**
     * The controls instance variable is used to store the controller to be used.
     */
    protected Controller controls;
    /**
     * The rotation instance variable is used to store the current rotation speed.
     */
    protected double rotation;
    /**
     * The bulletType instance variable is used to store the bullet type the ship has.
     */
    protected int bulletType;
    /**
     * The bullets instance variable is used to store the bullets that the ship has fired.
     */
    protected List<GenericBullet> bullets;
    /**
     * The powerup instance variable is used to store the power up data the ship has.
     */
    protected int[] powerup;
    /**
     * The nextShot instance variable is used to store when the ship can next fire.
     */
    private long nextShot;
    /**
     * The bulletSpawnOffset instance variable is used to store the offset of the bullets.
     */
    private int bulletSpawnOffset;
    /**
     * The player instance variable is used to store if the ship is the player ship.
     */
    private boolean player;

    /**
     * The GenericShip constructor is used to create a new ship.
     * @param position - The position of the ship.
     * @param velocity - The initial velocity of the ship.
     * @param direction - The direction of the ship.
     * @param lives - The lives the ship has.
     * @param controls - The controller the ship should use.
     * @param bulletMode - The bullet mode the ship has.
     * @param powerup - The power up data the ship has.
     * @param textures - The textures the ship has.
     * @param bullletSpawnOffset - The spawn point of bullets.
     */
    public GenericShip(Vector2D position, Vector2D velocity, Vector2D direction, int lives, Controller controls, int bulletMode, int[] powerup, Texture[] textures, int bullletSpawnOffset)
    {
        super(position, velocity, direction, lives, textures, true);
        this.controls = controls;
        this.bulletType = bulletMode;
        this.bullets = new LinkedList<GenericBullet>();
        this.powerup = powerup;
        this.nextShot = 0;
        this.bulletSpawnOffset = bullletSpawnOffset;
        this.rotation = 0.0;
        if(this.controls instanceof Keyboard)
        {
            this.player = true;
        }
        else
        {
            this.player = false;
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
        int a = this.hit();
        int b = item.hit();
        if(item instanceof GenericBullet)
        {
            if(((GenericBullet)item).isPlayerBullet())
            {
                return a + b;
            }
            else
            {
                return b;
            }
        }
        else
        {
            return a + b;
        }
    }

    /**
     * The getRotationVelocity instance method is used to get the rotation speed the ship has.
     * @return
     */
    public double getRotationVelocity()
    {
        return this.rotation;
    }

    /**
     * The canHit instance method is used to check if an object can be hit by ships.
     * @param object - The object to check.
     * @return - True if the object can hit the bullet. False otherwise.
     */
    public boolean canHit(GameObject object)
    {
        if(object instanceof GenericShip)
        {
            return true;
        }
        else if(object instanceof GenericBullet)
        {
            return true;
        }
        else if(object instanceof StationSection)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * The getBullets instance method is used to get the bullets the ship has fired.
     * @return - The list of bullets.
     */
    public List<GenericBullet> getBullets()
    {
        List<GenericBullet> hold = new LinkedList<GenericBullet>();
        hold.addAll(this.bullets);
        this.bullets.clear();
        return hold;
    }

    /**
     * The getPowerupData instance method is used to get the power up data of the equipped power up.
     * @return - The power up data.
     */
    public PowerupData getPowerupData()
    {
        return PowerupData.all[this.powerup[0]][this.powerup[1]];
    }

    /**
     * The getBulletType instance method is used to get the bullet type equipped.
     * @return - The bullet type.
     */
    public int getBulletType()
    {
        return this.bulletType;
    }

    /**
     * The getPowerup instance method is used to get the power up value that the ship has.
     * @return
     */
    public int getPowerup()
    {
        return this.powerup[1];
    }

    /**
     * The update instance method is used to update the ship
     */
    @Override
    public void update()
    {
        if(this.controls != null && this.explodeState < 2)
        {
            this.controls.setShip(this);
            State state = this.controls.getState();
            if(state.turn == 0)
            {
                if(this.rotation > Config.SHIP_ROT_DRAG * Config.UPDATE_PER_SECOND)
                {
                    this.rotation -= Config.SHIP_ROT_DRAG * Config.UPDATE_PER_SECOND;
                }
                else if(Config.SHIP_ROT_DRAG * -1 > this.rotation)
                {
                    this.rotation += Config.SHIP_ROT_DRAG * Config.UPDATE_PER_SECOND;
                }
                else
                {
                    this.rotation = 0.0;
                }
            }
            else
            {
                this.rotation += state.turn * this.getPowerupData().getRot_acceleration() * Config.UPDATE_PER_SECOND;
                if(this.rotation > this.getPowerupData().getRot_limit())
                {
                    this.rotation = this.getPowerupData().getRot_limit();
                }
                else if(this.rotation < this.getPowerupData().getRot_limit() * -1)
                {
                    this.rotation = this.getPowerupData().getRot_limit() * -1;
                }
            }
            this.direction.rotate(this.rotation);

            Vector2D drag = new Vector2D(this.velocity);
            drag.normalise().mult(Config.SHIP_DRAG * Config.UPDATE_PER_SECOND);
            if(this.velocity.mag() > drag.mag())
            {
                this.velocity.subtract(drag);
            }
            else
            {
                this.velocity.set(0, 0);
            }

            Vector2D thrust = new Vector2D(this.getPowerupData().getAcceleration() * Config.UPDATE_PER_SECOND * state.thrust, this.getPowerupData().getAcceleration() * Config.UPDATE_PER_SECOND * state.slide);
            this.velocity.add(thrust.rotate(this.direction.angle()));
            if(this.velocity.mag() > this.getPowerupData().getLimit())
            {
                this.velocity.normalise().mult(this.getPowerupData().getLimit());
            }


            if(this.bulletType >= 0)
            {
                if(state.shoot && this.nextShot < Config.UPDATE_NOW)
                {
                    this.nextShot = Config.UPDATE_NOW + this.getPowerupData().getBullet_fire();
                    Vector2D pos = new Vector2D(this.direction);
                    pos.normalise().mult(this.bulletSpawnOffset).add(this.position);
                    Vector2D vel = new Vector2D(this.direction);
                    int fuz = this.getPowerupData().getRot_fuz();
                    if(fuz != 0)
                    {
                        Random r = new Random();
                        vel.rotate(Math.toRadians((r.nextInt(fuz * 10) / 5) - fuz));
                    }
                    vel.normalise().mult(this.getPowerupData().getBullet_speed()).add(this.velocity);
                    switch(this.bulletType)
                    {
                        case 0:
                            this.bullets.add(new StandardBullet(pos, vel, this.player));
                            break;
                        case 1:
                            this.bullets.add(new SplittingBullet(pos, vel, this.player));
                            break;
                    }
                }
            }
        }

        if(this.lives > 0)
        {
            List<GenericBullet> bTemp = new ArrayList<GenericBullet>();
            for(GenericBullet b : this.bullets)
            {
                if(!b.isDead())
                {
                    bTemp.add(b);
                }
            }
            this.bullets.clear();
            this.bullets.addAll(bTemp);
        }
        else
        {
            this.velocity.set(0, 0);
        }
        super.update();
    }
}
