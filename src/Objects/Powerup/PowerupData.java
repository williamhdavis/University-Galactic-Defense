/**
 * Created by William Davis on 02/03/2016.
 */
package Objects.Powerup;

import Components.Texture;

public class PowerupData
{
    /**
     * The acceleration instance variable is used to store the acceleration the power up grants.
     */
    private int acceleration;
    /**
     * The limit instance variable is used to store the speed limit the power up grants.
     */
    private int limit;
    /**
     * The rot_acceleration instance variable is used to store the rotation acceleration the power up grants.
     */
    private double rot_acceleration;
    /**
     * The rot_limit instance variable is used to store the rotation speed limit the power up grants.
     */
    private double rot_limit;
    /**
     * The rot_fuz instance variable is used to store the firing fuzzing the power up grants.
     */
    private int rot_fuz;
    /**
     * The bullet_fire instance variable is used to store the bullet fire rate the power up grants.
     */
    private int bullet_fire;
    /**
     * The bullet_speed instance variable is used to store the muzzle speed the power up grants.
     */
    private int bullet_speed;
    /**
     * The texture instance variable is used to store the textures of the power up.
     */
    private Texture[] textures;

    /**
     * The PowerupData constructor is used to create a new power up definition.
     * @param acceleration - The acceleration the power up grants.
     * @param limit - The speed limit the power up grants.
     * @param rot_acceleration - The rotation acceleration the power up grants.
     * @param rot_limit - The rotation speed limit the power up grants.
     * @param rot_fuz - The bullet fire rate the power up grants.
     * @param bullet_fire - The bullet fire rate the power up grants.
     * @param bullet_speed - The muzzle speed the power up grants.
     * @param textures - The textures of the power up.
     */
    public PowerupData(int acceleration, int limit, double rot_acceleration, double rot_limit, int rot_fuz, int bullet_fire, int bullet_speed, Texture[] textures)
    {
        this.acceleration = acceleration;
        this.limit = limit;
        this.rot_acceleration = rot_acceleration;
        this.rot_limit = rot_limit;
        this.rot_fuz = rot_fuz;
        this.bullet_fire = bullet_fire;
        this.bullet_speed = bullet_speed;
        this.textures = textures;
    }

    /**
     * The getAcceleration instance method is used to get the acceleration the power up grants.
     * @return - The acceleration.
     */
    public int getAcceleration()
    {
        return this.acceleration;
    }

    /**
     * The getLimit instance method is used to get the speed limit the power up grants.
     * @return - The speed limit.
     */
    public int getLimit()
    {
        return this.limit;
    }

    /**
     * The getRot_acceleration instance method is used to get the rotation acceleration the power up grants.
     * @return - The rotation acceleration.
     */
    public double getRot_acceleration()
    {
        return this.rot_acceleration;
    }

    /**
     * The getRot_limit instance method is used to get the rotation speed limit the power up grants.
     * @return - The rotation speed limit.
     */
    public double getRot_limit()
    {
        return this.rot_limit;
    }

    /**
     * The getRot_fuz instance method is used to get the firing fuzzing the power up grants.
     * @return - The firing fuzzing.
     */
    public int getRot_fuz()
    {
        return this.rot_fuz;
    }

    /**
     * The getBullet_fire instance method is used to get the bullet fire rate the power up grants.
     * @return - The bullet fire rate.
     */
    public int getBullet_fire()
    {
        return this.bullet_fire;
    }

    /**
     * The getBullet_speed instance method is used to get the muzzle speed the power up grants.
     * @return - The muzzle speed.
     */
    public int getBullet_speed()
    {
        return this.bullet_speed;
    }

    /**
     * The getTextures instance method is used to get the textures of the power up.
     * @return - The textures.
     */
    public Texture[] getTextures()
    {
        return this.textures;
    }

    /**
     * The all class variable is used to store a list of all the available power ups.
     */
    public static PowerupData[][] all = {
        {
            new PowerupData(175, 350, Math.PI / 64, Math.PI / 64, 0, 250, 200, new Texture[]{Texture.powerupMenuTextures[0], Texture.powerupTextures[0]}),
            new PowerupData(175, 350, Math.PI / 64, Math.PI / 64, 0, 250, 200, new Texture[]{null, Texture.powerupTextures[0]}),
            new PowerupData(175, 350, Math.PI / 64, Math.PI / 64, 0, 250, 200, new Texture[]{null, Texture.powerupTextures[0]}),
            new PowerupData(175, 350, Math.PI / 64, Math.PI / 64, 0, 250, 300, new Texture[]{Texture.powerupMenuTextures[1], Texture.powerupTextures[1]}),
            new PowerupData(175, 350, Math.PI / 64, Math.PI / 64, 0, 250, 400, new Texture[]{Texture.powerupMenuTextures[2], Texture.powerupTextures[1]}),
            new PowerupData(175, 350, Math.PI / 64, Math.PI / 64, 0, 100, 200, new Texture[]{Texture.powerupMenuTextures[3], Texture.powerupTextures[1]}),
            new PowerupData(250, 500, Math.PI / 64, Math.PI / 64, 0, 250, 200, new Texture[]{Texture.powerupMenuTextures[4], Texture.powerupTextures[2]}),
            new PowerupData(325, 650, Math.PI / 64, Math.PI / 64, 0, 250, 200, new Texture[]{Texture.powerupMenuTextures[5], Texture.powerupTextures[2]}),
        },
        {
            new PowerupData(175, 350, Math.PI / 64, Math.PI / 64, 0, 250, 200, null), // Generic
            new PowerupData(250, 500, Math.PI / 64, Math.PI / 64, 7, 750, 200, null), // Enemy 1
            new PowerupData(175, 350, Math.PI / 128, Math.PI / 128, 7, 2500, 200, null), // Turret
        }
    };
}
