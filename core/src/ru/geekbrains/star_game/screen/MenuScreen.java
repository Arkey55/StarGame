package ru.geekbrains.star_game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.base.BaseScreen;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.sprite.Background;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Background background;

    private Texture img;

    private static final float speed = 2f;
    private int screenX;
    private int screenY;

    private Vector2 pos;
    private Vector2 target;
    private Vector2 velocity;
    private Vector2 temp;

    private void moveShip(){
        target.set(screenX, screenY);
        velocity.set(target.cpy().sub(pos).setLength(speed));
        temp.set(target);
        if (temp.sub(pos).len() > velocity.len()){
            pos.add(velocity);
        } else {
            pos.set(target);
        }
    }

    @Override
    public void show() {
        super.show();
        img = new Texture("spaceship2.png");
        bg = new Texture("kosmos2.jpg");
        background = new Background(bg);
        pos = new Vector2();
        target = new Vector2();
        velocity = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
//        moveShip();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        batch.draw(img, pos.x - 0.1f, pos.y - 0.1f, 0.2f, 0.2f);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        bg.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
}
