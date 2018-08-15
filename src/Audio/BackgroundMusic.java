/**
 * Created by William Davis on 14/03/2016.
 */
package Audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BackgroundMusic implements Runnable
{
    /**
     * The loops instance variable is used to store the loaded list of clips that can be picked from for the normal background music.
     */
    private List<Clip> loops;
    /**
     * The boss instance variable is used to store the loaded list of clips that can be picked from for the boss background music and the transitions.
     */
    private List<Clip> boss;
    /**
     * The running instance variable is used to keep the background music thread running. Setting it to false halts the thread.
     */
    private boolean running;
    /**
     * The mode instance variable is used to track if the music is in normal mode or boss mode.
     */
    private boolean mode;

    /**
     * The loopNames class variable is used to store the list of file names that should be loaded as the normal background music.
     */
    private static String[] loopNames = {
        "Loop1.wav",
        "Loop2.wav",
        "Loop3.wav",
        "Loop4.wav",
    };

    /**
     * The bossNames class variable is used to store the list of files that should be loaded as the boss background music.
     */
    private static String[] bossNames = {
        "Bossintro.wav",
        "Bossloop.wav",
        "Bossloop2.wav",
        "Bossloop3.wav",
        "Bossoutro.wav"
    };

    /**
     * The BackgroundMusic constructor is used to load the the two lists of files into the lists of clips.
     */
    public BackgroundMusic()
    {
        this.loops = new LinkedList<Clip>();
        for(String s: loopNames)
        {
            try
            {
                AudioInputStream ain = AudioSystem.getAudioInputStream(this.getClass().getResource("/Audio/Music/" + s));
                Clip c = AudioSystem.getClip();
                c.open(ain);
                this.loops.add(c);
                InputStream sinA = this.getClass().getResourceAsStream("/Audio/Music/" + s);
                AudioInputStream ainA = AudioSystem.getAudioInputStream(this.getClass().getResource("/Audio/Music/" + s));
                Clip cA = AudioSystem.getClip();
                cA.open(ainA);
                this.loops.add(cA);
            }
            catch(LineUnavailableException ex)
            {
                System.out.println("No clip available for file \"/Audio/Music/" + s + "\"");
            }
            catch(UnsupportedAudioFileException ex)
            {
                System.out.println("File \"/Audio/Music/" + s + "\" is an invalid format.");
            }
            catch(IOException ex)
            {
                System.out.println("File \"/Audio/Music/" + s + "\" could not be opened.");
            }
        }

        this.boss = new LinkedList<Clip>();
        int i = 0;
        for(String s: bossNames)
        {
            try
            {
                AudioInputStream ain = AudioSystem.getAudioInputStream(this.getClass().getResource("/Audio/Music/" + s));
                Clip c = AudioSystem.getClip();
                c.open(ain);
                this.boss.add(c);
                if(0 < i && i < bossNames.length - 1)
                {
                    AudioInputStream ainA = AudioSystem.getAudioInputStream(this.getClass().getResource("/Audio/Music/" + s));
                    Clip cA = AudioSystem.getClip();
                    cA.open(ainA);
                    this.boss.add(cA);
                }
            }
            catch(LineUnavailableException ex)
            {}
            catch(UnsupportedAudioFileException ex)
            {
                System.out.println("File \"/Audio/Music/" + s + "\" is an invalid format.");
            }
            catch(IOException ex)
            {
                System.out.println("File \"/Audio/Music/" + s + "\" could not be opened.");
            }
            ++i;
        }
    }

    /**
     * The start instance method is used to start a new thread of the BackgroundMusic class.
     */
    public void start()
    {
        new Thread(this, "Music").start();
    }

    /**
     * The run instance method is used to play suitable sounds based on the mode set back to back to create a constant background music track.
     */
    public void run()
    {
        Random r = new Random();
        int selected = -1;
        int pos = 0;
        Clip active = null;
        long length = 0;
        this.running = true;
        while(this.running)
        {
            if(active == null)
            {
                if(this.mode)
                {
                    if(pos != 0)
                    {
                        int ra = r.nextInt(this.boss.size() - 2) + 1;
                        if(ra == pos)
                        {
                            if(ra == this.boss.size() - 2)
                            {
                                --ra;
                            }
                            else
                            {
                                ++ra;
                            }
                        }
                        pos = ra;
                    }
                    active = this.boss.get(pos);
                    this.boss.get(pos).setFramePosition(0);
                    length = this.boss.get(pos).getMicrosecondLength();
                    this.boss.get(pos).start();
                    if(pos == 0)
                    {
                        ++pos;
                    }
                }
                else
                {
                    if(pos != 0)
                    {
                        pos = this.boss.size() - 1;
                        active = this.boss.get(pos);
                        this.boss.get(pos).setFramePosition(0);
                        length = this.boss.get(pos).getMicrosecondLength();
                        this.boss.get(pos).start();
                        pos = 0;
                    }
                    else
                    {
                        int ra = r.nextInt(this.loops.size());
                        if(ra == selected)
                        {
                            if(ra == this.loops.size() - 1)
                            {
                                --ra;
                            }
                            else
                            {
                                ++ra;
                            }
                        }
                        active = this.loops.get(ra);
                        selected = ra;
                        this.loops.get(ra).setFramePosition(0);
                        length = this.loops.get(ra).getMicrosecondLength();
                        this.loops.get(ra).start();
                    }
                }
            }
            else
            {
                if(length - active.getMicrosecondPosition() < 50000)
                {
                    active = null;
                }
            }
        }
    }

    /**
     * The stop instance method is used to stop the music.
     */
    public void stop()
    {
        this.running = false;
    }

    /**
     * The bossActive instance method is used to check if the background music playing is the boss mode music.
     * @return - True if playing boss music. False otherwise.
     */
    public boolean bossActive()
    {
        return this.mode;
    }

    /**
     * The activateBoss instance method is used to set the music mode to boss mode.
     */
    public void activateBoss()
    {
        this.mode = true;
    }

    /**
     * The deactivateBoss instance method is used to set the music mode to normal mode.
     */
    public void deactivateBoss()
    {
        this.mode = false;
    }
}
