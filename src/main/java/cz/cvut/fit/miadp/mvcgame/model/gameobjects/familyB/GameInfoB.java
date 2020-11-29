package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractGameInfo;

public class GameInfoB extends AbstractGameInfo {
    public GameInfoB(IGameModel model) {
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
