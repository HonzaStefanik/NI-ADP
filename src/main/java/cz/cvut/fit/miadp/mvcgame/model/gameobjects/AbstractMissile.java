package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractMissile extends LifetimeLimitedGO {

    private double initAngle;
    private int initVelocity;

    protected AbstractMissile(Position initialPosition, double initAngle, int initVelocity)
    {
        super(initialPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
    }

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        visitor.visitMissile(this);
    }

    public int getInitVelocity() {
        return this.initVelocity;
    }

    public double getInitAngle() {
        return this.initAngle;
    }

    public abstract void move();


}
