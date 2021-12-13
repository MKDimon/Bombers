package com.mygdx.game.logic;


import com.badlogic.gdx.Input;
import com.mygdx.game.model.EventType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GDXController {
    private static Map<Integer, EventType> map1 = new HashMap<>();
    private static Map<Integer, EventType> map2 = new HashMap<>();

    private static List<Integer> list1 = new LinkedList<>();
    private static List<Integer> list2 = new LinkedList<>();

    static {
        map1.put(Input.Keys.A, EventType.MOVE_LEFT);
        map1.put(Input.Keys.W, EventType.MOVE_UP);
        map1.put(Input.Keys.S, EventType.MOVE_DOWN);
        map1.put(Input.Keys.D, EventType.MOVE_RIGHT);
        map1.put(Input.Keys.SPACE, EventType.SET_BOMB);

        map2.put(Input.Keys.LEFT, EventType.MOVE_LEFT);
        map2.put(Input.Keys.UP, EventType.MOVE_UP);
        map2.put(Input.Keys.DOWN, EventType.MOVE_DOWN);
        map2.put(Input.Keys.RIGHT, EventType.MOVE_RIGHT);
        map2.put(Input.Keys.ENTER, EventType.SET_BOMB);

        list1.add(Input.Keys.A); list1.add(Input.Keys.W);
        list1.add(Input.Keys.S); list1.add(Input.Keys.D);
        list1.add(Input.Keys.SPACE);

        list2.add(Input.Keys.LEFT); list2.add(Input.Keys.UP);
        list2.add(Input.Keys.DOWN); list2.add(Input.Keys.RIGHT);
        list2.add(Input.Keys.ENTER);
    }

    public static EventType getEventTypeOne(int key) {
        return map1.getOrDefault(key, EventType.NONE);
    }

    public static List<Integer> getList1() {
        return list1;
    }

    public static List<Integer> getList2() {
        return list2;
    }

    public static EventType getEventTypeTwo(int key) {
        return map2.getOrDefault(key, EventType.NONE);
    }
}
