package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonPowerDownCommand extends AbstractGameCommand {

    public CannonPowerDownCommand(IGameModel subject) {
        this.subject = subject;
    }

    @Override
    protected void execute() {
        subject.cannonPowerDown();
    }
}