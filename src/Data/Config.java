/**
 * Created by William on 10/02/2016.
 */
package Data;

import java.awt.*;

public class Config
{
    /**
     * The UPDATE_PER_SECOND class variable is used to set the rate that the game refreshes.
     */
    public static final double UPDATE_PER_SECOND = 0.01;
    /**
     * The UPDATE_MAX_SKIP class variable is used to set the number of updates that can be skipped before a redraw is forced.
     */
    public static final int UPDATE_MAX_SKIP = 5;
    /**
     * The UPDATE_TIME class variable is used to store the next frame update time.
     */
    public static long UPDATE_TIME = 0;
    /**
     * The UPDATE_NOW class variable is used to store the current frame update time.
     */
    public static long UPDATE_NOW = 0;
    /**
     * The UPDATE_ANIMATION_DELAY class variable is used to set the delay between animation frames.
     */
    public static final int UPDATE_ANIMATION_DELAY = 40;
    /**
     * The UPDATE_HIT_DELAY class variable is used to set the minimum delay between hit calls on an object.
     */
    public static final int UPDATE_HIT_DELAY = 10;

    /**
     * The WIDTH class variable is used to set the width of the game window.
     */
    public static final int WIDTH = 1280;
    /**
     * The HEIGHT class variable is used to set the height of the game window.
     */
    public static final int HEIGHT = 720;
    /**
     * The FRAME_SIZE class variable is used to store the dimension of the game window.
     */
    public static final Dimension FRAME_SIZE = new Dimension(WIDTH, HEIGHT);

    /**
     * The STAR_DENSITY class variable is used to set the percentage of pixels that should be stars.
     */
    public static final int STAR_DENSITY = 1;
    /**
     * The STAR_DISTRIBUTION class variable is used to set the separation of star lines.
     */
    public static final int STAR_DISTRIBUTION = 10;

    /**
     * The STATIC_POSITION class variable is used to store the current position along the game board.
     */
    public static final Vector2D STATIC_POSITION = new Vector2D(0, 0);
    /**
     * The STATIC_VELOCITY class variable is used to store the current velocity of the game board.
     */
    public static final Vector2D STATIC_VELOCITY = new Vector2D(0, 0);

    /**
     * The BUTTON_WIDTH class variable is used to set the width of menu buttons.
     */
    public static final int BUTTON_WIDTH = 380;
    /**
     * The BUTTON_HEIGHT class variable is used to set the height of menu buttons.
     */
    public static final int BUTTON_HEIGHT = 80;
    /**
     * The BUTTON_BORDER class variable is used to set the thickness of the border around menu buttons.
     */
    public static final int BUTTON_BORDER = 5;
    /**
     * The BUTTON_TEXT_SCALE class variable is used to set the scale of the text on menu buttons.
     */
    public static final int BUTTON_TEXT_SCALE = 5;

    /**
     * The SHIP_START_X_VELOCITY class variable is used to set the level entry speed of the player ship.
     */
    public static final int SHIP_START_X_VELOCITY = 200;
    /**
     * The SHIP_START_LIVES class variable is used to set the lives the player starts with.
     */
    public static final int SHIP_START_LIVES = 3;
    /**
     * The SHIP_DRAG class variable is used to set the rate ships slow down without thrust.
     */
    public static final double SHIP_DRAG = 62;
    /**
     * The SHIP_ROT_DRAG class variable is used to set the rate ships rotation slows down without thrust.
     */
    public static final double SHIP_ROT_DRAG = (Math.PI / 64) / 8;
    /**
     * The SHIP_HIDE_BORDER class variable is used to set the distance ships have to be off screen before they are removed from the draw list.
     */
    public static final int SHIP_HIDE_BORDER = 50;

    /**
     * The BACKGROUND_PATCH class variable is used to set the extra background length added.
     */
    public static final int BACKGROUND_PATCH = 50;
    /**
     * The BACKGROUND_NUMBER_DEFAULT class variable is used to set the number of panels to use to make a level initially.
     */
    public static final int BACKGROUND_NUMBER_DEFAULT = 3;
    /**
     * The BACKGROUND_NUMBER_FUZZING class variable is used to set the random value to vary the number of panels by per level.
     */
    public static final int BACKGROUND_NUMBER_FUZZING = 2;

    /**
     * The SEGMENT_RATIO class variable is used to set how large a segment is compared to height and width.
     */
    public static final int SEGMENT_RATIO = 10;
    /**
     * The SEGMENT_WIDTH class variable is used to store the width of a station segment.
     */
    public static final int SEGMENT_WIDTH = WIDTH / SEGMENT_RATIO;
    /**
     * The SEGMENT_HEIGHT class variable is used to store the height of a station segment.
     */
    public static final int SEGMENT_HEIGHT = HEIGHT / SEGMENT_RATIO;

    /**
     * The STATION_RATE class variable is used to store the number of levels between station levels.
     */
    public static final int STATION_RATE = 4;

    /**
     * The BULLET_LIVE_TIME class variable is used to store the time a bullet should live for.
     */
    public static final int BULLET_LIVE_TIME = 25;

    /**
     * The MINE_DISTRIBUTION class variable is used to set the seperation between mine lines.
     */
    public static final int MINE_DISTRIBUTION = 120;
    /**
     * The MINE_PATH_WIDTH class variable is used to set the minimum gap between mines along the ships intended path.
     */
    public static final int MINE_PATH_WIDTH = 80;
    /**
     * The MINE_PATH_CHANGE class variable is used to set how often the intended path should change.
     */
    public static final int MINE_PATH_CHANGE = 500;
    /**
     * The MINE_PATH_EDGE_INSET class variable is used to set how close to the edge of the game screen the intended path should get.
     */
    public static final int MINE_PATH_EDGE_INSET = 100;
    /**
     * The MINE_DEGRID class variable is used to set the randomness of the position of mines.
     */
    public static final int MINE_DEGRID = 35;

    /**
     * The AI_BASIC_BOUND_INNER class variable is used to set the closest an Basic AI ship wants to get to the target.
     */
    public static final int AI_BASIC_BOUND_INNER = 120;
    /**
     * The AI_BASIC_BOUND_OUTER class variable is used to set the furthest an Basic AI ship wants to get from the target.
     */
    public static final int AI_BASIC_BOUND_OUTER = 200;
    /**
     * The AI_BASIC_SPOT_TOLERANCE class variable is used to set how close a Basic AI ship must be to its target point to start shooting.
     */
    public static final int AI_BASIC_SPOT_TOLERANCE = 25;

    /**
     * The ASTEROID_MIN_SPEED class variable is used to set the minimum movement speeds for the asteroid sizes.
     */
    public static final int[] ASTEROID_MIN_SPEED = {100, 50, 20};
    /**
     * The ASTEROID_MAX_SPEED class variable is used to set the maximum movement speeds for the asteroid sizes.
     */
    public static final int[] ASTEROID_MAX_SPEED = {200, 80, 40};

    /**
     * The KEY_BINDS class variable is used to set the key codes used for specific actions.
     */
    //                               ^   V   <   >   r<  r>  sh, se
    public static int[] KEY_BINDS = {68, 65, 87, 83, 81, 69, 32, 10};
    //public static int[] KEY_BINDS = {38, 40, 65, 68, 37, 39, 32, 10};
}