package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public class MissileA extends AbstractMissile {

    public MissileA(Position position){
        this.position = position;
    }

}
