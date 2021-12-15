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
    private final Texture textureExplode;
    private final Texture textureExplodeWave;
    private final Texture textureExplodeWaveEnd;
    private final int radius;
    private final long timeCreating;
    private final String explodeTexturePath = "explode.png";
    private final String explodeTextureWavePath = "explodeWave.png";
    private final String explodeTextureWaveEndPath = "explodeWaveEnd.png";

    public ExplodeWave(int x, int y, int radius, long timeCreating) {
        this.x = x;
        this.y = y;
        textureExplode = new Texture(explodeTexturePath);
        textureExplodeWave = new Texture(explodeTextureWavePath);
        textureExplodeWaveEnd = new Texture(explodeTextureWaveEndPath);
        this.radius = radius;
        this.timeCreating = timeCreating;
    }

    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        bomber.dead();
        return true;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(textureExplode, x, y);
        for(int i = 0; i < radius * 2 + 1; i++){
            batch.draw(textureExplodeWave,(this.x - radius + i) * 16, this.y *i * 16);
            batch.draw(textureExplodeWave,this.x * i * 16, (this.x - radius + i) * 16);
        }
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
