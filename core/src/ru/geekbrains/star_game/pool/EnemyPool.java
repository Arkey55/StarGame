package ru.geekbrains.star_game.pool;

import ru.geekbrains.star_game.base.SpritesPool;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.sprite.Enemy;
import ru.geekbrains.star_game.sprite.Explosion;

public class EnemyPool extends SpritesPool<Enemy> {
    private final BulletPool bulletPool;
    private final ExplosionPool explosionPool;
    private final Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, worldBounds);
    }
}
