package ru.geekbrains.star_game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture background;

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
        background = new Texture("kosmos.jpg");
        pos = new Vector2();
        target = new Vector2();
        velocity = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        moveShip();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img, pos.x, pos.y, 128, 128);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        background.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.screenX = screenX - 64;
        this.screenY = Gdx.graphics.getHeight() - screenY - 64;
        return false;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
}
