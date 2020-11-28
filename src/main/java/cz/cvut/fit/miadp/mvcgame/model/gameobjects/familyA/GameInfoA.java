package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractGameInfo;

public class GameInfoA extends AbstractGameInfo {

    public GameInfoA(IGameModel model) {
        super(model);
    }

    @Override
    public String getGameInfo() {
        return "Score: " + model.getScore()
                + " | Y: " + model.getCannonPosition().getY()
                + " | Power: " + model.getPower()
                + " | Gravity: " + model.getGravity();
    }
}
