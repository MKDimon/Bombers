package com.mygdx.game.logic;


import com.mygdx.game.item.AbstractItem;
import com.mygdx.game.item.Box;
import com.mygdx.game.item.Wall;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Cell;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BoardService {

    private static final Map<Character, AbstractItem> mapItem = new HashMap<>();

    static {
        mapItem.put('_', null);
        mapItem.put('#', new Wall());
        mapItem.put('*', new Box());
    }

    /*
    * ###############################
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * #_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#
    * ###############################
    * */
    //пока давай на той карте что вверху, потом уже  будем коробки добавлять и все остальное
    public static Board createBoard(String mapFile) {
        //наверное стоит в файле также хранить путь на картинку, если захотим делать разные карты то еще пути на разные картинки
        //!АТТЕНШН!
        //нужно читать файл с конца!!!!!!!!!!!!!!! то есть снизу вверх
        //чтобы элементу 0,0 соответсвовал левый нижний угол карты, потому что
        //в библиотеке система координат начинается как обычная дкс, а не сверху вниз как здесь.

        try(BufferedReader br = new BufferedReader(new FileReader(mapFile))) {
            String line;
            line = br.readLine();
            String[] sizes = line.split(" ");
            Cell[][] cells = new Cell[Integer.parseInt(sizes[1])][Integer.parseInt(sizes[0])];

            for (int i = 0; i < cells[0].length; i++) {
                line = br.readLine();
                for (int j = 0; j < line.length(); j++) {
                    cells[j][i] = new Cell(mapItem.get(line.charAt(j)), j, i);
                }
            }
            return new Board(cells);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Cell getEmptyCell(final Board board, boolean type) {
        Cell[][] cells = board.getGameMap();

        Cell result = null;

        for(Cell[] cells1: cells) {
            for (Cell cell: cells1) {
                if (cell.itemIsNull()) {
                    result = cell;
                    if (type) return result;
                }
            }
        }
        return result;
    }

}
