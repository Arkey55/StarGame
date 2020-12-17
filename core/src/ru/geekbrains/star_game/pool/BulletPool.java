package ru.geekbrains.star_game.pool;

import ru.geekbrains.star_game.base.SpritesPool;
import ru.geekbrains.star_game.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    public Bullet newObject() {
        return new Bullet();
    }
}
