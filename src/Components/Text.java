/**
 * Created by William Davis on 19/02/2016.
 */
package Components;

import Data.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Text
{
    /**
     * The chars instance variable is used to store the list of characters in the string as data objects.
     */
    private Character[] chars;
    /**
     * The scale instance variable is used to store the scale that the text should be drawn at.
     */
    private double scale;

    /**
     * The Text constructor is used to convert a string into a list of character data for drawing.
     * @param text - The string to be shown.
     * @param scale - The scale the string is to be shown at.
     */
    public Text(String text, double scale)
    {
        this.chars = new Character[text.length()];
        int i = 0;
        for(char c: text.toCharArray())
        {
            this.chars[i] = new Character(c);
            ++i;
        }
        this.scale = scale;
    }

    /**
     * The getOffset instance method is used to get the offset data for the string.
     * @return - The offset data.
     */
    public Vector2D getOffset()
    {
        return new Vector2D(this.chars.length * 3 * this.scale, 3.5 * this.scale);
    }

    /**
     * The draw instance method is used to draw the string to a graphics object.
     * @param g - The graphics object to draw to.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.scale(this.scale, this.scale);
        g.translate(this.chars.length * -3, -3.5);
        for(Character c: this.chars)
        {
            c.draw(g);
            g.translate(6, 0);
        }
        g.setTransform(at);
    }
}
