package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

public interface IGameModel extends IObservable {
    public void moveCannonUp();
    public void moveCannonDown();
    public void shootCannon();
    public void aimCannonUp();
    public void aimCannonDown();
    public void cannonPowerUp();
    public void cannonPowerDown();
    public void toggleMovingStrategy();
    public void toggleShootingMode();
    public void update();
    public void timeTick();
    public List<GameObject> getGameObjects();
    public IMovingStrategy getMovingStrategy();
    public Object createMemento();
    public void setMemento(Object memento);
    public Position getCannonPosition();


}
