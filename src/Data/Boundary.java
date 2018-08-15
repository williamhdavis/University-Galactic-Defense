/**
 * Created by William Davis on 19/02/2016.
 */
package Data;

import java.io.IOException;
import java.util.*;

public class Boundary
{
    /**
     * The vectors instance variable is used to store a list of points that make up a boundary.
     */
    private Vector2D[] vectors;

    /**
     * The Boundary constructor is used to create a new boundary from a list of vectors.
     * @param vectors - The points that make up the boundary.
     */
    public Boundary(Vector2D[] vectors)
    {
        this.vectors = vectors;
    }

    /**
     * The Boundary constructor is used to create a new boundary from a list of vectors.
     * @param vectors - The points that make up the boundary.
     * @param extraOffset - The ammount to adjust each point by.
     */
    public Boundary(Vector2D[] vectors, Vector2D extraOffset)
    {
        this(vectors);
        this.addOffset(extraOffset);
    }

    /**
     * The Boundary constructor is used to create a new boundary from a list of vectors.
     * @param bounds - The boundary to copy.
     */
    public Boundary(Boundary bounds)
    {
        this.vectors = bounds.get();
    }

    /**
     * The addOffset instance method is used shift the boundary
     * @param offset - The offset to shift the boundary by.
     */
    public void addOffset(Vector2D offset)
    {
        for(Vector2D v: this.vectors)
        {
            v.add(offset);
        }
    }

    /**
     * The setRotation instance method is used to rotate the boundary.
     * @param angle - The angle to rotate by.
     */
    public void setRotation(double angle)
    {
        for(Vector2D v: this.vectors)
        {
            v.rotate(angle);
        }
    }

    /**
     * The get instance method is used to get the boundary points.
     * @return - The list of points that make up the boundary.
     */
    public Vector2D[] get()
    {
        Vector2D[] hold = new Vector2D[this.vectors.length];
        int i = 0;
        while(i < this.vectors.length)
        {
            hold[i] = new Vector2D(this.vectors[i]);
            ++i;
        }
        return hold;
    }

    /**
     * The load class method is used to load a set of points from a properties file.
     * @param file - The file to load.
     * @param name - The name of the key in the file.
     * @param simple - Whether the simple or complex version of the boundry is needed.
     * @return - A new boundary made with the loaded points.
     */
    public static Boundary load(String file, String name, boolean simple)
    {
        return load(file, name, simple, null);
    }

    /**
     * The load class method is used to load a set of points from a properties file.
     * @param file - The file to load.
     * @param name - The name of the key in the file.
     * @param simple - Whether the simple or complex version of the boundry is needed.
     * @param offset - The ammount to shift the boundary by.
     * @return - A new boundary made with the loaded points.
     */
    public static Boundary load(String file, String name, boolean simple, Vector2D offset)
    {
        Properties prop = new Properties();
        try
        {
            prop.load(Boundary.class.getClass().getResourceAsStream("/Data/Boundaries/" + file + ".properties"));
            String ext = "complex";
            if(simple)
            {
                ext = "simple";
            }
            String data = prop.getProperty(name + "_" + ext);
            if(data != null)
            {
                String[] points = data.split("\\|");
                if(points.length > 0)
                {
                    Vector2D[] out = new Vector2D[points.length];
                    int i = 0;
                    while(i < points.length)
                    {
                        String[] pairs = points[i].split(",");
                        try
                        {
                            int x = Integer.parseInt(pairs[0]);
                            int y = Integer.parseInt(pairs[1]);
                            out[i] = new Vector2D(x, y);
                        }
                        catch(NumberFormatException ex)
                        {
                            return null;
                        }
                        catch(IndexOutOfBoundsException ex)
                        {
                            return null;
                        }
                        ++i;
                    }
                    if(offset == null)
                    {
                        return new Boundary(out);
                    }
                    else
                    {
                        return new Boundary(out, offset);
                    }
                }
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("File \"" + file + "\" could not be found.");
        }
        catch(IOException ex)
        {
            System.out.println("File \"" + file + "\" could not be opened.");
        }
        return  null;
    }
}
