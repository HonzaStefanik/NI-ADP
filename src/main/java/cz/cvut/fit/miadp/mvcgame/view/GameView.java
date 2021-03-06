package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.visitor.GameObjectRender;

public class GameView implements IObserver {

    private GameController controller;
    private IGameModel model;
    private IGameGraphics graphicsContext;
    private GameObjectRender gameObjectRender;
    private int updateCount;

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(model);
        this.model.registerObserver(this);
        this.updateCount = 0;
        this.gameObjectRender = new GameObjectRender();
    }

    @Override
    public void update() {
        updateCount++;
    }

    public void render() {
        if (graphicsContext == null) return;
        if (updateCount > 0) {
            graphicsContext.clear();
            model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(gameObjectRender));
            updateCount = 0;
        }
    }

    public GameController getController() {
        return controller;
    }

    public void setGraphicsContext(IGameGraphics graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.gameObjectRender.setGraphicsContext(graphicsContext);
    }
}
