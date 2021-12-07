package logic;

import base.Board;
import base.Cell;
import item.AbstractItem;
import item.Box;
import item.Wall;

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

    public static Board createBoard(String mapFile) {
        try(BufferedReader br = new BufferedReader(new FileReader(mapFile))) {
            String line;
            line = br.readLine();
            String[] sizes = line.split(" ");
            Cell[][] cells = new Cell[Integer.parseInt(sizes[0])][Integer.parseInt(sizes[1])];
            for (int i = 0; i < cells.length; i++) {
                line = br.readLine();
                for (int j = 0; j < line.length(); j++) {
                    cells[i][j] = new Cell(mapItem.get(line.charAt(j)), i, j);
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
                if (result == null && cell.itemIsNull()) {
                    result = cell;
                    if (type) return result;
                }
            }
        }
        return result;
    }

}
