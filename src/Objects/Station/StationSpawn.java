/**
 * Created by William Davis on 09/03/2016.
 */
package Objects.Station;

import Data.Vector2D;

public class StationSpawn
{
    /**
     * The type instance variable is used to store the spawn type.
     */
    private String type;
    /**
     * The position instance variable is used to store the spawn location.
     */
    private Vector2D position;
    /**
     * The modeData instance variable is used to store any extra spawn data.
     */
    private int[] modeData;
    /**
     * The probability instance variable is used to store the chance of the item being spawned.
     */
    private int probability;

    /**
     * The StationSpawn constructor is used to create a new spawn point.
     * @param type - The spawn type.
     * @param position - The spawn location.
     * @param modeData - The spawn extra data.
     * @param probability - The probability of being spawned.
     */
    public StationSpawn(String type, Vector2D position, int[] modeData, int probability)
    {
        this.type = type;
        this.position = position;
        this.modeData = modeData;
        this.probability = probability;
    }

    /**
     * The getType instance method is used to get the spawn type.
     * @return - The spawn type.
     */
    public String getType()
    {
        return this.type;
    }

    /**
     * The getPosition instance method is used to get the spawn position.
     * @return - The position.
     */
    public Vector2D getPosition()
    {
        return this.position;
    }

    /**
     * The getModeData instance method is used to get any extra mode data.
     * @return - The extra data.
     */
    public int[] getModeData()
    {
        return this.modeData;
    }

    /**
     * The getProbability instance method is used to get the probability of being spawned.
     * @return - The probability.
     */
    public int getProbability()
    {
        return this.probability;
    }
}
