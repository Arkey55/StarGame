package ru.geekbrains.star_game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture background;
    private Vector2 currentPosition;

    private float speed = 1f;
    private int screenX;
    private int screenY;
    Vector2 target;
    Vector2 direction;

    private void moveShipToMousePosition (){
        target = new Vector2(screenX, screenY);
        direction = new Vector2(target.sub(currentPosition)).nor();
        currentPosition.add(direction.scl(speed));
        //не знаю как остановить, продолжает вибрировать в точке назначения
    }

    @Override
    public void show() {
        super.show();
        img = new Texture("spaceship2.png");
        background = new Texture("kosmos.jpg");
        currentPosition = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img, currentPosition.x, currentPosition.y, 128, 128);
        moveShipToMousePosition();
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
        return super.touchDown(screenX, screenY, pointer, button);
    }
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.W:
                currentPosition.y += 10;
                break;
            case Input.Keys.S:
                currentPosition.y -= 10;
                break;
            case Input.Keys.A:
                currentPosition.x -= 10;
                break;
            case Input.Keys.D:
                currentPosition.x += 10;
                break;
        }
        return super.keyDown(keycode);
    }
}
