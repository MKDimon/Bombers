package logic;

import base.Bomber;
import base.EventType;
import com.badlogic.gdx.Gdx;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameLogic {
    private Queue<EventType> eventQueue;
    private boolean gameBegin;
    private List<Bomber> bombers;


    public GameLogic(List<Bomber> bombers) {
        this.bombers = bombers;
        gameBegin = false;
        eventQueue = new LinkedList<EventType>();
    }

    public void start() {
        gameBegin = true;
        while(gameBegin) {
            checkEvent();
        }
    }

    private void checkEvent() {
        for (Integer item: GDXController.getList1()) {
            if (Gdx.input.isKeyPressed(item)) {
                bombers.get(0).action(GDXController.getEventTypeOne(item));
                System.out.printf(item.toString());
            }
        }
        for (Integer item: GDXController.getList2()) {
            if (Gdx.input.isKeyPressed(item)) {
                bombers.get(1).action(GDXController.getEventTypeTwo(item));
                System.out.printf(item.toString());
            }
        }
    }

    public boolean isGameBegin() {
        return gameBegin;
    }

    public void setGameBegin(boolean gameBegin) {
        this.gameBegin = gameBegin;
    }

}
