package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;

public interface IGameObjectVisitor {
    void visitCannon(AbstractCannon cannon);
    void visitMissile(AbstractMissile missile);
    // TODO collisions, enemies, gameInfo

}
