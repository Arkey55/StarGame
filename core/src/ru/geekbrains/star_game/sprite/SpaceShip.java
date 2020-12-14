package ru.geekbrains.star_game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.star_game.base.Sprite;
import ru.geekbrains.star_game.math.Rect;

public class SpaceShip extends Sprite {

    private static final float SPEED = 0.003f;
    private Vector2 target;
    private Vector2 velocity;
    private Vector2 temp;

    public SpaceShip(TextureRegion region) {
        super(new TextureRegion(region));
        target = new Vector2();
        velocity = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);
        setHeightProportion(0.2f);
    }

    @Override
    public void update(float delta) {
        velocity.set(target.cpy().sub(pos)).setLength(SPEED);
        temp.set(target);
        if (temp.sub(pos).len() < SPEED)
            this.pos.set(target);
        else
            this.pos.add(velocity);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.target.set(touch);
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                this.target.add(0, 0.05f);
                break;
            case Input.Keys.DOWN:
                this.target.add(0, -0.05f);
                break;
            case Input.Keys.RIGHT:
                this.target.add(0.05f, 0);
                break;
            case Input.Keys.LEFT:
                this.target.add(-0.05f, 0);
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode: " + keycode);
        return false;
    }
}
