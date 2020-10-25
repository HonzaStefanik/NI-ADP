package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.visitor.GameObjectRender;

public abstract class LifetimeLimitedGO extends GameObject {

    private long bornAt = System.currentTimeMillis();

    public long getAge() {
        return System.currentTimeMillis() - this.bornAt;
    }

}
