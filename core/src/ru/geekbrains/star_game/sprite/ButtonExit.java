package ru.geekbrains.star_game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.star_game.base.BaseButton;
import ru.geekbrains.star_game.math.Rect;

public class ButtonExit extends BaseButton {

    private static float HEIGHT = 0.18f;
    private static float MARGIN = 0.05f;

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + MARGIN);
        setRight(worldBounds.getRight() - MARGIN);
    }
}
