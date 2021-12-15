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
    private final long timeExplodeBomb = 4;   // sc
    private final long timeExplodeWave = 300; //mlsc
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
        Iterator<Bomb> itBomb = bombs.iterator();
        while (itBomb.hasNext()) {
            Bomb bomb = itBomb.next();
            if (currentTime - bomb.getTimeCreating() > timeExplodeBomb) {
                ExplodeWave wave = new ExplodeWave(bomb.getX(), bomb.getY(), bomb.getRadius(), currentTime, explodeTexturePath);
                board.setItem(bomb.getX(), bomb.getY(), wave);
                waves.add(wave);
                itBomb.remove();
            }
        }
        return true;
    }

    /**
     * Распространение волны по вектору
     * @param wave Нужнная волна
     * @param x  - Вектор по X
     * @param y  - Вектор по Y
     */
    private void wavePropagation(ExplodeWave wave, int x, int y) {
        for (int i = 1; i <= wave.getRadius(); i++) {
            if (!board.itemActivate(wave.getX() + i * x, wave.getY() + i * y, null)) {
                break;
            }
            int finalI = i;
            bombers.stream().filter(bomber -> board.contains(wave.getX() + finalI * x,
                    wave.getY() + finalI * y, bomber)).forEach(Bomber::dead);
        }
    }

    public void explodeWave(long currentTime) {
        Iterator<ExplodeWave> itWave = waves.iterator();
        while (itWave.hasNext()) {
            ExplodeWave wave = itWave.next();

            bombers.stream().filter(x -> board.contains(wave.getX(), wave.getY(), x)).forEach(Bomber::dead);

            wavePropagation(wave, 1, 0);
            wavePropagation(wave, -1, 0);
            wavePropagation(wave, 0, 1);
            wavePropagation(wave, 0, -1);

            if (currentTime - wave.getTimeCreating() > timeExplodeWave / 1000.) {
                board.setItem(wave.getX(), wave.getY(), null);
                itWave.remove();
            }
        }
    }

    public void addBomber(Bomber bomber) {
        bombers.add(bomber);
    }
}
