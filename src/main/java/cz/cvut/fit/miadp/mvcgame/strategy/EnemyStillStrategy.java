package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractEnemy;

public class EnemyStillStrategy implements IEnemyMovementStrategy{
    @Override
    public void move(AbstractEnemy enemy) {
        // do nothing, stay still
    }
}
