/**
 * Created by William Davis on 14/03/2016.
 */
package Components;

import Data.Config;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SelectButton extends Button
{
    /**
     * The letter instance variable is used to store the character currently on the button.
     */
    private char letter;

    /**
     * The SelectButton constructor is used to create a new select button.
     * @param letter - The letter to be displayed.
     * @param destination - The level shift that should be applied if this button is selected.
     * @param selected - Whether the button is currently selected.
     */
    public SelectButton(char letter, int destination, boolean selected)
    {
        super(letter + "", destination, selected);
        this.letter = letter;
    }

    /**
     * The getChar instance method is used to get the character currently being shown on the button.
     * @return - The character being shown.
     */
    public char getChar()
    {
        return this.letter;
    }

    /**
     * The setChar instance method is used to change the letter being shown on the button.
     * @param letter - The new letter to be on the button.
     */
    public void setChar(char letter)
    {
        this.text = new Text(letter + "", Config.BUTTON_TEXT_SCALE);
        this.letter = letter;
    }

    /**
     * The draw instance method is used to draw the button to a graphics object.
     * @param g - The graphics object to draw to.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate((Config.BUTTON_HEIGHT / 2 + this.text.getOffset().getX() / 6), Config.BUTTON_HEIGHT / 2);
        Text a = new Text("^", Config.BUTTON_TEXT_SCALE);
        a.draw(g);
        g.setTransform(at);
        g.translate(0, a.getOffset().getY() * 4);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Config.BUTTON_HEIGHT, Config.BUTTON_HEIGHT);
        if(this.selected)
        {
            g.setColor(new Color(0, 0, 0, 100));
        }
        else
        {
            g.setColor(Color.BLACK);
        }
        g.fillRect(Config.BUTTON_BORDER, Config.BUTTON_BORDER, Config.BUTTON_HEIGHT - Config.BUTTON_BORDER * 2, Config.BUTTON_HEIGHT - Config.BUTTON_BORDER * 2);
        g.translate((Config.BUTTON_HEIGHT / 2 + this.text.getOffset().getX() / 6), Config.BUTTON_HEIGHT / 2);
        g.setColor(Color.WHITE);
        this.text.draw(g);
        g.translate(0, a.getOffset().getY() * 4);
        new Text("V", Config.BUTTON_TEXT_SCALE).draw(g);
        g.setTransform(at);
    }
}
