package ru.geekbrains.star_game;

import com.badlogic.gdx.Game;

import ru.geekbrains.star_game.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
}
