package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;

public class RealisticMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition(AbstractMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();
        long time = missile.getAge() / 100;
        int dX = (int) (initVelocity * time * Math.cos(initAngle));
        int dY = (int) (initVelocity * time * Math.sin(initAngle) - (0.5 * MvcGameConfig.GRAVITY * time * time)) * -1;
        missile.move(new Vector(dX, dY));
    }
}

