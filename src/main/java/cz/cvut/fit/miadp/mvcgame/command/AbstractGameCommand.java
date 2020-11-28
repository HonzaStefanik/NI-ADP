package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public abstract class AbstractGameCommand {

    protected IGameModel subject;
    protected Object memento;

    protected abstract void execute();

    public void doExecute(){
        this.memento = subject.createMemento();
        this.execute();
    }

    public void undo() {
        subject.setMemento(memento);
    }
}
