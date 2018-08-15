/**
 * Created by William on 10/02/2016.
 */
package Objects.Static;

import Data.Config;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Background extends StaticPart
{
    /**
     * The screens instance variable is used to store the number of screens making up the background.
     */
    private int screens;
    /**
     * The entry instance variable is used to store the background entry colour.
     */
    private Color entry;
    /**
     * The exit instance variable is used to store the background exit colour.
     */
    private Color exit;
    /**
     * The rates instance variable is used to store the star parallax rates.
     */
    private double[] rates;
    /**
     * The stars instance variable is used to store the list of stars on the background.
     */
    private List<Integer[]>[] stars;

    /**
     * The Background constructor is used to create a new background.
     * @param screens - The number of screens the background should be.
     * @param color - The backgrounds entry colour.
     */
    public Background(int screens, Color color)
    {
        this.entry = color;
        this.exit = null;
        this.screens = screens + Config.BACKGROUND_NUMBER_DEFAULT;
        this.stars = new List[]{new LinkedList<Integer[]>(), new LinkedList<Integer[]>(), new LinkedList<Integer[]>()};
        this.rates = new double[]{2, 2.2, 2.5};
        int i = 0;
        while(i < (Config.WIDTH / 2) * this.screens + (Config.BACKGROUND_PATCH * 2) / Config.STAR_DISTRIBUTION)
        {
            int j = 0;
            while(j < Config.HEIGHT / Config.STAR_DISTRIBUTION)
            {
                Random r = new Random();
                if(r.nextInt(100) < Config.STAR_DENSITY)
                {
                    Integer[] a = {i * Config.STAR_DISTRIBUTION, j * Config.STAR_DISTRIBUTION};
                    this.stars[r.nextInt(3)].add(a);
                }
                ++j;
            }
            ++i;
        }
    }

    /**
     * The Background constructor is used to create a new background.
     * @param screens - The number of screens the background should be.
     * @param entry - The backgrounds entry colour.
     * @param exit - The backgrounds exit colour.
     */
    public Background(int screens, Color entry, Color exit)
    {
        this(screens, entry);
        this.exit = exit;
    }

    /**
     * The getWidth instance method is used to get the width of the background.
     * @return - The width.
     */
    public int getWidth()
    {
        return Config.WIDTH * this.screens;
    }

    /**
     * The draw instance method is used to draw the background to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(Config.STATIC_POSITION.getX() - Config.BACKGROUND_PATCH, Config.STATIC_POSITION.getY());
        if(this.exit == null)
        {
            g.setColor(this.entry);
        }
        else
        {
            GradientPaint gp = new GradientPaint(0, 0, this.entry, Config.WIDTH * this.screens, 0, this.exit);
            g.setPaint(gp);
        }
        g.fillRect(0, 0, Config.WIDTH * this.screens + Config.BACKGROUND_PATCH * 2, Config.HEIGHT);
        g.setColor(Color.WHITE);
        g.setTransform(at);
        int i = 0;
        while(i < this.stars.length)
        {
            g.translate(Config.STATIC_POSITION.getX() / this.rates[i] - Config.BACKGROUND_PATCH, Config.STATIC_POSITION.getY());
            for(Integer[] s : this.stars[i])
            {
                if(s[0] + Config.STATIC_POSITION.getX() / this.rates[i] - Config.BACKGROUND_PATCH >= 0 && s[0] + Config.STATIC_POSITION.getX() / this.rates[i] - Config.BACKGROUND_PATCH <= Config.WIDTH)
                {
                    g.fillOval(s[0], s[1], 2, 2);
                }
            }
            g.setTransform(at);
            ++i;
        }
    }
}
