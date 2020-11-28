package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.GameObjectFactoryA;
import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
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
    private Queue<AbstractGameCommand> unexecutedCommands;
    private Stack<AbstractGameCommand> executedCommands;

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
        this.unexecutedCommands = new LinkedBlockingQueue<>();
        this.executedCommands = new Stack<>();
        this.gameInfo = gameObjectFactory.createGameInfo();
        for (int i = 0; i < MvcGameConfig.ENEMY_COUNT; i++) {
            this.enemies.add(gameObjectFactory.createEnemy());
        }
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
        executeCommands();
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
        memento.cannonX = this.cannon.getPosition().getX();
        memento.cannonY = this.cannon.getPosition().getY();
        memento.cannonAngle = cannon.getAngle();
        memento.cannonPower = cannon.getPower();
        memento.missilePositions.clear();
        memento.enemyPositions.clear();
        missiles.forEach(
                missile -> memento.missilePositions.add(
                        new Position(missile.getPosition())
                )
        );
        enemies.forEach(
                enemy -> memento.enemyPositions.add(
                        new Pair<>(enemy.getType(), new Position(enemy.getPosition()))
                )
        );
        return memento;
    }

    @Override
    public void setMemento(Object memento) {
        Memento m = (Memento) memento;
        this.score = m.score;
        cannon.getPosition().setX(m.cannonX);
        cannon.getPosition().setY(m.cannonY);
        cannon.setAngle(m.cannonAngle);
        cannon.setPower(m.cannonPower);
        missiles.clear();
        enemies.clear();
        m.missilePositions.forEach(
                pos -> {
                    AbstractMissile missile = gameObjectFactory.createMissile(cannon.getAngle(), cannon.getPower());
                    missile.setPosition(pos);
                    missiles.add(missile);
                }
        );
        m.enemyPositions.forEach(
                pair -> {
                    AbstractEnemy enemy = gameObjectFactory.createEnemy();
                    enemy.setPosition(pair.getValue());
                    enemy.setType(pair.getKey());
                    enemies.add(enemy);
                }
        );
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        unexecutedCommands.add(command);
    }

    @Override
    public void undoLastCommand() {
        if (executedCommands.isEmpty()) return;
        AbstractGameCommand lastCommand = executedCommands.pop();
        lastCommand.undo();
        notifyObservers();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getPower() {
        return cannon.getPower();
    }

    @Override
    public double getGravity() {
        // TODO allow the user to change it?
        return MvcGameConfig.GRAVITY;
    }

    private void executeCommands() {
        while (!unexecutedCommands.isEmpty()) {
            AbstractGameCommand currentCommand = unexecutedCommands.poll();
            currentCommand.doExecute();
            executedCommands.push(currentCommand);
        }
    }

    private static class Memento {
        private int score;
        private int cannonX;
        private int cannonY;
        private int cannonPower;
        private double cannonAngle;

        private List<Pair<Integer, Position>> enemyPositions = new ArrayList<>();
        private List<Position> missilePositions = new ArrayList<>();
    }
}
