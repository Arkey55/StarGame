package ru.geekbrains.star_game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.base.Sprite;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.math.Rnd;

public class Star extends Sprite {

    private static final float MIN_HEIGHT = 0.003f;
    private static final float MAX_HEIGHT = 0.008f;

    private final Vector2 v;
    private Rect worldBounds;

    private boolean isMax = true;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        float vx = Rnd.nextFloat(-0.005f, 0.005f);
        float vy = Rnd.nextFloat(-0.15f, -0.005f);
        v = new Vector2(vx, vy);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x, y);
        setHeightProportion(Rnd.nextFloat(MIN_HEIGHT, MAX_HEIGHT));
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        blinkStar();
    }

    private void blinkStar(){
        float height;
        if (isMax) {
            height = getHeight() + Rnd.nextFloat(0.00001f, 0.0001f);
            if (height > MAX_HEIGHT){
                isMax = false;
            }
        } else {
            height = getHeight() - Rnd.nextFloat(0.00001f, 0.0001f);
            if (height < MIN_HEIGHT){
                isMax = true;
            }
        }
        setHeightProportion(height);
    }
}
