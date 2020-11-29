package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractEnemy;
import cz.cvut.fit.miadp.mvcgame.strategy.IEnemyMovementStrategy;

public class EnemyA extends AbstractEnemy {

    public EnemyA(Position pos, int type, IEnemyMovementStrategy movementStrategy) {
        super(pos, type, movementStrategy);
    }
}
