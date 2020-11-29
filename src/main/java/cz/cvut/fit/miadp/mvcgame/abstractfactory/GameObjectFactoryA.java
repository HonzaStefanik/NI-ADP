package cz.cvut.fit.miadp.mvcgame.abstractfactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.*;
import cz.cvut.fit.miadp.mvcgame.strategy.EnemyStillStrategy;

import java.util.Random;

public class GameObjectFactoryA implements IGameObjectFactory{

    private IGameModel model;

    public GameObjectFactoryA(GameModel model) {
        this.model = model;
    }

    @Override
    public AbstractCannon createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public AbstractMissile createMissile(double initAngle, int initVelocity) {
        return new MissileA(new Position(model.getCannonPosition()), initAngle, initVelocity, model.getMovingStrategy());
    }

    @Override
    public AbstractEnemy createEnemy() {
        Random random = new Random();
        int cannonX = model.getCannonPosition().getX();
        // make sure enemies cant spawn behind / on the cannon
        int posX = cannonX * 2 + random.nextInt(MvcGameConfig.MAX_X - cannonX);
        int posY = random.nextInt(MvcGameConfig.MAX_Y) - MvcGameConfig.ENEMY_HIT_BOX;
        int type = (Math.random() <= 0.5) ? 1 : 2;
        return new EnemyA(new Position(posX, posY), type, new EnemyStillStrategy());
    }

    @Override
    public AbstractCollision createCollision(Position position) {
        return new CollisionA(position);
    }

    @Override
    public AbstractGameInfo createGameInfo() {
        return new GameInfoA(model);
    }
}
