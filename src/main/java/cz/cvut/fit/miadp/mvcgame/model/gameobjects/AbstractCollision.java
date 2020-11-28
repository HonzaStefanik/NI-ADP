package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractCollision extends LifetimeLimitedGO implements Cloneable {

    protected AbstractCollision(Position position) {
        super(position);
    }

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        // TODO compliant with UML diagram for lab 03, visitor behaviour will probably be defined in later lectures
    }

    @Override
    public AbstractCollision clone() throws CloneNotSupportedException {
        return (AbstractCollision) super.clone();
    }
}
