/**
 * Created by William Davis on 13/02/2016.
 */
package Objects.Station;

import Components.Texture;
import Data.Vector2D;
import Objects.Ship.GenericShip;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StationSectionData
{
    /**
     * The type instance variable is used to store the section type.
     */
    private String type;
    /**
     * The entry instance variable is used to store the section entry value.
     */
    private int entry;
    /**
     * The exit instance variable is used to store the section exit value.
     */
    private int exit;
    /**
     * The width instance variable is used to store the section width value.
     */
    private int width;
    /**
     * The difficulty instance variable is used to store the section difficulty value.
     */
    private int difficulty;
    /**
     * The textures instance variable is used to store the sections textures.
     */
    private Texture[] textures;
    /**
     * The spawns instance variable is used to store the sections spawn locations.
     */
    private StationSpawn[] spawns;

    /**
     * The StationSectionData constructor is used to create a new station section.
     * @param type - The section type.
     * @param entry - The section entry value.
     * @param exit - The section exit value.
     * @param width - The section width value.
     * @param difficulty - The section difficulty value.
     * @param textures - The sections textures.
     * @param spawns - The sections spawn locations.
     */
    public StationSectionData(String type, int entry, int exit, int width, int difficulty, Texture[] textures, StationSpawn[] spawns)
    {
        this.type = type;
        this.entry = entry;
        this.exit = exit;
        this.width = width;
        this.difficulty = difficulty;
        this.textures = textures;
        this.spawns = spawns;
    }

    /**
     * The getType instance variable is used to get the section type.
     * @return - The section type.
     */
    public String getType()
    {
        return this.type;
    }

    /**
     * The getEntry() instance variable is used to get the section entry value.
     * @return - The section entry value.
     */
    public int getEntry()
    {
        return this.entry;
    }

    /**
     * The getExit() instance variable is used to get the section exit value.
     * @return - The section exit value.
     */
    public int getExit()
    {
        return this.exit;
    }

    /**
     * The getWidth instance variable is used to get the section width value.
     * @return - The section width value.
     */
    public int getWidth()
    {
        return this.width;
    }

    /**
     * The getTextures instance variable is used to get the sections textures.
     * @return - The sections textures.
     */
    public Texture[] getTextures()
    {
        return this.textures;
    }

    /**
     * The getSpawns instance variable is used to get the sections spawn locations.
     * @return - The sections spawn locations.
     */
    public StationSpawn[] getSpawns()
    {
        return this.spawns;
    }

    /**
     * The top class variable is used to store the list of parts for the top part of the station.
     */
    private static StationSectionData[] top = {
            // Odds are Top only.
            new StationSectionData("entry", 0, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[0][0],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 101), new int[]{0, 1}, 5),
                    new StationSpawn("turret", new Vector2D(128, 101), new int[]{0, 1}, 20),
                    new StationSpawn("turret", new Vector2D(205, 101), new int[]{0, 1}, 20),
            }),
            new StationSectionData("corridor", 1, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[0][1],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 101), new int[]{0, 1}, 20),
                    new StationSpawn("turret", new Vector2D(128, 101), new int[]{0, 1}, 20),
                    new StationSpawn("turret", new Vector2D(205, 101), new int[]{0, 1}, 20),
            }),
            new StationSectionData("exit", 1, 0, 256, 1, new Texture[]{
                    Texture.stationTextures[0][2],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 101), new int[]{0, 1}, 20),
                    new StationSpawn("turret", new Vector2D(128, 101), new int[]{0, 1}, 20),
                    new StationSpawn("turret", new Vector2D(205, 101), new int[]{0, 1}, 5),
            }),
            new StationSectionData("corridor", 1, 2, 256, 1, new Texture[]{
                    Texture.stationTextures[0][3],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 101), new int[]{0, 1}, 50),
            }),
            new StationSectionData("corridor", 2, 2, 256, 1, new Texture[]{
                    Texture.stationTextures[0][4],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 173), new int[]{0, 1}, 20),
                    new StationSpawn("turret", new Vector2D(128, 173), new int[]{0, 1}, 20),
                    new StationSpawn("turret", new Vector2D(205, 173), new int[]{0, 1}, 20),
            }),
            new StationSectionData("corridor", 2, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[0][5],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(205, 101), new int[]{0, 1}, 50),
            }),
            new StationSectionData("entry", 0, 2, 256, 1, new Texture[]{
                    Texture.stationTextures[0][6],
            }, null),
            new StationSectionData("exit", 2, 0, 256, 1, new Texture[]{
                    Texture.stationTextures[0][7],
            }, null),
    };

    /**
     * The bottom class variable is used to store the list of parts for the bottom part of the station.
     */
    private static StationSectionData[] bottom = {
            // Evens are Bottom only.
            new StationSectionData("entry", 0, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[1][0],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 619), new int[]{1, 1}, 5),
                    new StationSpawn("turret", new Vector2D(128, 619), new int[]{1, 1}, 20),
                    new StationSpawn("turret", new Vector2D(205, 619), new int[]{1, 1}, 20),
            }),
            new StationSectionData("corridor", 1, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[1][1],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 619), new int[]{1, 1}, 20),
                    new StationSpawn("turret", new Vector2D(128, 619), new int[]{1, 1}, 20),
                    new StationSpawn("turret", new Vector2D(205, 619), new int[]{1, 1}, 20),
            }),
            new StationSectionData("exit", 1, 0, 256, 1, new Texture[]{
                    Texture.stationTextures[1][2],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 619), new int[]{1, 1}, 20),
                    new StationSpawn("turret", new Vector2D(128, 619), new int[]{1, 1}, 20),
                    new StationSpawn("turret", new Vector2D(205, 619), new int[]{1, 1}, 5),
            }),
            new StationSectionData("corridor", 1, 2, 256, 1, new Texture[]{
                    Texture.stationTextures[1][3],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 619), new int[]{1, 1}, 50),
            }),
            new StationSectionData("corridor", 2, 2, 256, 1, new Texture[]{
                    Texture.stationTextures[1][4],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(51, 547), new int[]{1, 1}, 20),
                    new StationSpawn("turret", new Vector2D(128, 547), new int[]{1, 1}, 20),
                    new StationSpawn("turret", new Vector2D(205, 547), new int[]{1, 1}, 20),
            }),
            new StationSectionData("corridor", 2, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[1][5],
            }, new StationSpawn[]{
                    new StationSpawn("turret", new Vector2D(205, 619), new int[]{1, 1}, 50),
            }),
            new StationSectionData("entry", 0, 2, 256, 1, new Texture[]{
                    Texture.stationTextures[1][6],
            }, null),
            new StationSectionData("exit", 2, 0, 256, 1, new Texture[]{
                    Texture.stationTextures[1][7],
            }, null),
    };

    /**
     * The back class variable is used to store the list of parts for the background part of the station.
     */
    private static StationSectionData[] back = {
            new StationSectionData("entry", 0, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[2][0],
           }, null),
            new StationSectionData("exit", 1, 0, 256, 1, new Texture[]{
                    Texture.stationTextures[2][2],
            }, null),
            new StationSectionData("corridor", 1, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[2][1],
            }, null),
            new StationSectionData("corridor", 1, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[2][3],
            }, new StationSpawn[]{
                    new StationSpawn("powerup", new Vector2D(128, 360), new int[]{}, 10)
            }),
            new StationSectionData("corridor", 1, 1, 256, 1, new Texture[]{
                    Texture.stationTextures[2][1],
            }, null),
    };

    /**
     * The pick class method is used to get a new station section.
     * @param player - The player ship.
     * @param position - The position of the section on the level.
     * @param entries - The entry values for the section.
     * @param difficulty - The maximum difficulty for the section.
     * @return - The new station section.
     */
    public static StationSection pick(GenericShip player, Vector2D position, int[] entries, int difficulty)
    {
        return pick(player, position, entries, difficulty, null);
    }

    /**
     * The pick class method is used to get a new station section.
     * @param player - The player ship.
     * @param position - The position of the section on the level.
     * @param entries - The entry values for the section.
     * @param difficulty - The maximum difficulty for the section.
     * @param type - The type of part allowed to be picked.
     * @return - The new station section.
     */
    public static StationSection pick(GenericShip player, Vector2D position, int[] entries, int difficulty, String type)
    {
        return pick(player, position, entries, difficulty, type, true);
    }

    /**
     * The pick class method is used to get a new station section.
     * @param player - The player ship.
     * @param position - The position of the section on the level.
     * @param entries - The entry values for the section.
     * @param difficulty - The maximum difficulty for the section.
     * @param type - The type of part to be allowed or not allowed for picking.
     * @param typeAllowed - If the type is in allowed or not allowed mode.
     * @return - The new station section.
     */
    public static StationSection pick(GenericShip player, Vector2D position, int[] entries, int difficulty, String type, boolean typeAllowed)
    {
        Random r = new Random();
        List<StationSectionData> tempTop = new LinkedList<StationSectionData>();
        for(StationSectionData s : top)
        {
            if(s.getEntry() == entries[0])
            {
                if(type == null)
                {
                    tempTop.add(s);
                }
                else
                {
                    if((typeAllowed && s.getType().equals(type)) || (!typeAllowed && !s.getType().equals(type)))
                    {
                        tempTop.add(s);
                    }
                }
            }
        }
        List<StationSectionData> tempBottom = new LinkedList<StationSectionData>();
        for(StationSectionData s : bottom)
        {
            if(s.getEntry() == entries[1])
            {
                if(type == null)
                {
                    tempBottom.add(s);
                }
                else
                {
                    if((typeAllowed && s.getType().equals(type)) || (!typeAllowed && !s.getType().equals(type)))
                    {
                        tempBottom.add(s);
                    }
                }
            }
        }
        StationSectionData t = tempTop.get(r.nextInt(tempTop.size()));
        StationSectionData b = tempBottom.get(r.nextInt(tempBottom.size()));
        List<StationSectionData> tempBack = new LinkedList<StationSectionData>();
        for(StationSectionData s: back)
        {
            if(s.getType().equals(t.getType()))
            {
                tempBack.add(s);
            }
        }
        StationSectionData ba = tempBack.get(r.nextInt(tempBack.size()));

        int[] exits = new int[]{t.getExit(), b.getExit()};
        int width = t.getWidth();
        Texture[] textures = new Texture[t.getTextures().length + b.getTextures().length + ba.getTextures().length];
        int i = 0;
        while(i < ba.getTextures().length)
        {
            textures[i] = ba.getTextures()[i];
            ++i;
        }
        i = 0;
        while(i < t.getTextures().length)
        {
            textures[i + ba.getTextures().length] = t.getTextures()[i];
            ++i;
        }
        i = 0;
        while(i < b.getTextures().length)
        {
            textures[i + t.getTextures().length + ba.getTextures().length] = b.getTextures()[i];
            ++i;
        }
        StationSpawn[] spawns;
        if(t.getSpawns() == null && b.getSpawns() == null && ba.getSpawns() == null)
        {
            spawns = null;
        }
        else
        {
            int len = 0;
            int iOff = 0;
            int iOffE = 0;
            if(t.getSpawns() != null)
            {
                len += t.getSpawns().length;
            }
            if(b.getSpawns() != null)
            {
                len += b.getSpawns().length;
            }
            if(ba.getSpawns() != null)
            {
                len += ba.getSpawns().length;
            }
            spawns = new StationSpawn[len];
            if(t.getSpawns() != null)
            {
                i = 0;
                while(i < t.getSpawns().length)
                {
                    spawns[i] = t.getSpawns()[i];
                    ++i;
                }
                iOff = t.getSpawns().length;
            }
            if(b.getSpawns() != null)
            {
                iOffE = iOff + b.getSpawns().length;
                i = 0;
                while(i < b.getSpawns().length)
                {
                    spawns[i + iOff] = b.getSpawns()[i];
                    ++i;
                }
                iOff = iOffE;
            }
            if(ba.getSpawns() != null)
            {
                iOffE = iOff + ba.getSpawns().length;
                i = 0;
                while(i < ba.getSpawns().length)
                {
                    spawns[i + iOff] = ba.getSpawns()[i];
                    ++i;
                }
                iOff = iOffE;
            }
        }
        return new StationSection(player, position, exits, width, textures, spawns, difficulty);
    }
}
