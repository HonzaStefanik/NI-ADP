package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

public class Vector {

    private int dX = 0;
    private int dY = 0;

    public Vector() {
    }

    public Vector(int dX, int dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }
}
