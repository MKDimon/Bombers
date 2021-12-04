package item;

import base.Bomber;
import base.Cell;

public class Shield implements AbstractItem {
    @Override
    public boolean changeParams(Cell cell, Bomber bomber) {
        return false;
    }
}
