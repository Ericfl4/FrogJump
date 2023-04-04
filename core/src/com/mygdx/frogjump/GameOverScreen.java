package com.mygdx.frogjump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
    final Frog game;
    OrthographicCamera camera;
    public GameOverScreen(final Frog gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1440, 810);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0, 0, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(game.manager.get("background.png",
                Texture.class), 0, 0);
        game.batch.draw(game.manager.get("medal.png", Texture.class), 500,
                250);
        if (game.record){
            game.smallFont.draw(game.batch, "NEW RECORD! ", 620, 250);
        }
        game.bigFont.draw(game.batch, "GAME OVER! ", 530, 500);
        game.bigFont.draw(game.batch, "Final Score: " + game.lastScore, 500, 380);
        game.bigFont.draw(game.batch, "Top Score: " + game.topScore, 555, 300);



        game.batch.end();
        if (Gdx.input.justTouched()) {
            game.record=false;
            game.setScreen(new com.mygdx.frogjump.MainMenuScreen(game));
            dispose();
        }
    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void show() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void dispose() {
    }
}
