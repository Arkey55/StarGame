package ru.geekbrains.star_game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.pool.BulletPool;
import ru.geekbrains.star_game.sprite.Bullet;

public class Ship extends Sprite {

    protected TextureRegion bulletRegion;
    protected Sound bulletSound;
    protected Vector2 bulletPos;
    protected Vector2 bulletV;
    protected float bulletHeight;
    protected int damage;
    protected int hp;
    private float reloadTimer;
    protected float reloadInterval = 0.2f;
    protected Vector2 v;
    protected Vector2 v0;
    protected Rect worldBounds;
    private final BulletPool bulletPool;

    public Ship(BulletPool bulletPool) {
        this.bulletPool = bulletPool;
    }

    public Ship(TextureRegion region, int rows, int cols, int frames, BulletPool bulletPool) {
        super(region, rows, cols, frames);
        this.bulletPool = bulletPool;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);

        reloadTimer += delta;
        if (reloadTimer >= reloadInterval){
            reloadTimer = 0;
            shoot();
        }
    }

    private void shoot(){
        bulletSound.play(0.3f);
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, damage);
    }
}
