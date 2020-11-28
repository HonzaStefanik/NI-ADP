package cz.cvut.fit.miadp.mvcgame.bridge;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JavaFXGraphics implements IGameGraphicsImplementor {

    protected GraphicsContext graphicsContext;

    public JavaFXGraphics(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void drawImage(String path, Position pos) {
        Image image = new Image(path);
        graphicsContext.drawImage(image, pos.getX(), pos.getY());
    }

    @Override
    public void drawText(String text, Position pos) {
        graphicsContext.fillText(text, pos.getX(), pos.getY());
    }

    @Override
    public void drawLine(Position startPos, Position endPos) {
        graphicsContext.strokeLine(
                startPos.getX(),
                startPos.getY(),
                endPos.getX(),
                endPos.getY()
        );
    }

    @Override
    public void clear() {
        graphicsContext.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
    }
}
