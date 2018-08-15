/**
 * Created by William Davis on 24/02/2016.
 */
package Objects.Static;

import Components.Text;
import Components.Texture;
import Data.Config;
import Data.Vector2D;
import Objects.Powerup.PowerupData;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Overlay extends StaticPart
{
    /**
     * The dispLives instance variable is used to store the lives the player has.
     */
    private int dispLives;
    /**
     * The dispScore instance variable is used to store the score the player has.
     */
    private int dispScore;
    /**
     * The ammo instance variable is used to store the ammo type the player has.
     */
    private int ammo;
    /**
     * The powerUp instance variable is used to store the power up data the player has.
     */
    private PowerupData powerUp;
    /**
     * The scoreLabel instance variable is used to store the text to display with the score.
     */
    private Text scoreLabel;
    /**
     * The ammoLabel instance variable is used to store the text to display above the ammo and power up.
     */
    private Text ammoLabel;

    /**
     * The Overlay constructor is used to make a new game overlay.
     */
    public Overlay()
    {
        this.dispLives = Config.SHIP_START_LIVES;
        this.dispScore = 0;
        this.ammo = 0;
        this.powerUp = PowerupData.all[0][0];
        this.scoreLabel = new Text("Score:", 2);
        this.ammoLabel = new Text("Power   Ammo", 2);
    }

    /**
     * The setData instance method is used to update the display information.
     * @param lives - The lives the player has.
     * @param score - The score the player has.
     * @param ammo - The ammo type the player has.
     * @param powerUp - The power up tpye the player has.
     */
    public void setData(int lives, int score, int ammo, PowerupData powerUp)
    {
        if(lives > 0)
        {
            this.dispLives = lives;
        }
        else
        {
            this.dispLives = 1;
        }
        this.dispScore = score;
        this.ammo = ammo;
        this.powerUp = powerUp;
    }

    /**
     * The draw instance method is used to draw the overlay to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        Text lives = new Text("*" + (this.dispLives - 1), 4);
        Text score = new Text(String.format("%010d", this.dispScore), 4);
        Vector2D offL = lives.getOffset();
        Vector2D offS = score.getOffset();
        AffineTransform at = g.getTransform();
        Texture.guiTextures[0].draw(g);

        g.translate(9, 13);
        g.scale(0.6, 0.6);
        g.translate(27, 31);
        Texture.shipTextures[0][0].draw(g);
        g.setTransform(at);

        g.translate(45, 17);
        g.translate(offL.getX(), offL.getY());
        lives.draw(g);
        g.setTransform(at);

        g.translate(Config.WIDTH / 2, this.scoreLabel.getOffset().getY() + 6);
        this.scoreLabel.draw(g);
        g.translate(0, offS.getY() + 14);
        score.draw(g);
        g.setTransform(at);

        g.translate(Config.WIDTH - (this.ammoLabel.getOffset().getX() + 13), this.scoreLabel.getOffset().getY() + 6);
        this.ammoLabel.draw(g);
        g.translate(24, 9);
        Texture.bulletMenuTextures[this.ammo].draw(g);
        g.translate(-96, 0);
        if(this.powerUp.getTextures() != null)
        {
            this.powerUp.getTextures()[0].draw(g);
        }
        else
        {
            PowerupData.all[0][0].getTextures()[0].draw(g);
        }
        g.setTransform(at);
    }
}
