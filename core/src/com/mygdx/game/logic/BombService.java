package com.mygdx.game.logic;


import com.mygdx.game.item.Bomb;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Bomber;

import java.util.ArrayList;
import java.util.List;

public class BombService {
    private final List<Bomber> bombers = new ArrayList<>();
//я тут вписал какие то пиксели но на самом деле я не помню, посмотри сперва, наверное я хотел чтобы бомбу можно выставлять даже если
//бомбер стоит непонятно где, вообще мне кажется ставить ее нужно на центр бомбера, там у Rectangle boardBomber есть метод getCenter()
//если что все координаты в библиотеке float
    public boolean createBomb(Board board, int radius, float x, float y) {
        //TODO: проверку на количество бомб и тд
        Bomb bomb = new Bomb((int)x/16, (int)y/16, radius);
        board.setItem((int)x/16,(int)y/16, bomb);
        explode((int)x/16,(int)y/16);
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
