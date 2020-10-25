package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;

public class CannonB extends AbstractCannon {

    private IGameObjectFactory gameObjectFactory;

    public CannonB(Position position, IGameObjectFactory gameObjectFactory) {
        this.position = position;
        this.gameObjectFactory = gameObjectFactory;
    }

    @Override
    public AbstractMissile shoot() {
        return gameObjectFactory.createMissile(new Position(this.getPosition().getX(), this.getPosition().getY()));
    }
}
