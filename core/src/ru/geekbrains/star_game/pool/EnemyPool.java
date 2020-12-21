package ru.geekbrains.star_game.pool;

import ru.geekbrains.star_game.base.SpritesPool;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.sprite.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {
    private final BulletPool bulletPool;
    private final Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public Enemy newObject() {
        return new Enemy(bulletPool, worldBounds);
    }
}
