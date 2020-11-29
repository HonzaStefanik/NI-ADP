package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;

import java.util.ArrayList;
import java.util.List;

import static cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig.ANGLE_STEP;
import static cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig.POWER_STEP;

public class CannonA extends AbstractCannon {

    private IGameObjectFactory gameObjectFactory;
    private List<AbstractMissile> shootingBatch;

    public CannonA(Position initialPosition, IGameObjectFactory gameObjectFactory) {
        this.position = initialPosition;
        this.gameObjectFactory = gameObjectFactory;

        this.shootingBatch = new ArrayList<>();
        this.shootingMode = SINGLE_SHOOTING_MODE;
    }

    @Override
    public void moveUp() {
        if (position.getY() > 0) {
            this.move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
        }
    }

    @Override
    public void moveDown() {
        if (position.getY() < MvcGameConfig.MAX_Y - 10) {
            this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
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
        this.shootingBatch.add(gameObjectFactory.createMissile(this.angle, this.power));
    }

    @Override
    public void aimUp() {
        angle -= ANGLE_STEP;
    }

    @Override
    public void aimDown() {
        angle += ANGLE_STEP;
    }

    @Override
    public void powerUp() {
        power += POWER_STEP;
    }

    @Override
    public void powerDown() {
        if (power - POWER_STEP > 0) {
            power -= POWER_STEP;
        } else {
            power = POWER_STEP;
        }
    }
}