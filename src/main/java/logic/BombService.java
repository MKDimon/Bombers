package logic;

import base.Board;
import base.Bomber;
import base.Cell;
import item.Bomb;

import java.util.ArrayList;
import java.util.List;

public class BombService {
    private final List<Bomber> bombers = new ArrayList<>();

    public boolean createBomb(Board board, int radius, int x, int y) {
        //TODO: проверку на количество бомб и тд
        Bomb bomb = new Bomb(x, y, radius);
        board.setItem(x,y, bomb);
        explode(x,y);
        return true;
    }

    private boolean explode(int x, int y) {
        //TODO: таймер и тд
        return true;
    }

    public void addBomber(Bomber bomber) {
        bombers.add(bomber);
    }
}
