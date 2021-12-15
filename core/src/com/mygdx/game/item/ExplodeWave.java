package com.mygdx.game.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

import java.util.ArrayList;
import java.util.List;

public class ExplodeWave implements AbstractItem{

    private final int x;
    private final int y;
    private final Texture texture;
    private final int radius;
    private final long timeCreating;

    public ExplodeWave(int x, int y, int radius, long timeCreating, String pathToTexture) {
        this.x = x;
        this.y = y;
        texture = new Texture(pathToTexture);
        this.radius = radius;
        this.timeCreating = timeCreating;
    }

    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        if (bomber != null)
            bomber.dead();
        return true;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }


    public int getRadius() {
        return radius;
    }

    public long getTimeCreating() {
        return timeCreating;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
