/**
 * Created by William Davis on 10/02/2016.
 */
package Objects.Static;

import Components.Text;
import Data.Config;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Cover extends StaticPart
{
    /**
     * The state instance variable is used to store the fade level of the loading screen.
     */
    private int state;
    /**
     * The covering instance variable is used to store if the loading screen is being displayed or not.
     */
    private boolean covering;
    /**
     * The text instance variable is used to store the text to be displayed on the loading screen.
     */
    private Text text;

    /**
     * The Cover constructor is used to create a new loading screen.
     */
    public Cover()
    {
        this.state = 0;
        this.covering = false;
        this.text = new Text("Loading…", 10);
    }

    /**
     * The toBlack instance method is used to set the loading screen to display.
     */
    public void toBlack()
    {
        this.covering = true;
    }

    /**
     * The toClear instance method is used to set the loading screen to hidden.
     */
    public void toClear()
    {
        this.covering = false;
    }

    /**
     * The transitionDone instance method is used to check if the loading screen is done fading.
     * @return - True if the fading is done. False otherwise.
     */
    public boolean transitionDone()
    {
        return (this.covering && this.state == 255) || (!this.covering && this.state == 0);
    }

    /**
     * The draw instance method is used to draw the cover to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        if(this.covering && this.state < 255)
        {
            this.state += 5;
        }
        else if(!this.covering && this.state > 0)
        {
            this.state -= 5;
        }
        g.setColor(new Color(0, 0, 0, this.state));
        g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);
        AffineTransform at = g.getTransform();
        g.translate(Config.WIDTH / 2, Config.HEIGHT / 2);
        g.setColor(new Color(255, 255, 255, this.state));
        this.text.draw(g);
        g.setTransform(at);
    }
}
