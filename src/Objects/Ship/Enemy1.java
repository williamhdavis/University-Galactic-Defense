/**
 * Created by William Davis on 25/02/2016.
 */
package Objects.Ship;

import Components.Texture;
import Controls.Controller;
import Controls.State;
import Data.Config;
import Data.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Enemy1 extends GenericShip
{
    /**
     * The Enemy1 constructor is used to create a new enemy ship.
     * @param controls - The controller to use.
     * @param position - The start position of the ship.
     * @param lives - The lives the ship has.
     * @param bulletMode - The bullet type the ship has.
     * @param powerup - The power up the ship has equipped.
     */
    public Enemy1(Controller controls, Vector2D position, int lives, int bulletMode, int powerup)
    {
        super(position, new Vector2D(0, 0), new Vector2D(-1, 0), lives, controls, bulletMode, new int[]{1, powerup}, new Texture[]{Texture.shipTextures[2][0]}, Texture.shipBulletSpawnOffsets[2]);
        this.controls.setShip(this);
    }

    /**
     * The Enemy1 constructor is used to copy an enemy ship.
     * @param enemy - The ship to copy.
     */
    public Enemy1(Enemy1 enemy)
    {
        this(enemy.controls, enemy.getPosition(), enemy.lives, enemy.getBulletType(), enemy.getPowerup());
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
            points = 25;
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
        double rot = this.direction.angle() + Math.PI / 2;
        g.rotate(rot);
        if(this.explodeState < 2)
        {
            State state = this.controls.getState();
            if(state.thrust == 1 || state.turn == 1)
            {
                Texture.shipTextures[2][1].draw(g);
            }
            if(state.thrust == 1 || state.turn == -1)
            {
                Texture.shipTextures[2][2].draw(g);
            }
            if(state.thrust == -1 || state.turn == -1)
            {
                Texture.shipTextures[2][3].draw(g);
            }
            if(state.thrust == -1 || state.turn == 1)
            {
                Texture.shipTextures[2][4].draw(g);
            }
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
