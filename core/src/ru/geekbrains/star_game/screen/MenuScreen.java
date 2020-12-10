package ru.geekbrains.star_game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.star_game.base.BaseScreen;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.sprite.Background;
import ru.geekbrains.star_game.sprite.SpaceShip;

public class MenuScreen extends BaseScreen {

    private Texture bgImg;
    private Background background;
    private Texture shipImg;
    private SpaceShip spaceShip;
    private Vector2 touch;

    @Override
    public void show() {
        super.show();
        bgImg = new Texture("OpenSpace.jpg");
        background = new Background(bgImg);
        shipImg = new Texture("spaceship.png");
        spaceShip = new SpaceShip(shipImg);
        touch = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spaceShip.moveShip(touch);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        spaceShip.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        bgImg.dispose();
        shipImg.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        spaceShip.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch = touch;
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
}
