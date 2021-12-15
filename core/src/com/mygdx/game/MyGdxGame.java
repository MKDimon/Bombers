package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		board = BoardService.createBoard("testMap.txt");
		bombService = new BombService(board);

		Cell one = BoardService.getEmptyCell(board, true);
		Cell two = BoardService.getEmptyCell(board, false);

		Bomber bomberOne = new Bomber(one.getX(), one.getY(), board, bombService, "bomberSpritePlayer1.png", "DeadSpritePlayer1.png");
		Bomber bomberTwo = new Bomber(two.getX(), two.getY(), board, bombService, "bomberSpritePlayer2.png", "DeadSpritePlayer2.png");

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
			bombService.explode(currentTime);
			bombService.explodeWave(currentTime);
			//тут будет описана логика обновлений игры
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
