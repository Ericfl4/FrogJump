package com.mygdx.frogjump;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.frogjump.Frog;
import com.mygdx.frogjump.GameScreen;

public class MainMenuScreen implements Screen {
    final Frog game;
    OrthographicCamera camera;
    public MainMenuScreen(final Frog gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1440, 810);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(game.manager.get("background.png",
                Texture.class), 0, 0);
        game.bigFont.draw(game.batch, "Welcome to FrogJump!!!", 360, 500);
        game.bigFont.draw(game.batch, "Tap anywhere to begin!", 360, 380);
        game.batch.end();
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
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
