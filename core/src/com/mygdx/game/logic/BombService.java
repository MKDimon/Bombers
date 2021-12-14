package com.mygdx.game.logic;


import com.mygdx.game.item.Bomb;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Bomber;

import java.util.ArrayList;
import java.util.List;

public class BombService {
    private final List<Bomber> bombers = new ArrayList<>();

    public boolean createBomb(Board board, int radius, int x, int y) {

        Bomb bomb = new Bomb(x, y, radius);
        board.setItem(x,y, bomb);
        //создали бомбу
        //для нее сделали время создания
        //следим за временем игры
        //если время - время создания > time
        //explode!
        explode(board, radius, x,y);
        return true;
    }

    private boolean explode(Board board, int radius, int x, int y) {
        //TODO: таймер и тд
        for(int i = 0; i < radius; i++){
            //а еще нужно отслеживать что индекс не ушел за границу
            //короче тут вообще все туманно максимально
            board.itemActivate(x+i, y, null);
            board.itemActivate(x, y+i, null);
            board.itemActivate(x-i, y, null);
            board.itemActivate(x, y-i, null);
        }
        return true;
    }

    public void addBomber(Bomber bomber) {
        bombers.add(bomber);
    }
}
