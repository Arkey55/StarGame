package ru.geekbrains.star_game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.pool.BulletPool;
import ru.geekbrains.star_game.pool.ExplosionPool;
import ru.geekbrains.star_game.sprite.Bullet;
import ru.geekbrains.star_game.sprite.Explosion;

public class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected TextureRegion bulletRegion;
    protected Sound bulletSound;
    protected Vector2 bulletPos;
    protected Vector2 bulletV;
    protected float bulletHeight;
    protected int damage;
    protected int hp;
    protected float reloadInterval;
    protected float reloadTimer;
    private float damageAnimateTimer;
    protected Vector2 v;
    protected Vector2 v0;
    protected Rect worldBounds;
    private final BulletPool bulletPool;
    private final ExplosionPool explosionPool;

    public Ship(BulletPool bulletPool, ExplosionPool explosionPool) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;
    }

    public Ship(TextureRegion region, int rows, int cols, int frames, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(region, rows, cols, frames);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval){
            reloadTimer = 0;
            shoot();
        }
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL){
            frame = 0;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public void doDamage(int damage){
        this.hp -= damage;
        if (hp <= 0){
            hp = 0;
            destroy();
        }
        frame = 1;
        damageAnimateTimer = 0;
    }

    public int getDamage() {
        return damage;
    }

    private void shoot(){
        bulletSound.play(0.3f);
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, damage);
    }



    private void boom(){
        Explosion explosion = explosionPool.obtain();
        explosion.set(this.pos, getHeight());
    }
}
