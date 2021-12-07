package base;

import item.AbstractItem;

public class Board {

    private final Cell[][] gameMap;

    public Board(Cell[][] gameMap) {
        this.gameMap = gameMap;
    }

    public void itemActivate(int x, int y, Bomber bomber) {
        gameMap[x][y].changeParams(bomber);
    }

    public void setItem(int x, int y, AbstractItem item) {
        gameMap[x][y].setItem(item);
    }

    public Cell[][] getGameMap() {
        return gameMap;
    }
}
