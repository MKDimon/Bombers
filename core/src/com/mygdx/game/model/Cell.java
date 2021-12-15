package com.mygdx.game.model;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.item.AbstractItem;

public class Cell {
    private AbstractItem item;
    private final int x;
    private final int y;
    private Rectangle boardCell;

    public Cell(AbstractItem item, int  x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
        boardCell = new Rectangle(x*16, y*16, 16,16);
    }

    // клетка знает о себе, но не знает какую сторону бомбера проверять, видимо нужно чтобы это проверялось еще до, а
    // это получается нужно дергать клетку аж в бомбера, что видимо есть плохо
    public boolean changeParams(Bomber bomber) {
        if(item == null) {
            return true;
        }
        //
        if(bomber == null || containsBomber(bomber))
        {
            return item.changeParams(this, bomber);
        }
        return true;
    }

    private boolean contains(float x, float y) {
        return boardCell.x < x && boardCell.x + boardCell.width > x && boardCell.y < y && boardCell.y + boardCell.height > y;
    }

    public boolean containsBomber(Bomber bomber){
        Rectangle bomberRect = bomber.getBoardBomber();
        return contains(bomberRect.x, bomberRect.y) ||
                contains(bomberRect.x, bomberRect.y+bomberRect.getHeight()) ||
                contains(bomberRect.x+bomberRect.getWidth(), bomberRect.y+bomberRect.getHeight()) ||
                contains(bomberRect.x+bomberRect.getWidth(), bomberRect.y);

    }
    public void render(SpriteBatch batch){
        if(item!=null) {
            item.render(batch, x*16, y*16);
        }
    }

    public void setItem(AbstractItem item) {
        this.item = item;
    }

    public boolean itemIsNull() {
        return item == null;
    }

    public AbstractItem getItem() {
        return item;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
