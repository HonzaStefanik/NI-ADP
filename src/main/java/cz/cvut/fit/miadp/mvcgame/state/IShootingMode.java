package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;

public interface IShootingMode {
    public String getName();
    public void shoot(AbstractCannon cannon);
}

