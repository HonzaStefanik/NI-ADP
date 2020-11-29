package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.strategy.IEnemyMovementStrategy;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractEnemy extends GameObject {

    private int type;
    private int direction;
    protected IEnemyMovementStrategy movementStrategy;

    public AbstractEnemy(Position pos, int type, IEnemyMovementStrategy movementStrategy) {
        this.position = pos;
        this.type = type;
        this.movementStrategy = movementStrategy;
        this.direction = 1;
    }

    public AbstractEnemy(AbstractEnemy abstractEnemy) {
        this.type = abstractEnemy.type;
        this.position = new Position(abstractEnemy.getPosition());
        this.movementStrategy = abstractEnemy.movementStrategy;
        this.direction = abstractEnemy.direction;
    }


    public void move() {
        movementStrategy.move(this);
    }

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        visitor.visitEnemy(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
