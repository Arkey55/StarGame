package ru.geekbrains.star_game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.star_game.base.Sprite;
import ru.geekbrains.star_game.math.Rect;

public class GameOver extends Sprite {
    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.08f);
    }
}
