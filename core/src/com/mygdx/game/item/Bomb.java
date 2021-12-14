package com.mygdx.game.item;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animator;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

import java.util.TimerTask;

public class Bomb implements AbstractItem {
    private final int x;
    private final int y;
    private final int radius;
    private float time;
    private Texture texture;
    private Animator animator;
    public Bomb(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        texture = new Texture("bomb.png");
        time = 3;
        animator = new Animator(new TextureRegion(texture), 1, 3, time);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getTime() {
        return time;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        return false;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(animator.getFrame(), x, y);
        animator.update(0.8f);
    }
}
