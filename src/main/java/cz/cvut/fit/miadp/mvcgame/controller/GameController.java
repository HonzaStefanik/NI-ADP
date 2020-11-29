package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.command.*;
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
                    model.registerCommand(new ShootCannonCommand(model));
                    break;
                case "W":
                    model.registerCommand(new AimCannonUpCommand(model));
                    break;
                case "S":
                    model.registerCommand(new AimCannonDownCommand(model));
                    break;
                case "Q":
                    model.registerCommand(new CannonPowerDownCommand(model));
                    break;
                case "E":
                    model.registerCommand(new CannonPowerUpCommand(model));
                    break;
                case "M":
                    model.registerCommand(new ToggleMovingStrategyCommand(model));
                    break;
                case "N":
                    model.registerCommand(new ToggleShootingModeCommand(model));
                    break;
                case "B":
                    model.registerCommand(new UndoActionCommand(model));
                    break;
                default:
                    // nothing
            }
        }
    }

}
