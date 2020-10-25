package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.GameObjectFactoryA;
import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements IObservable {
    private AbstractCannon cannon;
    private AbstractGameInfo gameInfo;
    private List<AbstractMissile> missiles;
    private List<AbstractEnemy> enemies;
    private List<AbstractCollision> collisions;
    private List<IObserver> observers;
    private IGameObjectFactory gameObjectFactory;

    public GameModel() {
        this.gameObjectFactory = new GameObjectFactoryA();
        this.cannon = this.gameObjectFactory.createCannon();
        this.observers = new ArrayList<>();
        this.missiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.collisions = new ArrayList<>();
        this.gameInfo = gameObjectFactory.createGameInfo();
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

    public void moveCannonUp() {
        cannon.moveUp();
        notifyObservers();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        notifyObservers();
    }

    public void shootCannon() {
        missiles.add(cannon.shoot());
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
            missile.move(new Vector(MvcGameConfig.MOVE_STEP, 0));
        }
        destroyMissiles();
        notifyObservers();
    }

    public Position getCannonPosition() {
        return cannon.getPosition();
    }

    public List<AbstractMissile> getMissiles() {
        return missiles;
    }
}
