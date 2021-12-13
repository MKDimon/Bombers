package com.mygdx.game.logic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.EventType;

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

    public void checkEvent() {
        for (Integer item: GDXController.getList1()) {
            if (Gdx.input.isKeyPressed(item)) {
                bombers.get(0).action(GDXController.getEventTypeOne(item));
            }
        }
        for (Integer item: GDXController.getList2()) {
            if (Gdx.input.isKeyPressed(item)) {
                bombers.get(1).action(GDXController.getEventTypeTwo(item));
            }
        }
    }

    public void render(SpriteBatch batch){
        for(Bomber bomber : bombers){
            bomber.render(batch);
        }
    }

    public boolean isGameBegin() {
        return gameBegin;
    }

    public void setGameBegin(boolean gameBegin) {
        this.gameBegin = gameBegin;
    }

}
