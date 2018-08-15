/**
 * Created by William Davis on 11/02/2016.
 */
package Controls;

import Objects.Ship.GenericShip;

public interface Controller
{
    /**
     * The getState instance method is used to get the current state that should be applied.
     * @return - The state the controls should be set to.
     */
    State getState();

    /**
     * The setShip instance method is used to set the ship being controlled.
     * @param ship - The ship being controlled.
     */
    void setShip(GenericShip ship);
}
