package cz.cvut.fit.miadp.mvcgame.view;

// in future, use Bridge to remove this dependency

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameView implements IObserver {

    private GameController controller;
    private GameModel model;
    private GraphicsContext gr;
    private boolean wasUpdate;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(model);
        this.model.registerObserver(this);
        this.wasUpdate = true;
    }

    @Override
    public void update() {
        wasUpdate = true;
        render();
    }

    public void render() {
        if (gr == null) return;
        drawCannon();
        wasUpdate = false;
    }

    private void drawCannon() {
        gr.drawImage(new Image("images/cannon.png"), model.getCannonPosition().getX(), model.getCannonPosition().getY());
    }

    public GameController getController() {
        return controller;
    }

    public void setGraphicsContext(GraphicsContext gr) {
        this.gr = gr;
    }

    public boolean wasUpdate() {
        return wasUpdate;
    }
}
