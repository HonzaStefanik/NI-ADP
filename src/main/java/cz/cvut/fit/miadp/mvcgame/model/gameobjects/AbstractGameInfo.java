package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectVisitor;

public abstract class AbstractGameInfo extends GameObject{

    protected IGameModel model;

    public AbstractGameInfo(IGameModel model) {
        this.model = model;
    }

    @Override
    public void acceptVisitor(IGameObjectVisitor visitor) {
        visitor.visitGameInfo(this);
    }

    public abstract String getGameInfo();

}
