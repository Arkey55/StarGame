package ru.geekbrains.star_game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.base.BaseScreen;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.pool.BulletPool;
import ru.geekbrains.star_game.sounds.Sounds;
import ru.geekbrains.star_game.sprite.Background;
import ru.geekbrains.star_game.sprite.Bullet;
import ru.geekbrains.star_game.sprite.SpaceShip;
import ru.geekbrains.star_game.sprite.Star;

public class GameScreen extends BaseScreen {

    private final static int STAR_COUNT = 128;
    private Texture bgImg;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;
    private BulletPool bulletPool;
    private SpaceShip spaceShip;


    @Override
    public void show() {
        super.show();
        bgImg = new Texture("textures/bg.png");
        background = new Background(bgImg);
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }

        bulletPool = new BulletPool();
        spaceShip = new SpaceShip(atlas, bulletPool);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        spaceShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bgImg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        spaceShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        spaceShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        spaceShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        spaceShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta){
        for (Star star : stars){
            star.update(delta);
        }
        bulletPool.updateActiveObjects(delta);
        spaceShip.update(delta);

    }

    private void freeAllDestroyed(){
        bulletPool.freeAllDestroyedActiveObjects();
    }

    private void draw(){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPool.drawActiveObjects(batch);
        spaceShip.draw(batch);
        batch.end();
    }
}
