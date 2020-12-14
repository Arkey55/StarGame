package ru.geekbrains.star_game.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseButton extends Sprite {

    private static final float SCALE = 0.9f;
    private int pointer;
    private boolean isPressed;

    public BaseButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (isPressed || !isMe(touch)){
            return false;
        }
        this.pointer = pointer;
        isPressed = true;
        setScale(SCALE);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (this.pointer != pointer || !isPressed){
            return false;
        }
        isPressed = false;
        setScale(1f);
        if (isMe(touch)){
            action();
            return false;
        }
        return false;
    }

    public abstract void action();
}
