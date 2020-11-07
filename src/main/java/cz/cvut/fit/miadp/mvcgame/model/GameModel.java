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

    @Override
    public void aimCannonUp() {
        cannon.aimUp();
        notifyObservers();
    }

    @Override
    public void aimCannonDown() {
        cannon.aimDown();
        notifyObservers();
    }

    @Override
    public void cannonPowerUp() {
        cannon.powerUp();
        notifyObservers();
    }

    @Override
    public void cannonPowerDown() {
        cannon.powerDown();
        notifyObservers();
    }

    @Override
    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(cannon);
        gameObjects.add(gameInfo);
        gameObjects.addAll(missiles);
        gameObjects.addAll(enemies);
        gameObjects.addAll(collisions);
        return gameObjects;
    }

    @Override
    public void timeTick() {
        update();
    }

    @Override
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

    @Override
    public void toggleShootingMode() {
        cannon.toggleShootingMode();
    }

    @Override
    public Position getCannonPosition() {
        return cannon.getPosition();
    }

    public List<AbstractMissile> getMissiles() {
        return missiles;
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    @Override
    public Memento createMemento() {
        Memento memento = new Memento();
        memento.score = this.score;
        memento.cannon = this.cannon;
        memento.enemies = this.enemies;
        memento.missiles = this.missiles;
        memento.collisions = this.collisions;
        return memento;
    }

    @Override
    public void setMemento(Object memento) {
        Memento m = (Memento) memento;
        this.score = m.score;
        this.cannon = m.cannon;
        this.enemies = m.enemies;
        this.missiles = m.missiles;
        this.collisions = m.collisions;
    }

    private static class Memento {
        private int score;
        private AbstractCannon cannon;
        private List<AbstractEnemy> enemies;
        private List<AbstractMissile> missiles;
        private List<AbstractCollision> collisions;
    }
}
