package ru.geekbrains.star_game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.star_game.base.BaseScreen;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.sounds.Sounds;
import ru.geekbrains.star_game.sprite.Background;
import ru.geekbrains.star_game.sprite.ButtonExit;
import ru.geekbrains.star_game.sprite.ButtonPlay;
import ru.geekbrains.star_game.sprite.Star;

public class MenuScreen extends BaseScreen {

    private final static int STAR_COUNT = 256;
    private Texture bgImg;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;
    private ButtonExit btnExit;
    private ButtonPlay btnPlay;
    private final Game game;


    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bgImg = new Texture("textures/bg.png");
        background = new Background(bgImg);
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }

        btnExit = new ButtonExit(atlas);
        btnPlay = new ButtonPlay(atlas, game);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        bgImg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        btnExit.resize(worldBounds);
        btnPlay.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        btnExit.touchDown(touch, pointer, button);
        btnPlay.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        btnExit.touchUp(touch, pointer, button);
        btnPlay.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta){
        for (Star star : stars){
            star.update(delta);
        }
    }

    private void draw (){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }

        btnExit.draw(batch);
        btnPlay.draw(batch);
        batch.end();
    }
}
