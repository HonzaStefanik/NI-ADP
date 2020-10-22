package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.Vector;
import cz.cvut.fit.miadp.mvcgame.visitor.GameObjectRender;

public class CannonA extends AbstractCannon {

    private IGameObjectFactory gameObjectFactory;

    public CannonA(Position position, IGameObjectFactory gameObjectFactory) {
        this.position = position;
        this.gameObjectFactory = gameObjectFactory;
    }

    @Override
    public void moveUp() {
        this.move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void moveDown() {
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }

    @Override
    public AbstractMissile shoot() {
        return gameObjectFactory.createMissile();
    }

    @Override
    public void acceptVisitor(GameObjectRender gameObjectRender) {
        gameObjectRender.visitCannon(this);
    }
}
