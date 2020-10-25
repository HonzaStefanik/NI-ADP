package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractMissile extends GameObject {

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        visitor.visitMissile(this);
    }
}
