package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.observer.IObservable;

public interface IGameModel extends IObservable {
    // TODO decide which methods go in this iface - hw04 (definitely will need those which fail at compilation)
    void moveCannonUp();

    void moveCannonDown();

    void shootCannon();
}
