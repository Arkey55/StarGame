package ru.geekbrains.star_game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.star_game.base.Ship;
import ru.geekbrains.star_game.math.Rect;
import ru.geekbrains.star_game.pool.BulletPool;

public class Enemy extends Ship {
    public Enemy(BulletPool bulletPool, Rect worldBounds) {
        super(bulletPool);
        this.worldBounds = worldBounds;
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(onScreen(v), delta);
        if (getTop() == worldBounds.getTop()){  //не срабатывает
            reloadTimer = reloadInterval;
        } else {
            reloadTimer += delta;
        }
        if (reloadTimer >= reloadInterval){
            reloadTimer = 0;
            shoot();
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
        this.v.set(v0);
        setHeightProportion(height);
    }

    public Vector2 onScreen(Vector2 v){
        Vector2 temp = new Vector2();
        if (getTop() > worldBounds.getTop()){
            temp.set(0f, -0.7f);
            if (getTop() >= worldBounds.getTop() - 0.01 && getTop() <= worldBounds.getTop() + 0.01f){
                shoot();
            }
            return temp;
        }
        return v;
    }
}
