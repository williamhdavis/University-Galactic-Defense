/**
 * Created by William Davis on 11/02/2016.
 */
package Controls;

import Data.Config;
import Objects.Ship.GenericShip;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter implements Controller
{
    /**
     * The state instance variable is used to store the state to be applied to the ship.
     */
    private State state;

    public Keyboard()
    {
        this.state = new State();
    }

    /**
     * The getState instance method is used to get the current state that should be applied.
     * @return - The state the controls should be set to.
     */
    public State getState()
    {
        return this.state;
    }

    /**
     * The setShip instance method is used to set the ship being controlled.
     * @param ship - The ship being controlled.
     */
    public void setShip(GenericShip ship)
    {}

    /**
     * The keyPressed instance method is used to change the state based on key press events.
     * @param e - The event data.
     */
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == Config.KEY_BINDS[0])
        {
            this.state.thrust = 1;
        }
        else if(key == Config.KEY_BINDS[1])
        {
            this.state.thrust = -1;
        }
        else if(key == Config.KEY_BINDS[2])
        {
            this.state.slide = -1;
        }
        else if(key == Config.KEY_BINDS[3])
        {
            this.state.slide = 1;
        }
        else if(key == Config.KEY_BINDS[4])
        {
            this.state.turn = -1;
        }
        else if(key == Config.KEY_BINDS[5])
        {
            this.state.turn = 1;
        }
        else if(key == Config.KEY_BINDS[6])
        {
            this.state.shoot = true;
        }
        else if(key == Config.KEY_BINDS[7])
        {
            this.state.select = true;
        }
    }

    /**
     * The keyReleased instance method is used to change the state based on key release events.
     * @param e - The event data.
     */
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if((key == Config.KEY_BINDS[0]) && (this.state.thrust == 1))
        {
            this.state.thrust = 0;
        }
        else if((key == Config.KEY_BINDS[1]) && (this.state.thrust == -1))
        {
            this.state.thrust = 0;
        }
        else if((key == Config.KEY_BINDS[2]) && (this.state.slide == -1))
        {
            this.state.slide = 0;
        }
        else if((key == Config.KEY_BINDS[3]) && (this.state.slide == 1))
        {
            this.state.slide = 0;
        }
        else if((key == Config.KEY_BINDS[4]) && (this.state.turn == -1))
        {
            this.state.turn = 0;
        }
        else if((key == Config.KEY_BINDS[5]) && (this.state.turn == 1))
        {
            this.state.turn = 0;
        }
        else if((key == Config.KEY_BINDS[6]) && (this.state.shoot))
        {
            this.state.shoot = false;
        }
        else if((key == Config.KEY_BINDS[7]) && (this.state.select))
        {
            this.state.select = false;
        }
    }
}
