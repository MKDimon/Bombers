package com.mygdx.game.item;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

public interface AbstractItem {
    boolean changeParams(Cell cell, Bomber bomber);
    void render(SpriteBatch batch, float x, float y);
}
