package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public interface IGameObjectVisitor {
    void visitCannon(AbstractCannon cannon);
    void visitMissile(AbstractMissile missile);
    void visitEnemy(AbstractEnemy enemy);
    void visitCollision(AbstractCollision collision);
    void visitGameInfo(AbstractGameInfo gameInfo);

}
