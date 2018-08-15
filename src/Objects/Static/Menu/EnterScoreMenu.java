/**
 * Created by William Davis on 14/03/2016.
 */
package Objects.Static.Menu;

import Components.Button;
import Components.SelectButton;
import Components.Text;
import Data.Config;
import Main.Level;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.Properties;

public class EnterScoreMenu extends GenericMenu
{
    /**
     * The level instance variable is used to store the level data.
     */
    private Level level;

    /**
     * The EnterScoreMenu constructor is used to create a enter score level.
     * @param level - The level data to use.
     */
    public EnterScoreMenu(Level level)
    {
        super();
        this.level = level;
        this.buttons.add(new SelectButton('A', 6, true));
        this.buttons.add(new SelectButton('A', 6, false));
        this.buttons.add(new SelectButton('A', 6, false));
    }

    /**
     * The save instance method is used to save the new score to the scores list.
     */
    public void save()
    {
        Properties prop = new Properties();
        Properties scores = new Properties();
        try
        {
            File e = new File(System.getProperty("user.dir") + "/data");
            if(!e.exists())
            {
                e.mkdirs();
            }
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
            int j = 0;
            while(i < 5)
            {
                String score = prop.getProperty(i + "_score");
                if(score != null)
                {
                    try
                    {
                        if(i != j || this.level.getScore() < Integer.parseInt(score))
                        {
                            scores.setProperty(i + "_tag", prop.getProperty(j + "_tag"));
                            scores.setProperty(i + "_score", prop.getProperty(j + "_score"));
                            ++j;
                        }
                        else
                        {
                            String s = "";
                            for(Button b : this.buttons)
                            {
                                s += ((SelectButton) b).getChar();
                            }
                            scores.setProperty(i + "_tag", s);
                            scores.setProperty(i + "_score", String.format("%010d", this.level.getScore()));
                        }
                    }
                    catch(NumberFormatException ex)
                    {}
                }
                ++i;
            }
            scores.store(new PrintWriter(System.getProperty("user.dir") + "/data/highscores.properties"), null);
        }
        catch(NullPointerException ex)
        {}
        catch(IOException ex)
        {}
    }

    /**
     * The changeLetter instance method is used to change the letter on a letter selector.
     * @param direction - The direction to move through the options.
     */
    public void changeLetter(int direction)
    {
        int i = 0;
        while(i < this.buttons.size())
        {
            if(this.buttons.get(i).isSelected())
            {
                break;
            }
            ++i;
        }
        SelectButton b = (SelectButton)this.buttons.get(i);
        char c = b.getChar();
        if('A' <= c + direction && c + direction <= 'Z')
        {
            b.setChar((char)(c + direction));
        }
    }

    /**
     * The draw instance method is used to draw the menu to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(Config.WIDTH / 2, 200);
        Text a = new Text("New High Score", 5);
        a.draw(g);
        Text b = new Text(String.format("%010d", this.level.getScore()), 10);
        g.translate(0, a.getOffset().getY() * 2 + b.getOffset().getY());
        b.draw(g);
        g.translate(0, b.getOffset().getY() * 2);
        g.translate((-Config.BUTTON_HEIGHT / 2) - (Config.BUTTON_HEIGHT), 0);
        int i = 0;
        while(i < this.buttons.size())
        {
            this.buttons.get(i).draw(g);
            g.translate(Config.BUTTON_HEIGHT, 0);
            ++i;
        }
        g.setTransform(at);
    }
}
