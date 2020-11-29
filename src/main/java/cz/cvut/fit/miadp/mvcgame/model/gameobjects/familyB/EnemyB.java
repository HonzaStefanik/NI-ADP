package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractEnemy;
import cz.cvut.fit.miadp.mvcgame.strategy.IEnemyMovementStrategy;

public class EnemyB extends AbstractEnemy {

    public EnemyB(Position pos, int type, IEnemyMovementStrategy movementStrategy) {
        super(pos, type, movementStrategy);
    }
}
