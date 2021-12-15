package com.mygdx.game.item;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

public class Shield implements AbstractItem {
    private Texture texture;
    public Shield(){}
    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        if (bomber != null)
            bomber.addParam(0,0,true);
        return false;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }
}
