/**
 * Created by whdavi on 15/01/2016.
 */
package Data;

// mutable 2D vectors
public final class Vector2D
{
    private double x, y;

    // constructor for zero vector
    public Vector2D()
    {
        this.x = 0.0;
        this.y = 0.0;
    }

    // constructor for vector with given coordinates
    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * The getX instance method is used to get the x value.
     * @return - The x value.
     */
    public double getX()
    {
        return this.x;
    }

    /**
     * The getY instance method is used to get the y value.
     * @return - The y value.
     */
    public double getY()
    {
        return this.y;
    }

    // set coordinates
    public Vector2D set(double x, double y)
    {
        this.x = x;
        this.y = y;
        return this;
    }

    // set coordinates based on argument vector
    public Vector2D set(Vector2D v)
    {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o)
    {
        if(o instanceof Vector2D)
        {
            Vector2D n = (Vector2D)(o);
            //return ((this.x == n.x) && (this.y == n.y));
            double TOL = 1E-10;
            return ((Math.abs(this.x - n.x) < TOL) && (Math.abs(this.y - n.y) < TOL));
        }
        else
        {
            return false;
        }
    }

    // String for displaying vector as text
    public String toString()
    {
        return "(X: " + this.x + ", Y: " + this.y + ")";
    }

    //  magnitude (= "length") of this vector
    public double mag()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    // angle between vector and horizontal axis in radians
    public double angle()
    {
        return Math.atan2(this.y, this.x);
    }

    // angle between this vector and another vector
    public double angle(Vector2D other)
    {
        double ang = other.angle() - this.angle();
        if(ang < 0)
        {
            ang += 2 * Math.PI;
        }
        return ang;
    }

    // add argument vector
    public Vector2D add(Vector2D v)
    {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    // add values to coordinates
    public Vector2D add(double x, double y)
    {
        this.x += x;
        this.y += y;
        return this;
    }

    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac)
    {
        this.x += fac * v.x;
        this.y += fac * v.y;
        return this;
    }

    // subtract argument vector
    public Vector2D subtract(Vector2D v)
    {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    // subtract values from coordinates
    public Vector2D subtract(double x, double y)
    {
        this.x -= x;
        this.y -= y;
        return this;
    }

    // multiply with factor
    public Vector2D mult(double fac)
    {
        this.x *= fac;
        this.y *= fac;
        return this;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle)
    {
        double x = this.x * Math.cos(angle) - this.y * Math.sin(angle);
        double y = this.x * Math.sin(angle) + this.y * Math.cos(angle);
        this.x = x;
        this.y = y;
        return this;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v)
    {
        return Math.abs(this.mag()) * Math.abs(v.mag()) * Math.cos(this.angle(v));
    }

    // distance to argument vector
    public double dist(Vector2D v)
    {
        return Math.sqrt(Math.pow((v.x - this.x), 2) + Math.pow((v.y - this.y), 2));
    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise()
    {
        double len = this.mag();
        this.x /= len;
        this.y /= len;
        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
    public Vector2D wrap(double w, double h)
    {
        x = (x + w) % w;
        y = (y + h) % h;
        return this;
    }

    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag)
    {
        double x = Math.cos(angle) * mag;
        double y = Math.sin(angle) * mag;
        return new Vector2D(x, y);
    }
}