package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.GameObjectFactoryA;
import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements IGameModel {
    private int score;
    private AbstractCannon cannon;
    private AbstractGameInfo gameInfo;
    private List<AbstractMissile> missiles;
    private List<AbstractEnemy> enemies;
    private List<AbstractCollision> collisions;
    private List<IObserver> observers;
    private IGameObjectFactory gameObjectFactory;
    private IMovingStrategy movingStrategy;

    public GameModel() {
        this.score = 0;
        this.gameObjectFactory = new GameObjectFactoryA(this);
        this.cannon = this.gameObjectFactory.createCannon();
        this.observers = new ArrayList<>();
        this.missiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.collisions = new ArrayList<>();
        this.gameInfo = gameObjectFactory.createGameInfo();
        this.movingStrategy = new SimpleMovingStrategy();
    }

    @Override
    public void registerObserver(IObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(IObserver::update);
    }

    @Override
    public void moveCannonUp() {
        cannon.moveUp();
        notifyObservers();
    }

    @Override
    public void moveCannonDown() {
        cannon.moveDown();
        notifyObservers();
    }

    @Override
    public void shootCannon() {
        missiles.addAll(cannon.shoot());
        notifyObservers();
    }

    public void aimCannonUp() {
        cannon.aimUp();
        notifyObservers();
    }

    public void aimCannonDown() {
        cannon.aimDown();
        notifyObservers();
    }

    public void cannonPowerUp() {
        cannon.powerUp();
        notifyObservers();
    }

    public void cannonPowerDown() {
        cannon.powerDown();
        notifyObservers();
    }


    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(cannon);
        gameObjects.add(gameInfo);
        gameObjects.addAll(missiles);
        gameObjects.addAll(enemies);
        gameObjects.addAll(collisions);
        return gameObjects;
    }

    public void timeTick() {
        update();
    }

    public void update() {
        moveMissiles();
    }

    private void destroyMissiles() {
        List<AbstractMissile> toRemove = new ArrayList<>();
        for (AbstractMissile missile : missiles) {
            if (missile.getPosition().getX() > MvcGameConfig.MAX_X) {
                toRemove.add(missile);
            }
        }
        missiles.removeAll(toRemove);
    }

    private void moveMissiles() {
        for (AbstractMissile missile : missiles) {
            missile.move();
        }
        destroyMissiles();
        notifyObservers();
    }

    public void toggleMovingStrategy() {
        if (movingStrategy instanceof SimpleMovingStrategy) {
            movingStrategy = new RealisticMovingStrategy();
        } else if (movingStrategy instanceof RealisticMovingStrategy) {
            movingStrategy = new SimpleMovingStrategy();
        }
    }

    public void toggleShootingMode() {
        cannon.toggleShootingMode();
    }

    public Position getCannonPosition() {
        return cannon.getPosition();
    }

    public List<AbstractMissile> getMissiles() {
        return missiles;
    }

    public IMovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public Memento createMemento() {
        Memento memento = new Memento();
        memento.score = this.score;
        return memento;
    }

    public void setMemento(Object memento) {
        Memento m = (Memento) memento;
        this.score = m.score;
    }

    private class Memento {
        private int score;
        // TODO game model state snapshot (info about the rest of the game i guess?)
    }
}
