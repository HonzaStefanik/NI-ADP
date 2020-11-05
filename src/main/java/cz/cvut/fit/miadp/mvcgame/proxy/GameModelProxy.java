package cz.cvut.fit.miadp.mvcgame.proxy;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class GameModelProxy implements IGameModel {

    private IGameModel subject;

    public GameModelProxy(IGameModel subject) {
        this.subject = subject;
    }

    @Override
    public void moveCannonUp() {
        subject.moveCannonUp();
    }

    @Override
    public void moveCannonDown() {
        subject.moveCannonDown();
    }

    @Override
    public void shootCannon() {
        subject.shootCannon();
    }
}
