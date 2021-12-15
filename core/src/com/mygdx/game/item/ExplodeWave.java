package com.mygdx.game.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

public class ExplodeWave implements AbstractItem{

    private Texture texture;

    public ExplodeWave(String pathToTexture) {
        texture = new Texture(pathToTexture);
    }

    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        bomber.dead();
        return true;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }
}
