package ru.geekbrains.star_game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.star_game.base.Ship;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.pool.BulletPool;
import ru.geekbrains.star_game.pool.ExplosionPool;

public class Enemy extends Ship {

    private Vector2 startV;

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        super(bulletPool, explosionPool);
        this.worldBounds = worldBounds;
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletPos = new Vector2();
        this.startV = new Vector2(0, -0.3f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getTop() < worldBounds.getTop()){
            this.v.set(v0);
        } else {
            reloadTimer = reloadInterval - delta * 2;
        }
        bulletPos.set(pos.x, pos.y - getHalfHeight());
        if(getBottom() < worldBounds.getBottom()){
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            TextureRegion bulletRegion,
            Sound bulletSound,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            int hp,
            float reloadInterval,
            Vector2 v0,
            float height
    ){
        this.regions = regions;
        this.bulletRegion = bulletRegion;
        this.bulletSound = bulletSound;
        this.bulletHeight = bulletHeight;
        this.bulletV = bulletV;
        this.damage = damage;
        this.hp = hp;
        this.reloadInterval = reloadInterval;
        this.v0.set(v0);
        this.v.set(startV);
        setHeightProportion(height);
    }

    public boolean isBulletCollision(Bullet bullet){
        return !(bullet.getRight() < getLeft()
        || bullet.getLeft() > getRight()
        || bullet.getBottom() > getTop()
        || bullet.getTop() < pos.y);
    }
}
