package cz.cvut.fit.miadp.mvcgame.abstractfactory;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public interface IGameObjectFactory {
    AbstractCannon createCannon();
    AbstractMissile createMissile(Position position);
    AbstractEnemy createEnemy();
    AbstractCollision createCollision();
    AbstractGameInfo createGameInfo();
}
