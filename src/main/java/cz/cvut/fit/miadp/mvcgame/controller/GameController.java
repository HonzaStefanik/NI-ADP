package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.command.MoveCannonDownCommand;
import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class GameController {

    private IGameModel model;

    public GameController(IGameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for (String code : pressedKeysCodes) {
            switch (code) {
                case "UP":
                    model.registerCommand(new MoveCannonUpCommand(model));
                    break;
                case "DOWN":
                    model.registerCommand(new MoveCannonDownCommand(model));
                    break;
                case "SPACE":
                    model.shootCannon();
                    break;
                case "W":
                    model.aimCannonUp();
                    break;
                case "S":
                    model.aimCannonDown();
                    break;
                case "Q":
                    model.cannonPowerDown();
                    break;
                case "E":
                    model.cannonPowerUp();
                    break;
                case "M":
                    model.toggleMovingStrategy();
                    break;
                case "N":
                    model.toggleShootingMode();
                    break;
              //  case "P":
              //      Caretaker.getInstance().createMemento();
              //      break;
              //  case "O":
              //      Caretaker.getInstance().setMemento();
              //      break;
                case "B":
                    model.undoLastCommand();
                    break;
                default:
                    // nothing
            }
        }
    }

}
