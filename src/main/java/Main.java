import base.Board;
import base.Bomber;
import base.Cell;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import logic.BoardService;
import logic.BombService;
import logic.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BombService bombService = new BombService();
        Board board = BoardService.createBoard("src/main/resources/mapOne");

        Cell one = BoardService.getEmptyCell(board, true);
        Cell two = BoardService.getEmptyCell(board, false);

        Bomber bomberOne = new Bomber(one.getX(), one.getY(), board, bombService);
        Bomber bomberTwo = new Bomber(two.getX(), two.getY(), board, bombService);

        List<Bomber> bombers = new ArrayList<>();
        bombers.add(bomberOne); bombers.add(bomberTwo);

        GameLogic gameLogic = new GameLogic(bombers);

        gameLogic.start();
    }

}
