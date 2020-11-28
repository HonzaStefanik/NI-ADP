package cz.cvut.fit.miadp.mvcgame.bridge;

import cz.cvut.fit.miadp.mvcgame.model.Position;

public class GameGraphics implements IGameGraphics {

    private IGameGraphicsImplementor implementor;

    public GameGraphics(IGameGraphicsImplementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void drawImage(String path, Position pos) {
        implementor.drawImage(path, pos);
    }

    @Override
    public void drawText(String text, Position pos) {
        implementor.drawText(text, pos);
    }

    @Override
    public void drawRectangle(Position leftTop, Position rightBottom) {
        implementor.drawLine(
                leftTop,
                new Position(rightBottom.getX(), leftTop.getY())
        );
        implementor.drawLine(
                new Position(rightBottom.getX(), leftTop.getY()),
                rightBottom
        );
        implementor.drawLine(
                rightBottom,
                new Position(leftTop.getX(), rightBottom.getY())
        );
        implementor.drawLine(
                new Position(leftTop.getX(), rightBottom.getY()),
                leftTop
        );
    }

    @Override
    public void clear() {
        implementor.clear();
    }
}
