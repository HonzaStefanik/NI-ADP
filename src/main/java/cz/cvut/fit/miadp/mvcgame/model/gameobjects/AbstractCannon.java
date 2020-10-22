package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

public abstract class AbstractCannon extends GameObject {

    //private int power;

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract AbstractMissile shoot();
}
