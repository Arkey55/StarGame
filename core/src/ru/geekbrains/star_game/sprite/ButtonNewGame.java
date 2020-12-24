package ru.geekbrains.star_game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.base.BaseButton;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.screen.GameScreen;

public class ButtonNewGame extends BaseButton {

    private static final float HEIGHT = 0.04f;
    private static final float MARGIN_BOTTOM = 0.3f;
    private static final float MARGIN_LEFT = 0.2f;
    private final Game game;

    public ButtonNewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + MARGIN_BOTTOM);
        setLeft(worldBounds.getLeft() + MARGIN_LEFT);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }
}
