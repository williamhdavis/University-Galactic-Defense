/**
 * Created by William Davis on 25/02/2016.
 */
package Controls.AI;

import Data.Config;
import Data.Vector2D;
import Main.Level;
import Objects.Ship.GenericShip;

import java.util.Random;

public class BasicAI extends GenericAI
{
    /**
     * The BasicAI constructor is used to create a new AI to target a given ship.
     * @param targetShip - The ship to be destroyed.
     */
    public BasicAI(GenericShip targetShip)
    {
        super(targetShip);
    }

    /**
     * The invalidPoint instance method is used to check if the instance variable targetPoint is still suitable.
     * @return - True if the point is still suitable. False otherwise.
     */
    protected boolean invalidPoint()
    {
        if(this.targetPoint.dist(this.targetShip.getPosition()) > Config.AI_BASIC_BOUND_OUTER || Config.AI_BASIC_BOUND_INNER > this.targetPoint.dist(this.targetShip.getPosition()))
        {
            return true;
        }
        else if(Math.abs(Math.atan2((this.targetPoint.getY() - this.targetShip.getPosition().getY()), (targetPoint.getX() - this.targetShip.getPosition().getX()))) < Math.toRadians(25))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * The closeToPoint instance method is used to check if the point in the instance variable targetPoint is close to the controlled ship.
     * @return - True if the controlled ship is close to the point. False otherwise.
     */
    protected boolean closeToPoint()
    {
        return this.ship.getPosition().dist(this.targetPoint) < Config.AI_BASIC_SPOT_TOLERANCE;
    }

    /**
     * The setTargetPoint instance method is used to change the point stored in the instance variable targetPoint to one that is suitable.
     */
    protected void setTargetPoint()
    {
        this.state.turn = 0;
        this.state.thrust = 0;
        this.state.shoot = false;

        Random r = new Random();

        int x = r.nextInt(Config.AI_BASIC_BOUND_OUTER - Config.AI_BASIC_BOUND_INNER) + Config.AI_BASIC_BOUND_INNER;

        double targetRot = this.targetShip.getDirection().angle();
        Vector2D pos = new Vector2D(x, 0).rotate(targetRot);
        Vector2D neg = new Vector2D(x, 0).rotate(targetRot);

        double rot = Math.toRadians(r.nextInt(140 - 25) + 25);
        pos.rotate(rot);
        neg.rotate(rot * -1);

        pos.add(this.targetShip.getPosition());
        neg.add(this.targetShip.getPosition());

        if(this.ship.getPosition().dist(pos) < this.ship.getPosition().dist(neg))
        {
            this.targetPoint = pos;
        }
        else
        {
            this.targetPoint = neg;
        }
    }

    /**
     * The turnToPoint instance method is used to turn the controlled ship towards a point.
     * @param point - The point the ship should turn to.
     */
    protected void turnToPoint(Vector2D point)
    {
        double changeAngle = this.getAngle(point);
        double slowDown = this.ship.getRotationVelocity() / ((this.ship.getPowerupData().getRot_acceleration() * Config.UPDATE_PER_SECOND) * 0.35) * Config.UPDATE_PER_SECOND;
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

    /**
     * The goToPoint instance method is used to move the controlled ship towards a point.
     * @param point - The point the ship should move to.
     */
    protected void goToPoint(Vector2D point)
    {
        double changeAngle = this.getAngle(point);
        if(Math.abs(changeAngle) < 0.05)
        {
            if(this.ship.getPosition().dist(point) > this.ship.getVelocity().mag())
            {
                this.state.thrust = 1;
            }
            else
            {
                this.state.thrust = -1;
            }
        }
        else
        {
            this.state.thrust = 0;
        }
    }

    /**
     * The fireAtPoint instance method is used to make the controlled ship fire bullets towards a point.
     * @param point - The points bullets should be fired at.
     */
    protected void fireAtPoint(Vector2D point)
    {
        double changeAngle = this.getAngle(point);
        if(Math.abs(changeAngle) < 0.5)
        {
            this.state.shoot = true;
        }
        else
        {
            this.state.shoot = false;
        }
    }
}
