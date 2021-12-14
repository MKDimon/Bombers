package com.mygdx.game.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

public class ExplodeWave implements AbstractItem{

    private Texture texture;

    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        return true;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {

    }
}
