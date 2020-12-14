package ru.geekbrains.star_game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.base.BaseScreen;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.sprite.Background;
import ru.geekbrains.star_game.sprite.SpaceShip;
import ru.geekbrains.star_game.sprite.Star;

public class GameScreen extends BaseScreen {

    private final static int STAR_COUNT = 64;
    private TextureAtlas atlas;
    private Texture bgImg;
    private Background background;
    private SpaceShip spaceShip;
    private Star[] stars;


    @Override
    public void show() {
        super.show();
        bgImg = new Texture("textures/bg.png");
        background = new Background(bgImg);
        spaceShip = new SpaceShip(atlas);
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        spaceShip.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        bgImg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        spaceShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    private void update(float delta){
        for (Star star : stars){
            star.update(delta);
        }
        spaceShip.update(delta);
    }

    private void draw(){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        spaceShip.draw(batch);
        batch.end();
    }
}
