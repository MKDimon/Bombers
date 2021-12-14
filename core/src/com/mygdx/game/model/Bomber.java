package com.mygdx.game.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
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
    private boolean live             = false;
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

        bomberAnimation = new Animator(new TextureRegion(texture), 4, 6, speedCycle);
        boardBomber = new Rectangle(pos.x+2, pos.y+2, 13,13);
    }

    //задача action выполнить действие пришедшее от пользователя
    public void action(EventType event) {
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
            if(event == EventType.MOVE_LEFT){
                //установка на строку анимации соответсвующую движению
                bomberAnimation.setFrameY(2);

                //смотрим на две крайние точки, смотрим не пересекают ли они другие предметы
                if (
                    board.itemActivate(xCenter-1, yCenter,this)&&
                    board.itemActivate(xCenter-1, yCenter+1,this)&&
                    board.itemActivate(xCenter-1, yCenter-1,this)

                ) {
                    move(-1*speed,0);
                }
                //если предметы дали добро, то
                // изменять позицию бомбера
            }
            else if(event == EventType.MOVE_RIGHT){
                bomberAnimation.setFrameY(1);
                if(
                    board.itemActivate(xCenter+1, yCenter,this)&&
                    board.itemActivate(xCenter+1, yCenter+1,this)&&
                    board.itemActivate(xCenter+1, yCenter-1,this)
                ){
                    move(1*speed,0);
                }
            }
            else if(event == EventType.MOVE_DOWN){
                bomberAnimation.setFrameY(0);
                if(
                    board.itemActivate(xCenter, yCenter-1,this)&&
                    board.itemActivate(xCenter+1, yCenter-1,this)&&
                    board.itemActivate(xCenter-1, yCenter-1,this)
                ){
                    move(0,-1*speed);
                }
            }
            else if(event == EventType.MOVE_UP){
                bomberAnimation.setFrameY(3);
                if(
                    board.itemActivate(xCenter, yCenter+1,this)&&
                    board.itemActivate(xCenter-1, yCenter+1,this)&&
                    board.itemActivate(xCenter+1, yCenter+1,this)
                ){
                    move(0,1*speed);
                }
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

    public void setLive(boolean live) {
        this.live = live;
    }
}
