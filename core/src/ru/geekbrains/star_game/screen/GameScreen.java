package ru.geekbrains.star_game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.geekbrains.star_game.base.BaseScreen;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.pool.BulletPool;
import ru.geekbrains.star_game.pool.EnemyPool;
import ru.geekbrains.star_game.pool.ExplosionPool;
import ru.geekbrains.star_game.sprite.Background;
import ru.geekbrains.star_game.sprite.Bullet;
import ru.geekbrains.star_game.sprite.ButtonExit;
import ru.geekbrains.star_game.sprite.ButtonNewGame;
import ru.geekbrains.star_game.sprite.Enemy;
import ru.geekbrains.star_game.sprite.GameOver;
import ru.geekbrains.star_game.sprite.SpaceShip;
import ru.geekbrains.star_game.sprite.Star;
import ru.geekbrains.star_game.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private final static int STAR_COUNT = 128;
    private enum State {PLAYING, GAME_OVER}
    private Texture bgImg;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private EnemyPool enemyPool;
    private EnemyEmitter enemyEmitter;
    private SpaceShip spaceShip;
    private Music music;
    private Sound bulletSound;
    private Sound explosionSound;
    private State state;
    private GameOver gameOver;
    private ButtonNewGame btnNG;
    private final Game game;

    public GameScreen(Game game) {
        this.game = game;
    }

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
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        explosionPool = new ExplosionPool(atlas, explosionSound);
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds);
        enemyEmitter = new EnemyEmitter(atlas, worldBounds, bulletSound, enemyPool);
        spaceShip = new SpaceShip(atlas, bulletPool, explosionPool);
        gameOver = new GameOver(atlas);
        btnNG = new ButtonNewGame(atlas, game);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        state = State.PLAYING;

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision();
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
        gameOver.resize(worldBounds);
        btnNG.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bgImg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        enemyPool.dispose();
        music.dispose();
        bulletSound.dispose();
        explosionSound.dispose();
        spaceShip.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            spaceShip.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            spaceShip.keyUp(keycode);
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            spaceShip.touchDown(touch, pointer, button);
        } else if (state == State.GAME_OVER){
            btnNG.touchDown(touch, pointer, button);
        }

        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            spaceShip.touchUp(touch, pointer, button);
        } else if (state == State.GAME_OVER){
            btnNG.touchUp(touch, pointer, button);
        }
        return false;
    }

    private void update(float delta){
        for (Star star : stars){
            star.update(delta);
        }
        explosionPool.updateActiveObjects(delta);
        if (state == State.PLAYING) {
            bulletPool.updateActiveObjects(delta);
            enemyPool.updateActiveObjects(delta);
            spaceShip.update(delta);
            enemyEmitter.generate(delta);
        }
    }

    private void checkCollision(){
        if (state == State.GAME_OVER){
            return;
        }
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for(Enemy enemy : enemyList){
            float minDist = enemy.getHalfWidth() + spaceShip.getHalfWidth();
            if (spaceShip.pos.dst(enemy.pos) < minDist){
                enemy.destroy();
                spaceShip.doDamage(enemy.getDamage());
            }
            for(Bullet bullet : bulletList){
                if (bullet.getOwner() == spaceShip && enemy.isBulletCollision(bullet)){
                    if (enemy.getTop() - enemy.getHalfHeight() < worldBounds.getTop()){
                        enemy.doDamage(bullet.getDamage());
                        bullet.destroy();
                    }
                }
            }
        }
        for (Bullet bullet : bulletList){
            if (bullet.getOwner() != spaceShip && spaceShip.isBulletCollision(bullet)){
                spaceShip.doDamage(bullet.getDamage());
                bullet.destroy();
            }
        }
        if (spaceShip.isDestroyed()){
            state = State.GAME_OVER;
        }
    }

    private void freeAllDestroyed(){
        bulletPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
    }

    private void draw(){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        if (state == State.PLAYING) {
            bulletPool.drawActiveObjects(batch);
            enemyPool.drawActiveObjects(batch);
            spaceShip.draw(batch);
        } else if (state == State.GAME_OVER){
            gameOver.draw(batch);
            btnNG.draw(batch);
        }
        explosionPool.drawActiveObjects(batch);
        batch.end();
    }
}
