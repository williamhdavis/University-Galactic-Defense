/**
 * Created by William on 09/03/2016.
 */
package Objects.Ship;

import Components.Texture;
import Controls.Controller;
import Data.Config;
import Data.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Turret extends GenericShip
{
    /**
     * The top instance variable is used to store if the turret is at the top or bottom of the game window.
     */
    private boolean top;
    /**
     * The supportMode instance variable is used to store the support type to be used.
     */
    private int supportMode;
    /**
     * The staticDirection instance variable is used to store the direction of the support.
     */
    private Vector2D staticDirection;

    /**
     * The Turret constructor is used to create a new turret.
     * @param controls - The controller to use.
     * @param position - The position of the turret.
     * @param top - If the turret is at the top or bottom of the game window.
     * @param lives - The lives the turret has.
     * @param bulletMode - The bullet type the turret has.
     * @param powerup - The powerup the turret has.
     * @param supportMode - The support type the turret has.
     */
    public Turret(Controller controls, Vector2D position, boolean top, int lives, int bulletMode, int powerup, int supportMode)
    {
        super(position, new Vector2D(), new Vector2D(0, -1), lives, controls, bulletMode, new int[]{1, powerup}, new Texture[]{Texture.shipTextures[3][0], Texture.shipTextures[3][supportMode], Texture.shipTextures[3][supportMode + 1]}, Texture.shipBulletSpawnOffsets[3]);
        this.controls.setShip(this);
        if(top)
        {
            this.direction.set(0, 1);
            this.staticDirection = new Vector2D(0, 1);
        }
        else
        {
            this.staticDirection = new Vector2D(0, -1);
        }
        this.top = top;
        this.supportMode = supportMode;
    }

    /**
     * The Turret constructor is used to copy a turret.
     * @param turret - The turret to copy.
     */
    public Turret(Turret turret)
    {
        this(turret.controls, turret.getPosition(), turret.top, turret.lives, turret.getBulletType(), turret.getPowerup(), turret.supportMode);
    }

    /**
     * The isDead instance method is used to check if the turret is no longer needed in memory.
     * @return - True if the turret can be deleted. False otherwise.
     */
    @Override
    public boolean isDead()
    {
        return false;
    }

    /**
     * The hit instance method is used to hit the ship.
     * @return - The score gained.
     */
    @Override
    public int hit()
    {
        int points = 0;
        if(!this.iHit && this.lives == 1)
        {
            points = 15;
        }
        super.hit();
        return points;
    }

    /**
     * The draw instance method is used to draw the ship to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(this.position.getX() + Config.STATIC_POSITION.getX(), this.position.getY());
        AffineTransform at2 = g.getTransform();
        g.rotate(this.staticDirection.angle() + Math.PI / 2);
        if(this.explodeState > Texture.largeExplosion.length / 2)
        {
            this.textures[2].draw(g);
        }
        else
        {
            this.textures[1].draw(g);
        }
        g.setTransform(at2);
        double rot = this.direction.angle() + Math.PI / 2;
        g.rotate(rot);
        if(this.explodeState < 2)
        {
            this.textures[0].draw(g);
        }
        if(this.lives == 0 && !super.isDead())
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
