package base;

import item.AbstractItem;
import logic.BombService;

import java.util.Objects;

public class Bomber {
    private double x;
    private double y;

    private double speed     = 0.2;
    private int radius       = 1;
    private boolean immortal = false;

    private int maxCountBombs = 1;
    private int curCountBombs = 0;

    private Board board;

    private double collisionRadius = 0.3;

    private BombService bombService;

    public Bomber(double x, double y, Board board, BombService bombService) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.bombService = bombService;
    }

    public void action(EventType event) {
        if (event == EventType.SET_BOMB) {
            bombService.createBomb(board, radius, (int) x, (int) y);
        }
        else {
            board.itemActivate((int) x, (int) y, this);
        }
    }

    private void addParam(AbstractItem item) {

    }

    public void move() {

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }

    public int getMaxCountBombs() {
        return maxCountBombs;
    }

    public void setMaxCountBombs(int maxCountBombs) {
        this.maxCountBombs = maxCountBombs;
    }

    public int getCurCountBombs() {
        return curCountBombs;
    }

    public void setCurCountBombs(int curCountBombs) {
        this.curCountBombs = curCountBombs;
    }

    public double getCollisionRadius() {
        return collisionRadius;
    }

    public void setCollisionRadius(double collisionRadius) {
        this.collisionRadius = collisionRadius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bomber)) return false;
        Bomber bomber = (Bomber) o;
        return Double.compare(bomber.getX(), getX()) == 0 &&
                Double.compare(bomber.getY(), getY()) == 0 &&
                Double.compare(bomber.getSpeed(), getSpeed()) == 0 &&
                getRadius() == bomber.getRadius() &&
                isImmortal() == bomber.isImmortal() &&
                getMaxCountBombs() == bomber.getMaxCountBombs() &&
                getCurCountBombs() == bomber.getCurCountBombs() &&
                Double.compare(bomber.getCollisionRadius(), getCollisionRadius()) == 0 &&
                Objects.equals(board, bomber.board) &&
                Objects.equals(bombService, bomber.bombService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getSpeed(), getRadius(), isImmortal(), getMaxCountBombs(), getCurCountBombs(), board, getCollisionRadius(), bombService);
    }
}
