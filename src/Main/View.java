/**
 * Created by William on 10/02/2016.
 */
package Main;

import Data.Config;
import Objects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View extends JComponent
{
    /**
     * The game instance method is used to store the game data to be drawn.
     */
    private Game game;

    /**
     * The View constructor is used to create a new display window.
     * @param game - The game data.
     */
    public View(Game game)
    {
        this.game = game;
        this.setPreferredSize(Config.FRAME_SIZE);
    }

    /**
     * The paintComponent instance method is used to draw all the game objects to the window.
     * @param g - The graphics object to draw to.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        synchronized(Game.class)
        {
            List<GameObject> objects = this.game.getObjects();
            int i = 0;
            while(i < objects.size())
            {
                objects.get(i).draw((Graphics2D) g);
                ++i;
            }
        }
    }
}
