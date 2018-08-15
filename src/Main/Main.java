/**
 * Created by William Davis on 10/02/2016.
 */
package Main;

import javax.swing.*;

public class Main extends JFrame
{
    /**
     * The Main constructor is used to create the game window and begin the game.
     */
    public Main()
    {
        super("Galactic Defence");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        Game game = new Game();
        this.addKeyListener(game.getKeyboard());
        View view = new View(game);
        this.add(view);
        this.pack();
        game.start(view);
    }

    /**
     * The main class method is used as the entry point of the game.
     */
    public static void main(String[] args)
    {
        new Main();
    }
}
