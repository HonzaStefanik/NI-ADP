package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.MvcGame;
import cz.cvut.fit.miadp.mvcgame.bridge.GameGraphics;
import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.bridge.JavaFXGraphics;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * TODO
 * 1) Implement enemies + collisions - DONE
 * 2) Implement game info - DONE
 * 3) Figure out values to control the game to make the game feel good t ocontrol (eg how fast cannon moves, how fast power changes) - good enough I think
 * 4) Unit tests  (5, at least of them 2 with mocks) - DONE
 * 5) game object factory B?
 */

public class MvcGameJavaFxLauncher extends Application {

    private static final MvcGame theMvcGame = new MvcGame();

    @Override
    public void init() {
        theMvcGame.init();
    }

    @Override
    public void start(Stage stage) {
        String winTitle = theMvcGame.getWindowTitle();
        int winWidth = theMvcGame.getWindowWidth();
        int winHeight = theMvcGame.getWindowHeight();

        stage.setTitle( winTitle );

        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );

        Canvas canvas = new Canvas( winWidth, winHeight );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();
        IGameGraphics gr = new GameGraphics(new JavaFXGraphics(gc));

        ArrayList<String> pressedKeysCodes = new ArrayList<>();

        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !pressedKeysCodes.contains(code) )
                        pressedKeysCodes.add( code );
                }
        );

        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    pressedKeysCodes.remove(code);
                }
        );

        // the game-loop
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                theMvcGame.processPressedKeys(pressedKeysCodes);
                pressedKeysCodes.clear();
                theMvcGame.update();
                theMvcGame.render(gr);
            }
        }.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}