package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractMissile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObjectRender implements IGameObjectVisitor {

    private GraphicsContext graphicsContext;

    @Override
    public void visitCannon(AbstractCannon cannon) {
        graphicsContext.drawImage(new Image("images/cannon.png"), cannon.getPosition().getX(), cannon.getPosition().getY());
    }

    @Override
    public void visitMissile(AbstractMissile missile) {
        graphicsContext.drawImage(new Image("images/missile.png"), missile.getPosition().getX(), missile.getPosition().getY());

    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }
}
