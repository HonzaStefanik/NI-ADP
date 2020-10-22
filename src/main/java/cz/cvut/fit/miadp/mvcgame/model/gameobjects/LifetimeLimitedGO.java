package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.visitor.GameObjectRender;

public class LifetimeLimitedGO extends GameObject {

    private long bornAt = System.currentTimeMillis();

    public long getAge() {
        return System.currentTimeMillis() - this.bornAt;
    }

    @Override
    public void acceptVisitor(GameObjectRender gameObjectRender) {
//TODO
    }
}
