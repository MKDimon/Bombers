package com.mygdx.game.model;

public enum EventType {
    MOVE_LEFT(-1, 0, 2),
    MOVE_RIGHT(1, 0, 1),
    MOVE_UP(0, 1, 3),
    MOVE_DOWN(0, -1, 0),
    SET_BOMB(0, 0, 0),
    NONE(0, 0, 0),
    ;

    private final int x;
    private final int y;
    private final int frameId;

    EventType(int x, int y, int frameId) {
        this.x = x;
        this.y = y;
        this.frameId = frameId;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getFrameId() {
        return frameId;
    }
}
