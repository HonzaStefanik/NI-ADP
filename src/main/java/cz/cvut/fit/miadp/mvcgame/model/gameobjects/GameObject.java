package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class GameObject {

    protected Position position;

    public void move(Vector vector) {
        position.add(vector);
    }

    public Position getPosition() {
        return position;
    }

    public abstract void acceptVisitor(IGameObjectVisitor visitor);
}
