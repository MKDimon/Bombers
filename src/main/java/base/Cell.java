package base;

import item.AbstractItem;

public class Cell {
    private AbstractItem item;
    private int x;
    private int y;

    public Cell(AbstractItem item, int x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
    }

    public boolean changeParams(Bomber bomber) {
        return item.changeParams(this, bomber);
    }

    public void setItem(AbstractItem item) {
        this.item = item;
    }
}
