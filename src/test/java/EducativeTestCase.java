import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class EducativeTestCase {

    @Test
    public void undoCommandTest() {

        // illustration of mocking
        // IGameGraphics graphicsMock = mock(IGameGraphics);
        // when(graphicsMock.drawImage()).then(1);

        IGameModel model = new GameModel();

        Position positionBeforeUndo = model.getCannonPosition();

        model.registerCommand(new MoveCannonUpCommand(model));
        model.update();
        model.undoLastCommand();

        Position positionAfterUndo = model.getCannonPosition();

        assertEquals(positionBeforeUndo.getX(), positionAfterUndo.getX());
        assertEquals(positionBeforeUndo.getY(), positionAfterUndo.getY());
    }
}
