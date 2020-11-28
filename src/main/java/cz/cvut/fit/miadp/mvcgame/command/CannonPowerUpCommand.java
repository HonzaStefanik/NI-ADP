package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonPowerUpCommand extends AbstractGameCommand {
    public CannonPowerUpCommand(IGameModel subject) {
        this.subject = subject;
    }

    @Override
    protected void execute() {
        subject.cannonPowerUp();
    }
}
