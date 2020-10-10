package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

public class LifetimeLimitedGO extends GameObject {

    private long bornAt = System.currentTimeMillis();

    public long getAge() {
        return System.currentTimeMillis() - this.bornAt;
    }
}
