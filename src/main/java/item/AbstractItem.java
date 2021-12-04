package item;

import base.Bomber;
import base.Cell;

public interface AbstractItem {
    boolean changeParams(Cell cell, Bomber bomber);
}
