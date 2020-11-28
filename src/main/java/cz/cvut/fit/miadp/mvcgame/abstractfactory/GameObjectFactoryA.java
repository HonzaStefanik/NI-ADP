package cz.cvut.fit.miadp.mvcgame.abstractfactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.CannonA;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.EnemyA;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.GameInfoA;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.MissileA;

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
        int posX = cannonX + random.nextInt(MvcGameConfig.MAX_X - cannonX);
        // hardcoded 20px since idk the picture sizes; this is done os they wont spawn under the frame
        int posY = random.nextInt(MvcGameConfig.MAX_Y) + 20;
        int type = (Math.random() <= 0.5) ? 1 : 2;
        return new EnemyA(new Position(posX, posY), type);
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
