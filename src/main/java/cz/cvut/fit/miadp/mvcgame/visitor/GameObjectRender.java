package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public class GameObjectRender implements IGameObjectVisitor {

    private IGameGraphics graphicsContext;

    @Override
    public void visitCannon(AbstractCannon cannon) {
        graphicsContext.drawImage("images/cannon.png", cannon.getPosition());
    }

    @Override
    public void visitMissile(AbstractMissile missile) {
        graphicsContext.drawImage("images/missile.png", missile.getPosition());
    }

    @Override
    public void visitEnemy(AbstractEnemy enemy) {

    }

    @Override
    public void visitCollision(AbstractCollision collision) {

    }

    @Override
    public void visitGameInfo(AbstractGameInfo gameInfo) {

    }

    public void setGraphicsContext(IGameGraphics graphicsContext) {
        this.graphicsContext = graphicsContext;
    }
}
