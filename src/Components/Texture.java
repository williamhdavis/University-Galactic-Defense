/**
 * Created by William Davis on 16/02/2016.
 */
package Components;

import Data.Boundary;
import Data.Config;
import Data.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class Texture
{
    /**
     * The offset instance variable is used to store the offset to be applied to the texture data.
     */
    private Vector2D offset;
    /**
     * The image instance variable is used to store the image to be drawn to the game window.
     */
    private Image image;
    /**
     * The simple instance variable is used to store the simple collision boundary data for the texture.
     */
    private Boundary simple;
    /**
     * The complex instance variable is used to store the complex collision boundary data for the texture.
     */
    private Boundary complex;

    /**
     * The Texture constructor is used to create a new texture data set.
     * @param location - The path to the image file.
     * @param offset - The offset to be applied to the texture.
     * @param simple - The simple collision boundary data.
     * @param complex - The complex collision boundary data.
     */
    public Texture(String location, Vector2D offset, Boundary simple, Boundary complex)
    {
        this.offset = offset;
        this.simple = simple;
        this.complex = complex;
        if(this.offset != null && this.simple != null)
        {
            this.simple.addOffset(this.offset);
        }
        if(this.offset != null && this.complex != null)
        {
            this.complex.addOffset(this.offset);
        }
        try
        {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Textures/" + location));
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println("File \"" + location + "\" could not be found.");
        }
        catch(IOException ex)
        {
            System.out.println("File \"" + location + "\" could not be opened.");
        }
    }

    /**
     * The getBounds instance variable is used to get a collision boundary data set.
     * @param simple - If the collision set should be the simple set.
     * @return - The collision boundary data set.
     */
    public Boundary getBounds(boolean simple)
    {
        if(simple)
        {
            return this.simple;
        }
        else
        {
            return this.complex;
        }
    }

    /**
     * The draw instance method is used to draw the texture to a graphics object.
     * @param g - The graphics object to draw to.
     */
    public void draw(Graphics2D g)
    {
        AffineTransform at = g.getTransform();
        if(this.offset != null)
        {
            g.translate(this.offset.getX(), this.offset.getY());
        }
        g.drawImage(this.image, 0, 0, null);
        g.setTransform(at);
    }

    /**
     * The guiTextures class variable is used to store the texture data for the GUI overlays.
     */
    public static Texture[] guiTextures = {
        new Texture("GUI/Layout.png", null, null, null)
    };

    /**
     * The stationTextures class variable is used to store the texture data for the segments that make up a space station.
     */
    public static Texture[][] stationTextures = {
        {
            new Texture("Station/Top/Entry/1.png", null,
                Boundary.load("stationTop", "entry1", true), null
            ),
            new Texture("Station/Top/Corridor/1.png", null,
                Boundary.load("stationTop", "corridor1", true), null
            ),
            new Texture("Station/Top/Exit/1.png", null,
                Boundary.load("stationTop", "exit1", true), null
            ),
            new Texture("Station/Top/Entry/2.png", null,
                Boundary.load("stationTop", "entry2", true), null
            ),
            new Texture("Station/Top/Corridor/2.png", null,
                Boundary.load("stationTop", "corridor2", true), null
            ),
            new Texture("Station/Top/Exit/2.png", null,
                Boundary.load("stationTop", "exit2", true), null
            ),
            new Texture("Station/Top/Entry/3.png", null,
                Boundary.load("stationTop", "entry3", true), null
            ),
            new Texture("Station/Top/Exit/3.png", null,
                Boundary.load("stationTop", "exit3", true), null
            ),
        },
        {
            new Texture("Station/Bottom/Entry/1.png", new Vector2D(0, Config.HEIGHT - 72),
                Boundary.load("stationBottom", "entry1", true), null
            ),
            new Texture("Station/Bottom/Corridor/1.png", new Vector2D(0, Config.HEIGHT - 72),
                Boundary.load("stationBottom", "corridor1", true), null
            ),
            new Texture("Station/Bottom/Exit/1.png", new Vector2D(0, Config.HEIGHT - 72),
                Boundary.load("stationBottom", "exit1", true), null
            ),
            new Texture("Station/Bottom/Entry/2.png", new Vector2D(0, Config.HEIGHT - 144),
                Boundary.load("stationBottom", "entry2", true), null
            ),
            new Texture("Station/Bottom/Corridor/2.png", new Vector2D(0, Config.HEIGHT - 144),
                Boundary.load("stationBottom", "corridor2", true), null
            ),
            new Texture("Station/Bottom/Exit/2.png", new Vector2D(0, Config.HEIGHT - 144),
                Boundary.load("stationBottom", "exit2", true), null
            ),
            new Texture("Station/Bottom/Entry/3.png", new Vector2D(0, Config.HEIGHT - 144),
                Boundary.load("stationBottom", "entry3", true), null
            ),
            new Texture("Station/Bottom/Exit/3.png", new Vector2D(0, Config.HEIGHT - 144),
                Boundary.load("stationBottom", "exit3", true), null
            ),
        },
        {
            new Texture("Station/Background/Entry/1.png", null, null, null),
            new Texture("Station/Background/Corridor/1.png", null, null, null),
            new Texture("Station/Background/Exit/1.png", null, null, null),
            new Texture("Station/Background/Corridor/3.png", null, null, null),
        }

    };

    /**
     * The shipBulletSpawnOffsets class variable is used to store the bullet spawn distance for each ship type.
     */
    public static int[] shipBulletSpawnOffsets = {
        18, // Player
        0,  // Mine
        10, // Enemy 1
        24,  // Turret
    };

    /**
     * The shipTextures class variable is used to store the texture data for each ship.
     */
    public static Texture[][] shipTextures = {
        {
            new Texture("Ship/Player/Ship.png", new Vector2D(-29, -40),
                Boundary.load("ships", "player", true),
                Boundary.load("ships", "player", false)
            ),
            new Texture("Ship/Player/Bottom.png", new Vector2D(-29, -40), null, null),
            new Texture("Ship/Player/Up.png", new Vector2D(-29, -40), null, null),
            new Texture("Ship/Player/Left Bottom.png", new Vector2D(-29, -40), null, null),
            new Texture("Ship/Player/Left Up.png", new Vector2D(-29, -40), null, null),
            new Texture("Ship/Player/Right Bottom.png", new Vector2D(-29, -40), null, null),
            new Texture("Ship/Player/Right Up.png", new Vector2D(-29, -40), null, null),
        },
        {
            new Texture("Ship/Mine.png", new Vector2D(-14, -14),
                Boundary.load("ships", "mine", true),
                Boundary.load("ships", "mine", false)
            ),
        },
        {
            new Texture("Ship/Enemy 1/Ship.png", new Vector2D(-28, -15),
                Boundary.load("ships", "enemy1", true),
                Boundary.load("ships", "enemy1", false)
            ),
            new Texture("Ship/Enemy 1/Bottom Left.png", new Vector2D(-28, -15), null, null),
            new Texture("Ship/Enemy 1/Bottom Right.png", new Vector2D(-28, -15), null, null),
            new Texture("Ship/Enemy 1/Top Left.png", new Vector2D(-28, -15), null, null),
            new Texture("Ship/Enemy 1/Top Right.png", new Vector2D(-28, -15), null, null),
        },
        {
            new Texture("Ship/Turret/Ship.png", new Vector2D(-14, -21),
                Boundary.load("ships", "turret", true),
                Boundary.load("ships", "turret", false)
            ),
            new Texture("Ship/Turret/Frame 1 Norm.png", new Vector2D(-14, -9), null, null),
            new Texture("Ship/Turret/Frame 1 Dest.png", new Vector2D(-14, -9), null, null),
        }
    };

    /**
     * The asteroidOffsets class variable is used to set how far from the edge of an asteroid belt the asteroid should spawn.
     */
    public static int[] asteroidOffsets = {
        25, // Small
        60, // Medium
        93, // Large
    };

    /**
     * The asteroidTextures class variable is used to store the texture data for the asteroids.
     */
    public static Texture[] asteroidTextures = {
        new Texture("Asteroid/1.png", new Vector2D(-100, -100),
            Boundary.load("asteroids", "1", true),
            Boundary.load("asteroids", "1", false)
        ),
        new Texture("Asteroid/2.png", new Vector2D(-100, -100),
            Boundary.load("asteroids", "2", true),
            Boundary.load("asteroids", "2", false)
    ),
        new Texture("Asteroid/3.png", new Vector2D(-100, -100),
            Boundary.load("asteroids", "3", true),
            Boundary.load("asteroids", "3", false)
        ),
    };

    /**
     * The powerupTextures class variable is used to store the texture data for the powerups.
     */
    public static Texture[] powerupTextures = {
        new Texture("Powerup/1.png", new Vector2D(-20, -15),
            Boundary.load("powerups", "normal", true),
            Boundary.load("powerups", "normal", false)
        ),
        new Texture("Powerup/2.png", new Vector2D(-20, -15),
            Boundary.load("powerups", "normal", true),
            Boundary.load("powerups", "normal", false)
        ),
        new Texture("Powerup/3.png", new Vector2D(-20, -15),
            Boundary.load("powerups", "normal", true),
            Boundary.load("powerups", "normal", false)
        ),
        new Texture("Powerup/4.png", new Vector2D(-20, -15),
            Boundary.load("powerups", "normal", true),
            Boundary.load("powerups", "normal", false)
        ),
        new Texture("Powerup/5.png", new Vector2D(-20, -15),
            Boundary.load("powerups", "normal", true),
            Boundary.load("powerups", "normal", false)
        ),
    };

    /**
     * The powerupMenuTextures class variable is used to store the texture data for the powerup gui items.
     */
    public static Texture[] powerupMenuTextures = {
        new Texture("GUI/Powerup/1.png", null, null, null),
        new Texture("GUI/Powerup/2.png", null, null, null),
        new Texture("GUI/Powerup/3.png", null, null, null),
        new Texture("GUI/Powerup/4.png", null, null, null),
        new Texture("GUI/Powerup/5.png", null, null, null),
        new Texture("GUI/Powerup/6.png", null, null, null),
    };

    /**
     * The bulletTextures class variable is used to store the texture data for the bullets.
     */
    public static Texture[] bulletTextures = {
        new Texture("Ship/Bullet/Standard.png", new Vector2D(-2, -4),
            Boundary.load("bullets", "norm", true),
            Boundary.load("bullets", "norm", false)
        ),
        new Texture("Ship/Bullet/Splitting.png", new Vector2D(-2, -4),
            Boundary.load("bullets", "norm", true),
            Boundary.load("bullets", "norm", false)
        ),
    };

    /**
     * The bulletMenuTextures class variable is used to store the texture data for the bullet gui items.
     */
    public static Texture[] bulletMenuTextures = {
        new Texture("GUI/Bullet/1.png", null, null, null),
        new Texture("GUI/Bullet/2.png", null, null, null)
    };

    /**
     * The largeExplosion class variable is used to store the texture data for the explosions.
     */
    public static Texture[] largeExplosion = {
        new Texture("Explosion/1.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large1", true),
            Boundary.load("explosion", "large1", false)
        ),
        new Texture("Explosion/2.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large1", true),
            Boundary.load("explosion", "large1", false)
        ),
        new Texture("Explosion/3.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large3", true),
            Boundary.load("explosion", "large3", false)
        ),
        new Texture("Explosion/4.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large4", true),
            Boundary.load("explosion", "large4", false)
        ),
        new Texture("Explosion/5.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large5", true),
            Boundary.load("explosion", "large5", false)
        ),
        new Texture("Explosion/6.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large6", true),
            Boundary.load("explosion", "large6", false)
        ),
        new Texture("Explosion/7.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large7", true),
            Boundary.load("explosion", "large7", false)
        ),
        new Texture("Explosion/8.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large8", true),
            Boundary.load("explosion", "large8", false)
        ),
        new Texture("Explosion/9.png", new Vector2D(-60, -60),
            Boundary.load("explosion", "large9", true),
            Boundary.load("explosion", "large9", false)
        ),
        new Texture("Explosion/10.png", new Vector2D(-60, -60), null, null),
        new Texture("Explosion/11.png", new Vector2D(-60, -60), null, null),
        new Texture("Explosion/12.png", new Vector2D(-60, -60), null, null),
        new Texture("Explosion/13.png", new Vector2D(-60, -60), null, null),
        new Texture("Explosion/14.png", new Vector2D(-60, -60), null, null),
    };
}