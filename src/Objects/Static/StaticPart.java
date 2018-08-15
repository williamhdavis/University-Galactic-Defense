/**
 * Created by William on 10/02/2016.
 */
package Objects.Static;

import Components.Texture;
import Data.Vector2D;
import Objects.GameObject;

public abstract class StaticPart extends GameObject
{
    /**
     * The StaticPart constructor is used to create a new static part.
     * @param position - The position of the part.
     * @param textures - The textures to use to draw the part.
     */
    public StaticPart(Vector2D position, Texture[] textures)
    {
        super(position, null, null, 0, textures);
    }

    /**
     * The SStaticPart constructor is used to create a new static part.
     */
    public StaticPart()
    {
        this(null, null);
    }

    /**
     * The isDead instance method is used to check if the static part can be deleted from memory.
     * @return - True if the part can be deleted. False otherwise.
     */
    @Override
    public boolean isDead()
    {
        return false;
    }

    /**
     * The canHit instance method is used to check if an object can be hit by a static part.
     * @param object - The object to check.
     * @return - True if the object can hit the bullet. False otherwise.
     */
    public boolean canHit(GameObject object)
    {
        return false;
    }

    /**
     * The hit instance method is used to hit the static part.
     * @return - The score gained.
     */
    @Override
    public int hit()
    {
        return 0;
    }

    /**
     * The update instance method is used to update the static part.
     */
    @Override
    public void update()
    {}
}
