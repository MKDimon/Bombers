package com.mygdx.game.model;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.item.AbstractItem;

import java.awt.*;
import java.util.ArrayList;

public class Board {

    private final int sizePx = 16;

    private final Cell[][] gameMap;

    public Board(Cell[][] gameMap) {
        this.gameMap = gameMap;
    }

    public boolean itemActivate(int x, int y, Bomber bomber) {
        if (x < 0 || y < 0 || x >= gameMap.length || y >= gameMap[0].length) {
            return false;
        }
        return gameMap[x][y].changeParams(bomber);
    }

    public void render(SpriteBatch batch){
        for(int i = 0; i <gameMap.length; i++){
            for(int j = 0; j < gameMap[i].length; j++){
                gameMap[i][j].render(batch);
            }
        }
    }

    public void setItem(int x, int y, AbstractItem item) {
        gameMap[x][y].setItem(item);
    }

    public Cell[][] getGameMap() {
        return gameMap;
    }
}
