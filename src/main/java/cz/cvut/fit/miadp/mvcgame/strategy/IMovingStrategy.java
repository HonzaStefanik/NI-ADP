package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;

public interface IMovingStrategy {
    void updatePosition(AbstractMissile missile);
}
