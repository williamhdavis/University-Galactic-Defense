/**
 * Created by William Davis on 10/02/2016.
 */
package Components;

import Data.CharacterMap;
import java.awt.*;

public class Character
{
    /**
     * The structure instance variable is used to store the data that is used to draw the selected character.
     */
    private int[][] structure;

    /**
     * The Character constructor is used to load the data needed to draw the given character.
     * @param character - The character to be drawn.
     */
    public Character(char character)
    {
        this.structure = null;
        for(CharacterMap c: CharacterMap.getAll())
        {
            if(c.equals(character))
            {
                this.structure = c.structure();
                break;
            }
        }
    }

    /**
     * The draw instance method is used to draw the character to a graphics object.
     * @param g - The graphics object to draw to.
     */
    public void draw(Graphics2D g)
    {
        if(this.structure != null)
        {
            for(int[] s: this.structure)
            {
                if(s != null)
                {
                    g.fillRect(s[0], s[1], s[2], s[3]);
                }
            }
        }
    }
}
