package com.mygdx.game.item;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

public class Box implements AbstractItem {
    private Texture texture;
    public Box(){texture = new Texture("box.png");}
    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        return false;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }
}
