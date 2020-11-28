package cz.cvut.fit.miadp.mvcgame.config;

public class MvcGameConfig {
    public static final int MAX_X = 1280;
    public static final int MAX_Y = 720;
    public static final int GAME_INFO_X = 10;
    public static final int GAME_INFO_Y = 20;
    public static final int MOVE_STEP = 2;
    public static final int CANNON_POS_X = 50;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final int TIME_TICK_PERIOD = 1000;
    public static final int POWER_STEP = 1;
    public static final int INIT_POWER = 10;
    public static final int ENEMY_COUNT = 1;
    public static final int ENEMY_HIT_BOX = 30;
    public static final int COLLISION_DISPLAY_TIME_SECONDS = 5;
    public static final double INIT_ANGLE = 0;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final double GRAVITY = 9.8;
    public static final String ENEMY = "images/enemy";
    public static final String CANNON = "images/cannon.png";
    public static final String MISSILE = "images/missile.png";
    public static final String COLLISION = "images/collision.png";
    private MvcGameConfig() {
    }
}