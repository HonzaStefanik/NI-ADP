package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractEnemy;

public class EnemyMoveStrategy implements IEnemyMovementStrategy {
    @Override
    public void move(AbstractEnemy enemy) {
        if (enemy.getPosition().getY() + MvcGameConfig.ENEMY_HIT_BOX >= MvcGameConfig.MAX_Y
            || enemy.getPosition().getY() + MvcGameConfig.ENEMY_HIT_BOX <= 0
        ) {
            enemy.setDirection(
                    enemy.getDirection() * -1
            );
        }
        enemy.move(new Vector(0, MvcGameConfig.ENEMY_MOVE_STEP * enemy.getDirection()));
    }
}
