package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;

import java.util.ArrayList;
import java.util.List;

public class CannonB extends AbstractCannon {

    private IGameObjectFactory gameObjectFactory;
    private double angle;
    private int power;
    private List<AbstractMissile> shootingBatch;

    public CannonB(Position initialPosition, IGameObjectFactory gameObjectFactory) {
        this.position = initialPosition;
        this.gameObjectFactory = gameObjectFactory;
        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;
        this.shootingBatch = new ArrayList<>();
        this.shootingMode = SINGLE_SHOOTING_MODE;
    }

    @Override
    public void moveUp() {
        if (position.getY() > 0) {
            this.move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
        } else {
            position = new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y);
        }
    }

    @Override
    public void moveDown() {
        if (position.getY() < MvcGameConfig.MAX_Y - 10) {
            this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
        } else {
            position = new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y);
        }
    }

    @Override
    public List<AbstractMissile> shoot() {
        this.shootingBatch.clear();
        // use current state to shoot
        this.shootingMode.shoot(this);
        return this.shootingBatch;
    }

    public void primitiveShoot() {

        this.shootingBatch.add(this.gameObjectFactory.createMissile(this.angle, this.power));
    }

    @Override
    public void aimUp() {
        this.angle -= MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void aimDown() {
        this.angle += MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void powerUp() {
        this.power += MvcGameConfig.POWER_STEP;
    }

    @Override
    public void powerDown() {
        this.power -= MvcGameConfig.POWER_STEP;
    }
}