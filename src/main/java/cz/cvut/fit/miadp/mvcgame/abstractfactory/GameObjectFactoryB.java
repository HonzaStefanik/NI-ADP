package cz.cvut.fit.miadp.mvcgame.abstractfactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.CannonA;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB.MissileB;

public class GameObjectFactoryB implements IGameObjectFactory {

    private IGameModel model;

    public GameObjectFactoryB(IGameModel model) {
        this.model = model;
    }

    @Override
    public AbstractCannon createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public AbstractMissile createMissile(double initAngle, int initVelocity) {
        return new MissileB(model.getCannonPosition(), initAngle, initVelocity, model.getMovingStrategy());
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
        return null;
    }
}