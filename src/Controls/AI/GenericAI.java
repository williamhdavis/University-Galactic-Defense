/**
 * Created by William on 29/02/2016.
 */
package Controls.AI;

import Controls.Controller;
import Controls.State;
import Data.Vector2D;
import Objects.Ship.GenericShip;

public abstract class GenericAI implements Controller
{
    /**
     * The state instance variable is used to store the state to be applied to the ship.
     */
    protected State state;
    /**
     * The ship instance variable is used to store the ship this AI is controlling.
     */
    protected GenericShip ship;
    /**
     * The targetShip instance variable is used to store the ship that this AI is trying to destroy.
     */
    protected GenericShip targetShip;
    /**
     * The targetPoint instance variable is used to store the position the AI wants to be at.
     */
    protected Vector2D targetPoint;

    /**
     * The GenericAI constructor is used to create a new AI instance for controlling a ship.
     * @param targetShip - The ship to be destroyed.
     */
    public GenericAI(GenericShip targetShip)
    {
        this.state = new State();
        this.targetShip = targetShip;
    }

    /**
     * The setShip instance method is used to set the ship the AI is controlling.
     * @param ship - The ship the AI is controlling.
     */
    public void setShip(GenericShip ship)
    {
        this.ship = ship;
    }

    /**
     * The getAngle instance method is used to get the angle between the controlled ship and a given point.
     * @param point - The target point.
     * @return - The angle in radians the ship being controlled needs to turn.
     */
    protected double getAngle(Vector2D point)
    {
        if(this.ship != null)
        {
            double targetAngle = Math.atan2((point.getY() - this.ship.getPosition().getY()), (point.getX() - this.ship.getPosition().getX()));
            if(targetAngle < 0)
            {
                targetAngle += 2 * Math.PI;
            }
            double currentAngle = this.ship.getDirection().angle();
            if(currentAngle < 0)
            {
                currentAngle += 2 * Math.PI;
            }
            double changeAngle = targetAngle - currentAngle;
            if(changeAngle > Math.PI)
            {
                changeAngle -= 2 * Math.PI;
            }
            else if(changeAngle < Math.PI * -1)
            {
                changeAngle += 2 * Math.PI;
            }
            return changeAngle;
        }
        else
        {
            return 0;
        }
    }

    /**
     * The getState instance method is used to get the current state that should be applied based on the current positions and interactions between the controlled ship and the target ship.
     * @return - The state the controls should be set to.
     */
    public State getState()
    {
        if(this.ship != null)
        {
            if(this.targetPoint != null)
            {
                // Target point is set.
                if(this.invalidPoint())
                {
                    // Target point is no longer valid. Pick a new one.
                    this.setTargetPoint();
                }
                else if(this.closeToPoint())
                {
                    // Aim at target and fire.
                    this.state.thrust = 0;
                    this.turnToPoint(this.targetShip.getPosition());
                    this.fireAtPoint(this.targetShip.getPosition());
                }
                else
                {
                    // Target point is still valid. Go to point.
                    this.state.shoot = false;
                    this.turnToPoint(this.targetPoint);
                    this.goToPoint(this.targetPoint);
                }
            }
            else
            {
                // Target point to be selected.
                this.setTargetPoint();
            }
        }
        else
        {
            // AI unable to function.
            this.state.thrust = 0;
            this.state.turn = 0;
            this.state.shoot = false;
        }
        return this.state;
    }

    /**
     * The invalidPoint instance method is used to check if the instance variable targetPoint is still suitable.
     * @return - True if the point is still suitable. False otherwise.
     */
    protected abstract boolean invalidPoint();

    /**
     * The closeToPoint instance method is used to check if the point in the instance variable targetPoint is close to the controlled ship.
     * @return - True if the controlled ship is close to the point. False otherwise.
     */
    protected abstract boolean closeToPoint();

    /**
     * The setTargetPoint instance method is used to change the point stored in the instance variable targetPoint to one that is suitable.
     */
    protected abstract void setTargetPoint();

    /**
     * The turnToPoint instance method is used to turn the controlled ship towards a point.
     * @param point - The point the ship should turn to.
     */
    protected abstract void turnToPoint(Vector2D point);

    /**
     * The goToPoint instance method is used to move the controlled ship towards a point.
     * @param point - The point the ship should move to.
     */
    protected abstract void goToPoint(Vector2D point);

    /**
     * The fireAtPoint instance method is used to make the controlled ship fire bullets towards a point.
     * @param point - The points bullets should be fired at.
     */
    protected abstract void fireAtPoint(Vector2D point);
}
