package com.mygdx.game.logic;


import com.mygdx.game.item.Bomb;
import com.mygdx.game.item.ExplodeWave;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Bomber;

import java.util.ArrayList;
import java.util.List;

public class BombService {
    private final List<Bomber> bombers = new ArrayList<>();
    private final List<Bomb> bombs = new ArrayList<>();
    private long timeExplode = 4;


    public boolean createBomb(Board board, int radius, int x, int y, long timeCreate) {

        Bomb bomb = new Bomb(x, y, radius, timeCreate);
        board.setItem(x,y, bomb);
        bombs.add(bomb);
        return true;
    }

    public boolean explode(Board board, long currentTime) {
        for(Bomb bomb : bombs){
            if(currentTime - bomb.getTimeCreate() > timeExplode){
                board.setItem(bomb.getX(), bomb.getY(), new ExplodeWave("explode.png"));
            }
        }
        return true;
    }

    public void addBomber(Bomber bomber) {
        bombers.add(bomber);
    }
}
