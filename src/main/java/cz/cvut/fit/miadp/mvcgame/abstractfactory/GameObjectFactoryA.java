package cz.cvut.fit.miadp.mvcgame.abstractfactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.CannonA;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.GameInfoA;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.MissileA;

public class GameObjectFactoryA implements IGameObjectFactory{

    @Override
    public AbstractCannon createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public AbstractMissile createMissile(Position position) {
        return new MissileA(position);
    }

    @Override
    public AbstractEnemy createEnemy() {
        return null;
    }

    @Override
    public AbstractCollision createCollision() {
        return null;
    }

    @Override
    public AbstractGameInfo createGameInfo() {
        return new GameInfoA("test");
    }
}
