package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;

public abstract class GameObject {

    protected Position position;

    public void move(Vector vector) {
        position.add(vector);
    }

    public Position getPosition() {
        return position;
    }
}
