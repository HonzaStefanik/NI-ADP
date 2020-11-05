package cz.cvut.fit.miadp.mvcgame.memento;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    private IGameModel model;
    private List<Object> mementos = new ArrayList<>();

    private Caretaker() {
    }

    private static class CaretakerSingleton {
        private static final Caretaker CARETAKER_INSTANCE = new Caretaker();
    }

    public static Caretaker getInstance() {
        return CaretakerSingleton.CARETAKER_INSTANCE;
    }

    public Object createMemento() {
        if (model != null) {
            Object memento = model.createMemento();
            mementos.add(memento);
            return memento;
        }
        return null;
    }

    public void setMemento() {
        if (model != null && !mementos.isEmpty()) {
            model.setMemento(
                    mementos.get(mementos.size() - 1)
            );
        }
    }

    public void setModel(IGameModel model) {
        this.model = model;
    }
}
