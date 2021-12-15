package com.mygdx.game.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Board;
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
    private final Board board;

    public ExplodeWave(int x, int y, int radius, long timeCreating, Board board) {
        this.x = x;
        this.y = y;
        textureExplode = new Texture(explodeTexturePath);
        textureExplodeWave = new Texture(explodeTextureWavePath);
        textureExplodeWaveEnd = new Texture(explodeTextureWaveEndPath);
        this.radius = radius;
        this.timeCreating = timeCreating;
        this.board = board;
    }

    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        if (bomber != null)
            bomber.dead();
        return true;
    }

    private void renderPropagation(SpriteBatch batch, int x, int y) {
        for (int i = 1; i <= radius; i++) {
            if (!board.itemActivate(this.x + i * x, this.y + i * y, null)) {
                break;
            }
            batch.draw(textureExplode, 16*(i * x+ this.x ), 16*(i * y + this.y));
        }
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(textureExplode, x, y);
        renderPropagation(batch,1, 0);
        renderPropagation(batch,-1, 0);
        renderPropagation(batch,0, 1);
        renderPropagation(batch,0, -1);
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
