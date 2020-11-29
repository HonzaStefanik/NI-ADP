package cz.cvut.fit.miadp.mvcgame.abstractfactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB.*;
import cz.cvut.fit.miadp.mvcgame.strategy.EnemyMoveStrategy;

import java.util.Random;

public class GameObjectFactoryB implements IGameObjectFactory {

    private IGameModel model;

    public GameObjectFactoryB(IGameModel model) {
        this.model = model;
    }

    @Override
    public AbstractCannon createCannon() {
        return new CannonB(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public AbstractMissile createMissile(double initAngle, int initVelocity) {
        return new MissileB(new Position(model.getCannonPosition()), initAngle, initVelocity, model.getMovingStrategy());
    }

    @Override
    public AbstractEnemy createEnemy() {
        Random random = new Random();
        int cannonX = model.getCannonPosition().getX();
        // make sure enemies cant spawn behind / on the cannon
        int posX = cannonX * 2 + random.nextInt(MvcGameConfig.MAX_X - cannonX);
        // hardcoded 20px since idk the picture sizes; this is done os they wont spawn under the frame
        int posY = random.nextInt(MvcGameConfig.MAX_Y) - MvcGameConfig.ENEMY_HIT_BOX;
        int type = (Math.random() <= 0.5) ? 1 : 2;
        return new EnemyB(new Position(posX, posY), type, new EnemyMoveStrategy());
    }

    @Override
    public AbstractCollision createCollision(Position position) {
        return new CollisionB(position);
    }

    @Override
    public AbstractGameInfo createGameInfo() {
        return new GameInfoB(model);
    }
}
