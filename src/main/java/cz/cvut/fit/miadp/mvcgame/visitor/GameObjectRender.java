package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public class GameObjectRender implements IGameObjectVisitor {

    private IGameGraphics graphicsContext;

    @Override
    public void visitCannon(AbstractCannon cannon) {
        graphicsContext.drawImage(MvcGameConfig.CANNON, cannon.getPosition());
    }

    @Override
    public void visitMissile(AbstractMissile missile) {
        graphicsContext.drawImage(MvcGameConfig.MISSILE, missile.getPosition());
    }

    @Override
    public void visitEnemy(AbstractEnemy enemy) {
        graphicsContext.drawImage(MvcGameConfig.ENEMY + enemy.getType() + ".png", enemy.getPosition());
    }

    @Override
    public void visitCollision(AbstractCollision collision) {
        graphicsContext.drawImage(MvcGameConfig.COLLISION, collision.getPosition());
    }

    @Override
    public void visitGameInfo(AbstractGameInfo gameInfo) {
        graphicsContext.drawText(gameInfo.getGameInfo(), new Position(MvcGameConfig.GAME_INFO_X, MvcGameConfig.GAME_INFO_Y));
    }

    public void setGraphicsContext(IGameGraphics graphicsContext) {
        this.graphicsContext = graphicsContext;
    }
}
