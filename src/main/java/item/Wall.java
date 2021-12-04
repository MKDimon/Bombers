package item;

import base.Bomber;
import base.Cell;

public class Wall implements AbstractItem {
    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        return false;
    }
}
