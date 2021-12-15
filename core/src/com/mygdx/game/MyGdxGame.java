package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.logic.BoardService;
import com.mygdx.game.logic.BombService;
import com.mygdx.game.logic.GameLogic;
import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//это местный мейн, в create происходить создание
//в рендер отрисовка
//в апдейт вся логика игры
public class MyGdxGame extends ApplicationAdapter {
	//это поле для отрисовки
	private SpriteBatch batch;
	private BackGround backGround;
	private Board board;
	private GameLogic gameLogic;
	private BombService bombService;

	@Override
	public void create () {

		batch = new SpriteBatch();
		backGround = new BackGround();
		bombService = new BombService();
		board = BoardService.createBoard("testMap.txt");

		Cell one = BoardService.getEmptyCell(board, false);
		Cell two = BoardService.getEmptyCell(board, true);

		Bomber bomberOne = new Bomber(one.getX(), one.getY(), board, bombService);
		Bomber bomberTwo = new Bomber(two.getX(), two.getY(), board, bombService);

		List<Bomber> bombers = new ArrayList<>();
		bombers.add(bomberOne);
		bombers.add(bomberTwo);

		gameLogic = new GameLogic(bombers);
	}

	@Override
	public void render () {
		update();

		batch.begin();
		backGround.render(batch);
		board.render(batch);
		gameLogic.render(batch);
		batch.end();
	}

	public void update(){
		if (!gameLogic.gameEnd()) {
			//сюда нужно добавить таймер и если будет 3 секунды то взрыв
			long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
			gameLogic.checkEvent();
			gameLogic.checkTime(currentTime);
			bombService.explode(board, currentTime);
			//тут будет описана логика обновлений игры
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
