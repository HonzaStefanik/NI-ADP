package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractEnemy;

public interface IEnemyMovementStrategy {
    void move(AbstractEnemy enemy);
}
