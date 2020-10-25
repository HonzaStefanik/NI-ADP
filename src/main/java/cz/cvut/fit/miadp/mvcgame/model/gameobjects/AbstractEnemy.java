package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractEnemy extends GameObject {
    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        // TODO compliant with UML diagram for lab 03, visitor behaviour will probably be defined in later lectures
    }
}
