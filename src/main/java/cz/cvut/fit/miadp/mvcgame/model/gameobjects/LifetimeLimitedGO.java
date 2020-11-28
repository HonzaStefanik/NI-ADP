package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class LifetimeLimitedGO extends GameObject {

    private LocalDateTime bornAt;

    public LifetimeLimitedGO() {
    }

    protected LifetimeLimitedGO(Position position) {
        this.position = position;
        this.bornAt = LocalDateTime.now();
    }

    public long getAge() {
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.MILLIS.between(bornAt, now);
    }

    public LocalDateTime getBornAt() {
        return bornAt;
    }
}
