/**
 * Created by William Davis on 14/03/2016.
 */
package Objects.Static.Menu;

import Components.Button;
import Components.Text;
import Data.Config;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class HighMenu extends GenericMenu
{
    /**
     * The data instance variable is used to store the list of high scores.
     */
    private List<String> data;
    /**
     * The lowPass instance variable is used to store the lowest score in the high score list.
     */
    private int lowPass;

    /**
     * The HighMenu constructor is used to create a new high score display level.
     */
    public HighMenu()
    {
        super();
        this.buttons.add(new Button("< Back", 5, true));
        this.data = new LinkedList<String>();
        this.lowPass = 0;
        Properties prop = new Properties();
        try
        {
            File f = new File(System.getProperty("user.dir") + "/data/highscores.properties");
            if(f.exists())
            {
                prop.load(new FileReader(f));
            }
            else
            {
                prop.load(this.getClass().getResourceAsStream("/Data/highscores.properties"));
            }
            int i = 0;
            while(i < 5)
            {
                String tag = prop.getProperty(i + "_tag");
                String score = prop.getProperty(i + "_score");
                if(tag == null || score == null)
                {
                    this.data.clear();
                    this.data.add("");
                    this.data.add("Error loading data.");
                    return;
                }
                if(i == 4)
                {
                    try
                    {
                        this.lowPass = Integer.parseInt(score);
                    }
                    catch(NumberFormatException ex)
                    {}
                }
                this.data.add((i + 1) + ":" + tag + "-" + score);
                ++i;
            }
        }
        catch(NullPointerException ex)
        {
            this.data.add("");
            this.data.add("Error loading data.");
        }
        catch(IOException ex)
        {
            this.data.add("");
            this.data.add("Error loading data.");
        }
    }

    /**
     * The getMinScore instance method is used to get the lowest score in the high score list.
     * @return - The lowest high score.
     */
    public int getMinScore()
    {
        return this.lowPass;
    }

    /**
     * The draw instance method is used to draw the menu to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(10, 10);
        g.scale(0.6, 0.6);
        this.buttons.get(0).draw(g);
        g.setTransform(at);

        g.translate(Config.WIDTH / 2, 150);
        for(String s: this.data)
        {
            Text t = new Text(s, 8);
            t.draw(g);
            g.translate(0, t.getOffset().getY() * 2 + 50);
        }
        g.setTransform(at);
    }
}
