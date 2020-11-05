package cz.cvut.fit.miadp.mvcgame.config;

public class MvcGameConfig {
    public static final int MAX_X = 1280;
    public static final int MAX_Y = 720;
    public static final int MOVE_STEP = 2;
    public static final int CANNON_POS_X = 50;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final int TIME_TICK_PERIOD = 1000;
    public static final int POWER_STEP = 10;
    public static final int INIT_POWER = 10;
    public static final double INIT_ANGLE = 0;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final double GRAVITY = 9.8;

    private MvcGameConfig() {
    }
}