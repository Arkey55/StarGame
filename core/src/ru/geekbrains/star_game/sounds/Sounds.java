package ru.geekbrains.star_game.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    private Music bgMusic;
    private Sound bulletSound;

    public Sounds() {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
    }

    public void playMusic(){
        bgMusic.play();
    }

    public void playBulletSound(){
        bulletSound.play(1.0f);
    }

    public void dispose(){
        bgMusic.dispose();
        bulletSound.dispose();
    }
}
