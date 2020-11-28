package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractEnemy extends GameObject {

    private int type;

    public AbstractEnemy(Position pos, int type) {
        this.position = pos;
        this.type = type;
    }

    public AbstractEnemy(AbstractEnemy abstractEnemy){
        this.type = abstractEnemy.type;
        this.position = new Position(abstractEnemy.getPosition());
    }

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
       visitor.visitEnemy(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
