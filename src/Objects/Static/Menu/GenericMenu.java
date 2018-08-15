/**
 * Created by William on 12/02/2016.
 */
package Objects.Static.Menu;

import Components.Button;
import Objects.Static.StaticPart;

import java.util.LinkedList;
import java.util.List;

public abstract class GenericMenu extends StaticPart
{
    /**
     * The buttons instance variable is used to store the buttons on the menu.
     */
    List<Button> buttons;

    /**
     * The GenericMenu constructor is used to create a new menu.
     */
    public GenericMenu()
    {
        this.buttons = new LinkedList<Button>();
    }

    /**
     * The changeSelection instance method is used to change the button selected.
     * @param direction - The direction to move through the buttons.
     */
    public void changeSelection(int direction)
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
        if(0 <= i + direction && i + direction <= this.buttons.size() - 1)
        {
            this.buttons.get(i).unselect();
            this.buttons.get(i + direction).select();
        }
    }

    /**
     * The getSelected instance method is used to get the level shift to apply from the selected button.
     * @return - The level shift.
     */
    public int getSelected()
    {
        for(Button b: this.buttons)
        {
            if(b.isSelected())
            {
                return b.getAction();
            }
        }
        return 0;
    }
}
