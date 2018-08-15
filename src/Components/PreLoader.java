/**
 * Created by William on 22/02/2016.
 */
package Components;

import Data.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class PreLoader
{
    /**
     * The position class variable is used to store the location that graphics should be preloaded in.
     */
    private static Vector2D position = new Vector2D(-200, -200);

    /**
     * The explodeLarge class variable is used to track where in the explosion animation the class is.
     */
    private static int explodeLarge = 0;

    /**
     * The needsRun class method is used to check if there are more assets that need loading.
     * @return - True if all loading is done. False otherwise.
     */
    public static boolean needsRun()
    {
        return explodeLarge > Texture.largeExplosion.length;
    }

    /**
     * The draw instance method is used to draw a frame of the PreLoader to a graphics object.
     * @param g - The graphics object to draw to.
     */
    public static void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(position.getX(), position.getY());
        if(explodeLarge <= Texture.largeExplosion.length)
        {
            ++explodeLarge;
            Texture.largeExplosion[explodeLarge - 1].draw(g);
        }
        g.setTransform(at);
    }
}
