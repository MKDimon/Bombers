package com.mygdx.game.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animator;
import com.mygdx.game.item.AbstractItem;
import com.mygdx.game.logic.BombService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Bomber {
    //это для преобрзования координат в [][], соотвествует размеру квадратика одной клетки
    private int sizePx = 16;
    //позиция бомбера на карте для отрисовки
    private Vector2 pos;
    //класс ответственный за анимацию движения
    private Animator bomberAnimation;
    //спрайт бомбера
    private Texture texture;
    private Texture textureDead;
    //границы бомбера
    private Rectangle boardBomber;

    //скорость передвижения
    private float speed              = 0.5f;
    private float speedCycle         = 12;
    //скорость цикла анимации
    private float speedAnimation     = 0.3f;
    //радиус взрыва
    private int radius               = 1;
    //бессмертие
    private boolean immortal         = false;
    private boolean live             = true;
    //максимальное количество бомб
    private int maxCountBombs        = 1;
    //текущее количество бомб
    private int curCountBombs        = 0;

    //карта
    private final Board board;

    //сервис ответственный за взрыв бомбы
    private final BombService bombService;

    private long reloadingTime        = 2;
    private List<Long> createBombTime = new ArrayList<>();

    public Bomber(int x, int y, Board board, BombService bombService) {

        this.board = board;
        this.bombService = bombService;
        bombService.addBomber(this);

        pos = new Vector2(sizePx * x, sizePx * y);
        texture = new Texture("bomberSprite.png");
        textureDead = new Texture("DeadSprite.png");

        bomberAnimation = new Animator(new TextureRegion(texture), 4, 6, speedCycle);
        boardBomber = new Rectangle(pos.x+2, pos.y+2, 13,13);
    }

    //задача action выполнить действие пришедшее от пользователя
    public void action(EventType event) {
        if (!live) { return; }
        Vector2 centerBomber = new Vector2();
        boardBomber.getCenter(centerBomber);
        int xCenter = (int)centerBomber.x/sizePx;
        int yCenter = (int)centerBomber.y/sizePx;
        if (event == EventType.SET_BOMB && curCountBombs != maxCountBombs) {
            long timeCreate = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            createBombTime.add(timeCreate);
            bombService.createBomb(board, radius, xCenter, yCenter, timeCreate);
            curCountBombs++;
        }
        else {
            bomberAnimation.setFrameY(event.getFrameId());
            // Смотрим клетку по направлению движения и
            // клетки слева и справа
            if (board.itemActivate(xCenter + event.getX() + event.getY(),
                            yCenter + event.getY() + event.getX(), this) &&
                board.itemActivate(xCenter + event.getX() - event.getY(),
                            yCenter + event.getY() - event.getX(), this) &&
                board.itemActivate(xCenter + event.getX(),
                            yCenter + event.getY(), this))
            {
                move(event.getX() * speed, event.getY() * speed);
            }
        }
    }

    public void checkTime(long currentTime){
        if(curCountBombs > 0){
            Iterator<Long> iterator = createBombTime.iterator();
            while (iterator.hasNext()){
                if(currentTime - iterator.next() > reloadingTime){
                    curCountBombs--;
                    iterator.remove();
                }
            }
        }
    }

    //отрисовывает бомбера
    public void render(SpriteBatch batch){
        batch.draw(bomberAnimation.getFrame(), pos.x, pos.y);
    }

    private void addParam(AbstractItem item) {

    }

    public Rectangle getBoardBomber() {
        return boardBomber;
    }

    public void move(float x, float y) {
        pos.x += x;
        pos.y += y;
        boardBomber.x += x;
        boardBomber.y += y;
        bomberAnimation.update(speedAnimation);
    }

    public Texture getTexture() {
        return texture;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }

    public int getMaxCountBombs() {
        return maxCountBombs;
    }

    public void setMaxCountBombs(int maxCountBombs) {
        this.maxCountBombs = maxCountBombs;
    }

    public int getCurCountBombs() {
        return curCountBombs;
    }

    public void setCurCountBombs(int curCountBombs) {
        this.curCountBombs = curCountBombs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bomber bomber = (Bomber) o;
        return Float.compare(bomber.speed, speed) == 0 && radius == bomber.radius && immortal == bomber.immortal && maxCountBombs == bomber.maxCountBombs && curCountBombs == bomber.curCountBombs && Objects.equals(pos, bomber.pos) && Objects.equals(bomberAnimation, bomber.bomberAnimation) && Objects.equals(texture, bomber.texture) && Objects.equals(boardBomber, bomber.boardBomber) && Objects.equals(board, bomber.board) && Objects.equals(bombService, bomber.bombService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, bomberAnimation, texture, boardBomber, speed, radius, immortal, maxCountBombs, curCountBombs, board, bombService);
    }

    public void dead() {
        bomberAnimation = new Animator(new TextureRegion(textureDead), 1, 5, speedCycle);

        this.live = false;
    }

    public boolean getLive() { return live; }
}
