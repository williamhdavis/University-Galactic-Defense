/**
 * Created by William Davis on 10/02/2016.
 */
package Components;

import Data.Config;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Button
{
    /**
     * The text instance variable is used to store the text that is to be shown on the button.
     */
    protected Text text;
    /**
     * The selected instance variable is used to store if this button is selected.
     */
    protected boolean selected;
    /**
     * The action instance variable is used to store the level shift that should be applied if this button is pressed.
     */
    protected int action;

    /**
     * The Button constructor is used to create a new button for displaying.
     * @param text - The text to be on the button.
     * @param action - The level shift to be applied if this button is pressed.
     */
    public Button(String text, int action)
    {
        this.text = new Text(text, Config.BUTTON_TEXT_SCALE);
        this.selected = false;
        this.action = action;
    }

    /**
     * The Button constructor is used to create a new button for displaying.
     * @param text - The text to be on the button.
     * @param action - The level shift to be applied if this button is pressed.
     * @param selected - The state of the button, selected or not.
     */
    public Button(String text, int action, boolean selected)
    {
        this(text, action);
        this.selected = selected;
    }

    /**
     * The select instance method is used to select this button.
     */
    public void select()
    {
        this.selected = true;
    }

    /**
     * The unselect instance method is used to deselect this button.
     */
    public void unselect()
    {
        this.selected = false;
    }

    /**
     * The getAction instance method is used to get the level shift that should be applied.
     * @return - The level shift.
     */
    public int getAction()
    {
        return this.action;
    }

    /**
     * The isSelected instance method is used to check if this button is currently selected.
     * @return - True if button selected. False otherwise.
     */
    public boolean isSelected()
    {
        return this.selected;
    }

    /**
     * The draw instance method is used to draw the button to a graphics object.
     * @param g - The graphics object to draw to.
     */
    public void draw(Graphics2D g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        if(this.selected)
        {
            g.setColor(new Color(0, 0, 0, 100));
        }
        else
        {
            g.setColor(Color.BLACK);
        }
        g.fillRect(Config.BUTTON_BORDER, Config.BUTTON_BORDER, Config.BUTTON_WIDTH - Config.BUTTON_BORDER * 2, Config.BUTTON_HEIGHT - Config.BUTTON_BORDER * 2);
        AffineTransform at = g.getTransform();
        g.translate((Config.BUTTON_WIDTH / 2), Config.BUTTON_HEIGHT / 2);
        g.setColor(Color.WHITE);
        this.text.draw(g);
        g.setTransform(at);
    }
}
