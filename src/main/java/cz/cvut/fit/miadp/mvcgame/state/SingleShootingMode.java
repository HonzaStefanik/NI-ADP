package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;

public class SingleShootingMode implements IShootingMode {

    @Override
    public String getName() {
        return "SingleShootingMode";
    }

    @Override
    public void shoot(AbstractCannon cannon) {
        cannon.primitiveShoot();
    }
}
