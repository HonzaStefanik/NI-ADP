package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;

public interface IShootingMode {
    String getName();
    void shoot(AbstractCannon cannon);
}

