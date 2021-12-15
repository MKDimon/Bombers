package com.mygdx.game.model;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.item.AbstractItem;
import com.mygdx.game.item.Bomb;

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

    public boolean contains(int x, int y, Bomber bomber){
        if (x < 0 || y < 0 || x >= gameMap.length || y >= gameMap[0].length) {
            return false;
        }
        return gameMap[x][y].containsBomber(bomber);
    }

    public void render(SpriteBatch batch){
        for (Cell[] cells : gameMap) {
            for (Cell cell : cells) {
                cell.render(batch);
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
