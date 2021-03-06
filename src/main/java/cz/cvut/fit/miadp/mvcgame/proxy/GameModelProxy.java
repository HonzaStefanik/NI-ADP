package cz.cvut.fit.miadp.mvcgame.proxy;

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

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

    @Override
    public void aimCannonUp() {
        subject.aimCannonUp();
    }

    @Override
    public void aimCannonDown() {
        subject.aimCannonDown();
    }

    @Override
    public void cannonPowerUp() {
        subject.cannonPowerUp();
    }

    @Override
    public void cannonPowerDown() {
        subject.cannonPowerDown();
    }

    @Override
    public void toggleMovingStrategy() {
        subject.toggleMovingStrategy();
    }

    @Override
    public void toggleShootingMode() {
        subject.toggleShootingMode();
    }

    @Override
    public void update() {
        subject.update();
    }

    @Override
    public void timeTick() {
        subject.timeTick();
    }

    @Override
    public List<GameObject> getGameObjects() {
        return subject.getGameObjects();
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return subject.getMovingStrategy();
    }

    @Override
    public Object createMemento() {
        return subject.createMemento();
    }

    @Override
    public void setMemento(Object memento) {
        subject.setMemento(memento);
    }

    @Override
    public Position getCannonPosition() {
        return subject.getCannonPosition();
    }

    @Override
    public void registerObserver(IObserver observer) {
        subject.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        subject.unregisterObserver(observer);
    }

    @Override
    public void notifyObservers() {
        subject.notifyObservers();
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        subject.registerCommand(command);
    }

    @Override
    public void undoLastCommand() {
        subject.undoLastCommand();
    }

    @Override
    public int getScore() {
        return subject.getScore();
    }

    @Override
    public int getPower() {
        return subject.getPower();
    }

    @Override
    public double getGravity() {
        return subject.getGravity();
    }
}
