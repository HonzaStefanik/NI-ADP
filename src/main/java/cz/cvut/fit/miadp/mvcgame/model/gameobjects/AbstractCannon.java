package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.visitor.GameObjectRender;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractCannon extends GameObject {

    //private int power;

    public void moveUp() {
        this.move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
    }

    public void moveDown() {
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }

    public abstract AbstractMissile shoot();

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        visitor.visitCannon(this);
    }
}
