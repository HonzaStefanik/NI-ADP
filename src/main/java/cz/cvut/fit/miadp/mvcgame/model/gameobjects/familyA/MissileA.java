package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;
import cz.cvut.fit.miadp.mvcgame.visitor.GameObjectRender;

public class MissileA extends AbstractMissile {

    public MissileA(Position position){
        this.position = position;
    }
    @Override
    public void acceptVisitor(GameObjectRender gameObjectRender) {
        gameObjectRender.visitMissile(this);
    }

}
