package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractCollision extends LifetimeLimitedGO {

    protected AbstractCollision(Position position) {
        super(position);
    }

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        visitor.visitCollision(this);
    }

}
