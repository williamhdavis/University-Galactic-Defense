/**
 * Created by William Davis on 11/02/2016.
 */
package Controls;

public class State
{
    /**
     * The thrust instance variable is used to store the thrust state.
     */
    public int thrust;
    /**
     * The slide instance variable is used to store the slide state.
     */
    public int slide;
    /**
     * The turn instance variable is used to store the turn state.
     */
    public int turn;
    /**
     * The shoot instance variable is used to store the shoot state.
     */
    public boolean shoot;
    /**
     * The select instance variable is used to store the select state.
     */
    public boolean select;

    /**
     * The State constructor us used to create a new state.
     */
    public State()
    {
        this.thrust = 0;
        this.slide = 0;
        this.turn = 0;
        this.shoot = false;
        this.select = false;
    }
}
