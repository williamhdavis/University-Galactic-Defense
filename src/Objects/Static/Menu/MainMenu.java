/**
 * Created by William Davis on 10/02/2016.
 */
package Objects.Static.Menu;

import Components.Button;
import Components.PreLoader;
import Data.Config;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class MainMenu extends GenericMenu
{
    /**
     * The MainMenu constructor is used to make a main menu level.
     */
    public MainMenu()
    {
        super();
        this.buttons.add(new Button("Play", 1, true));
        this.buttons.add(new Button("Highscores", -5));
    }

    /**
     * The draw instance method is used to draw the menu to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        if(PreLoader.needsRun())
        {
            PreLoader.draw(g);
        }
        AffineTransform at = g.getTransform();
        g.translate((Config.WIDTH / 2) - (Config.BUTTON_WIDTH / 2), (Config.HEIGHT / 2) - (((Config.BUTTON_HEIGHT + Config.BUTTON_BORDER * 2) * this.buttons.size()) - (Config.BUTTON_BORDER * 2)) / 2);
        for(Button b: this.buttons)
        {
            b.draw(g);
            g.translate(0, Config.BUTTON_HEIGHT + Config.BUTTON_BORDER * 2);
        }
        g.setTransform(at);
    }
}
