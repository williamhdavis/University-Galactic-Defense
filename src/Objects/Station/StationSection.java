/**
 * Created by William Davis on 13/02/2016.
 */
package Objects.Station;

import Controls.AI.TurretAI;
import Data.Config;
import Data.Vector2D;
import Objects.Bullet.BulletPickup;
import Objects.Bullet.GenericBullet;
import Objects.GameObject;
import Objects.Powerup.Powerup;
import Objects.Powerup.PowerupData;
import Objects.Ship.GenericShip;
import Objects.Ship.Turret;
import Objects.Static.StaticPart;
import Components.Texture;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class StationSection extends StaticPart
{
    /**
     * The exits instance variable is used to store the exit data for the section.
     */
    private int[] exits;
    /**
     * The width instance variable is used to store the width of the section.
     */
    private int width;
    /**
     * The objects instance variable is used to store all the game objects that are in the section.
     */
    private List<GameObject> objects;

    /**
     * The StationSection constructor is used to create a new station section.
     * @param player - The player ship.
     * @param position - The position of the section on the level.
     * @param exits - The exit values for the section.
     * @param width - The width of the section.
     * @param textures - The textures the section uses.
     * @param spawns - The spawn points in the section.
     * @param difficulty - The difficulty of the section.
     */
    public StationSection(GenericShip player, Vector2D position, int[] exits, int width, Texture[] textures, StationSpawn[] spawns, int difficulty)
    {
        super(position, textures);
        this.exits = exits;
        this.width = width;
        this.objects = new LinkedList<GameObject>();
        if(player != null)
        {
            if(spawns != null)
            {
                if(spawns.length > 0)
                {
                    Random r = new Random();
                    for(StationSpawn s: spawns)
                    {
                        if(r.nextInt(100) < s.getProbability())
                        {
                            if(s.getType().equals("turret"))
                            {
                                int[] modeData = s.getModeData();
                                this.objects.add(new Turret(new TurretAI(player, modeData[0] == 0), new Vector2D(position).add(s.getPosition()), modeData[0] == 0, difficulty, 0, 2, modeData[1]));
                            }
                            else if(s.getType().equals("powerup"))
                            {
                                int pow = r.nextInt(BulletPickup.TOTAL_TYPES + PowerupData.all[0].length);
                                if(pow < BulletPickup.TOTAL_TYPES)
                                {
                                    this.objects.add(new BulletPickup(new Vector2D(position).add(s.getPosition()), pow));
                                }
                                else
                                {
                                    this.objects.add(new Powerup(new Vector2D(position).add(s.getPosition()), pow - BulletPickup.TOTAL_TYPES));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * The StationSection constructor is used to copy a station section.
     * @param section - The section to copy.
     */
    public StationSection(StationSection section)
    {
        super(section.getPosition(), section.textures);
        this.exits = section.exits;
        this.width = section.width;
        this.objects = new LinkedList<GameObject>();
    }

    /**
     * The canHit instance method is used to check if an object can be hit by station sections.
     * @param object - The object to check.
     * @return - True if the object can hit the bullet. False otherwise.
     */
    @Override
    public boolean canHit(GameObject object)
    {
        if(object instanceof GenericShip)
        {
            return true;
        }
        else if(object instanceof GenericBullet)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * The getObjects instance method is used to get the list of objects in the section.
     * @return - The list of objects.
     */
    public List<GameObject> getObjects()
    {
        return this.objects;
    }

    /**
     * The getExit instance method is used to get the exit values for the section.
     * @return - The exit values.
     */
    public int[] getExit()
    {
        return this.exits;
    }

    /**
     * The getWidth instance method is used to get the width of the section.
     * @return - The section width.
     */
    public int getWidth()
    {
        return this.width;
    }

    /**
     * The draw instance method is used to draw the station section to a graphics object.
     * @param g - The graphics object.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        g.translate(Config.STATIC_POSITION.getX() + this.position.getX(), Config.STATIC_POSITION.getY());
        for(Texture t: this.textures)
        {
            t.draw(g);
        }
        g.setTransform(at);
    }
}
