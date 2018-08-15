/**
 * Created by William on 08/03/2016.
 */
package Controls.AI;

import Data.Config;
import Data.Vector2D;
import Objects.Ship.GenericShip;

public class TurretAI extends GenericAI
{
    /**
     * The top instance variable is used to store if the ship being controlled is at the top or bottom of the window.
     */
    private boolean top;
    /**
     * The direction instance variable is used to store the direction that the ship being controlled is locked in.
     */
    private Vector2D direction;

    /**
     * The TurretAI constructor is used to create a new AI to target a given ship.
     * @param targetShip - The ship to be destroyed.
     * @param top - Is the ship being controlled on the top or bottom of the window.
     */
    public TurretAI(GenericShip targetShip, boolean top)
    {
        super(targetShip);
        this.top = top;
        if(this.top)
        {
            this.direction = new Vector2D(0, 1);
        }
        else
        {
            this.direction = new Vector2D(0, -1);
        }
    }

    /**
     * The invalidPoint instance method is used to check if the instance variable targetPoint is still suitable.
     * @return - True if the point is still suitable. False otherwise.
     */
    protected boolean invalidPoint()
    {
        return false;
    }

    /**
     * The closeToPoint instance method is used to check if the point in the instance variable targetPoint is close to the controlled ship.
     * @return - True if the controlled ship is close to the point. False otherwise.
     */
    protected boolean closeToPoint()
    {
        return true;
    }

    /**
     * The setTargetPoint instance method is used to change the point stored in the instance variable targetPoint to one that is suitable.
     */
    protected void setTargetPoint()
    {
        this.targetPoint = new Vector2D(0, 0);
    }

    /**
     * The inVision instance method is used to check if the ship being controlled is to be able to see a given point.
     * @param point - The point to be checked.
     * @return - True if the point can be seen. False otherwise.
     */
    private boolean inVision(Vector2D point)
    {
        double targetAngle = Math.atan2((point.getY() - this.ship.getPosition().getY()), (point.getX() - this.ship.getPosition().getX()));
        return this.direction.angle() - Math.toRadians(75) < targetAngle && targetAngle < this.direction.angle() + Math.toRadians(75);
    }

    /**
     * The turnToPoint instance method is used to turn the controlled ship towards a point.
     * @param point - The point the ship should turn to.
     */
    protected void turnToPoint(Vector2D point)
    {
        if(inVision(point))
        {
            // WORKS CLOCKWISE, NOT ANTI. FIX.
            double changeAngle = this.getAngle(point);
            double slowDown = this.ship.getRotationVelocity() / ((this.ship.getPowerupData().getRot_acceleration() * Config.UPDATE_PER_SECOND) * 0.45) * Config.UPDATE_PER_SECOND;
            if(Math.abs(changeAngle) < 0.05)
            {
                this.state.turn = 0;
            }
            else
            {
                if(changeAngle > slowDown)
                {
                    this.state.turn = 1;
                }
                else
                {
                    this.state.turn = -1;
                }
            }
        }
        else
        {
            this.state.turn = 0;
        }
    }

    /**
     * The goToPoint instance method is used to move the controlled ship towards a point.
     * @param point - The point the ship should move to.
     */
    protected void goToPoint(Vector2D point)
    {}

    /**
     * The fireAtPoint instance method is used to make the controlled ship fire bullets towards a point.
     * @param point - The points bullets should be fired at.
     */
    protected void fireAtPoint(Vector2D point)
    {
        double changeAngle = this.getAngle(point);
        if(Math.abs(changeAngle) < 0.5 && inVision(point))
        {
            this.state.shoot = true;
        }
        else
        {
            this.state.shoot = false;
        }
    }
}
