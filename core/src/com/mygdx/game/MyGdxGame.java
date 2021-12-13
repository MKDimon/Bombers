package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.logic.BoardService;
import com.mygdx.game.logic.BombService;
import com.mygdx.game.logic.GameLogic;
import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

//это местный мейн, в create происходить создание
//в рендер отрисовка
//в апдейт вся логика игры
public class MyGdxGame extends ApplicationAdapter {
	//это поле для отрисовки
	private SpriteBatch batch;
	private BackGround backGround;
	private Board board;
	private GameLogic gameLogic;


	@Override
	public void create () {

		/*


        Bomber bomberOne = new Bomber(one.getX(), one.getY(), board, bombService);
        Bomber bomberTwo = new Bomber(two.getX(), two.getY(), board, bombService);

        List<Bomber> bombers = new ArrayList<>();
        bombers.add(bomberOne); bombers.add(bomberTwo);

        GameLogic gameLogic = new GameLogic(bombers);

        gameLogic.start();*/
		batch = new SpriteBatch();
		backGround = new BackGround();
		BombService bombService = new BombService();
		board = BoardService.createBoard("testMap.txt");

		Cell one = BoardService.getEmptyCell(board, true);
		//Cell two = BoardService.getEmptyCell(board, false);

		Bomber bomberOne = new Bomber(one.getX(), one.getY(), board, bombService);
		List<Bomber> bombers = new ArrayList<>();
		bombers.add(bomberOne);

		gameLogic = new GameLogic(bombers);
		//Bomber bomberTwo = new Bomber();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		update();
		batch.begin();
		backGround.render(batch);
		board.render(batch);
		gameLogic.render(batch);
		batch.end();
	}

	public void update(){
		gameLogic.checkEvent();
		//тут будет описана логика обновлений игры
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
