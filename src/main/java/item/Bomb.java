package item;

import base.Bomber;
import base.Cell;

public class Bomb implements AbstractItem {
    private final int x;
    private final int y;
    private final int radius;

    public Bomb(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        return false;
    }
}
