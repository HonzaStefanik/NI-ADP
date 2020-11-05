package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public class MissileA extends AbstractMissile {

    IMovingStrategy movingStrategy;

    public MissileA(Position initialPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(initialPosition, initAngle, initVelocity);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public void move() {
        this.movingStrategy.updatePosition(this);
    }


}
