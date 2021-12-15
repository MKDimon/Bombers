package com.mygdx.game.logic;


import com.mygdx.game.item.Bomb;
import com.mygdx.game.item.ExplodeWave;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Bomber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BombService {
    private final List<Bomber> bombers = new ArrayList<>();
    private final List<Bomb> bombs = new ArrayList<>();
    private final List<ExplodeWave> waves = new ArrayList<>();
    private final long timeExplodeBomb = 4;
    private final long timeExplodeWave = 1;
    private final String explodeTexturePath = "explode.png";
    private final String explodeTextureWavePath = "explodeWave.png";
    private final String explodeTextureWaveEndPath = "explodeWaveEnd.png";
    private final Board board;

    public BombService(Board board) {
        this.board = board;
    }

    public boolean createBomb(int radius, int x, int y, long timeCreate) {
        Bomb bomb = new Bomb(x, y, radius, timeCreate);
        board.setItem(x,y, bomb);
        bombs.add(bomb);
        return true;
    }

    public boolean explode(long currentTime) {
        for (Bomb bomb : bombs) {
            if (currentTime - bomb.getTimeCreating() > timeExplodeBomb) {
                ExplodeWave wave = new ExplodeWave(bomb.getX(), bomb.getY(), bomb.getRadius(), currentTime, explodeTexturePath);
                board.setItem(bomb.getX(), bomb.getY(), wave);
                //здесь нужно добавить добавление волн
                waves.add(wave);
            }
        }
        return true;
    }

    public void explodeWave(long currentTime) {
        Iterator<ExplodeWave> itWave = waves.iterator();
        while (itWave.hasNext()) {
            ExplodeWave wave = itWave.next();
            for (Bomber bomber: bombers) {
                for (int i = 0; i < wave.getRadius()*2 + 1; i++) {
                    if (board.contains(wave.getX() - wave.getRadius() + i, wave.getY(), bomber) ||
                    board.contains(wave.getX(), wave.getY() - wave.getRadius() + i, bomber)) {
                        bomber.dead();
                    }
                }
            }
            if (currentTime - wave.getTimeCreating() > timeExplodeWave) {
                board.setItem(wave.getX(), wave.getY(), null);
                itWave.remove();
            }
        }
    }

    public void addBomber(Bomber bomber) {
        bombers.add(bomber);
    }
}
