package ru.geekbrains.star_game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.star_game.base.Sprite;
import ru.geekbrains.star_game.math.Rect;

public class SpaceShip extends Sprite {

    private static final float SPEED = 0.003f;

    public SpaceShip(Texture region) {
        super(new TextureRegion(region));
    }

    public void moveShip(Vector2 target){
        Vector2 velocity = new Vector2(target.cpy().sub(this.pos).setLength(SPEED));
        Vector2 temp = new Vector2(target);
        if (temp.sub(pos).len() > velocity.len()){
            this.pos.add(velocity);
        } else {
            this.pos.set(target);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight()/5);
        this.pos.set(worldBounds.pos);
    }
}
