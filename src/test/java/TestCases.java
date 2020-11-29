import cz.cvut.fit.miadp.mvcgame.abstractfactory.GameObjectFactoryA;
import cz.cvut.fit.miadp.mvcgame.command.*;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.CannonA;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.MissileA;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestCases {

    @Test
    public void undoCommandTest() {
        IGameModel model = new GameModel();

        Position positionBeforeUndo = model.getCannonPosition();

        model.registerCommand(new MoveCannonUpCommand(model));
        model.update();
        model.undoLastCommand();

        Position positionAfterUndo = model.getCannonPosition();

        assertEquals(positionBeforeUndo.getX(), positionAfterUndo.getX());
        assertEquals(positionBeforeUndo.getY(), positionAfterUndo.getY());
    }

    @Test
    public void execPowerUpCommandTest() {
        IGameModel model = new GameModel();
        int initPower = model.getPower();

        model.registerCommand(new CannonPowerUpCommand(model));
        model.update();

        assertEquals(initPower + MvcGameConfig.POWER_STEP, model.getPower());
    }

    @Test
    public void missileShootTest() {
        MissileA missile = new MissileA(new Position(50, 50), 0, 0, new SimpleMovingStrategy());
        int startX = missile.getPosition().getX();
        int startY = missile.getPosition().getY();
        missile.move();
        int endX = missile.getPosition().getX();
        int endY = missile.getPosition().getY();
        assertEquals(missile.getInitVelocity(), endX - startX);
        assertEquals(endY, startY);

    }

    @Test
    public void cannonShootTest() {
        GameObjectFactoryA factory = mock(GameObjectFactoryA.class);
        Mockito.when(factory.createMissile(anyInt(), anyInt()))
                .thenReturn(new MissileA(new Position(0, 0), 0, 0, new SimpleMovingStrategy()));
        Mockito.when(factory.createCannon())
                .thenReturn(new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), factory));
        AbstractCannon cannon = factory.createCannon();

        int shotCount = 5;
        for (int i = 0; i < shotCount; i++) {
            cannon.shoot();
        }
        verify(factory, times(shotCount)).createMissile(anyInt(), anyInt());
        cannon.toggleShootingMode();
        for (int i = 0; i < shotCount; i++) {
            cannon.shoot();
        }
        verify(factory, times(shotCount + shotCount * 2)).createMissile(anyInt(), anyInt());
    }

    @Test
    public void controllerTest() {
        GameModel model =  mock(GameModel.class);
        GameController controller = new GameController(model);

        controller.processPressedKeys(List.of("UP"));
        verify(model, times(1)).registerCommand(any(MoveCannonUpCommand.class));
        controller.processPressedKeys(List.of("DOWN"));
        verify(model, times(2)).registerCommand(any(MoveCannonDownCommand.class));
        controller.processPressedKeys(List.of("SPACE"));
        verify(model, times(3)).registerCommand(any(ShootCannonCommand.class));
        controller.processPressedKeys(List.of("W"));
        verify(model, times(4)).registerCommand(any(AimCannonUpCommand.class));
        controller.processPressedKeys(List.of("W"));
        verify(model, times(5)).registerCommand(any(AimCannonDownCommand.class));
        controller.processPressedKeys(List.of("Q"));
        verify(model, times(6)).registerCommand(any(CannonPowerDownCommand.class));
        controller.processPressedKeys(List.of("E"));
        verify(model, times(7)).registerCommand(any(CannonPowerUpCommand.class));
        controller.processPressedKeys(List.of("M"));
        verify(model, times(8)).registerCommand(any(ToggleMovingStrategyCommand.class));
        controller.processPressedKeys(List.of("N"));
        verify(model, times(9)).registerCommand(any(ToggleShootingModeCommand.class));
        controller.processPressedKeys(List.of("B"));
        verify(model, times(10)).registerCommand(any(UndoActionCommand.class));
    }
}
