package com.mygdx.game.model.item;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

public class Wall implements AbstractItem {
    private Texture texture;
    public Wall(){
        texture = new Texture("Wall.png");
    }
    //подумать как отрисовывать, каждую отдельно или нет?
    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        return false;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }
}
