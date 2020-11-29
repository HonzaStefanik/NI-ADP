package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

import java.util.List;

public abstract class AbstractCannon extends GameObject {

    protected IShootingMode shootingMode;
    protected static IShootingMode SINGLE_SHOOTING_MODE = new SingleShootingMode();
    protected static IShootingMode DOUBLE_SHOOTING_MODE = new DoubleShootingMode();
    protected double angle;
    protected int power;

    public AbstractCannon() {
        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;
    }

    public void toggleShootingMode() {
        if (this.shootingMode instanceof SingleShootingMode) {
            this.shootingMode = DOUBLE_SHOOTING_MODE;
        } else if (this.shootingMode instanceof DoubleShootingMode) {
            this.shootingMode = SINGLE_SHOOTING_MODE;
        }
    }


    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void aimUp();

    public abstract void aimDown();

    public abstract void powerUp();

    public abstract void powerDown();

    public abstract List<AbstractMissile> shoot();

    public abstract void primitiveShoot();

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        visitor.visitCannon(this);
    }

    public double getAngle() {
        return angle;
    }

    public int getPower() {
        return power;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public IShootingMode getShootingMode() {
        return shootingMode;
    }

    public void setShootingMode(IShootingMode shootingMode) {
        this.shootingMode = shootingMode;
    }
}
