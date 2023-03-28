package com.mygdx.frogjump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen implements Screen {
    final Frog game;
    OrthographicCamera camera;
    Stage stage;
    Player player;
    boolean dead;
    Array<Rock> obstacles;
    long lastObstacleTime;
    float score;
    int xSuelo = 0;
    int xNubes = 0;
    int xCielo = 0;
    int sumarSaltoExtra = 0;
    public GameScreen(final Frog gam) {
        this.game = gam;
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1440, 810);
        player = new com.mygdx.frogjump.Player();
        player.setManager(game.manager);
        stage = new Stage();
        stage.getViewport().setCamera(camera);
        stage.addActor(player);
        // create the obstacles array and spawn the first obstacle
        obstacles = new Array<Rock>();
        spawnObstacle();
        score = 0;
        player.setY( 135 );
    }

    private void spawnObstacle() {
// Calcula la alçada de l'obstacle aleatòriament
// Crea dos obstacles: Una tubería superior i una inferior
        Rock rock1 = new Rock();
        rock1.setX(1500);
        rock1.setY(125);
        rock1.setManager(game.manager);
        obstacles.add(rock1);
        stage.addActor(rock1);
        lastObstacleTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
    }

    @Override
    public void render(float delta) {
        dead = false;
        xSuelo-=20;
        xNubes-=5;
        xCielo-=1;
        // clear the screen with a color
        ScreenUtils.clear(0.3f, 0.8f, 0.8f, 1);
// tell the camera to update its matrices.
        camera.update();
// tell the SpriteBatch to render in the
// coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);
// begin a new batch
        if (xSuelo==-1440){
            xSuelo=0;
        }
        if (xNubes==-1440){
            xNubes=0;
        }
        if (xCielo==-1440){
            xCielo=0;
        }

        game.batch.begin();
        game.batch.draw(game.manager.get("suelo.png", Texture.class), xSuelo,
                0);
        game.batch.draw(game.manager.get("suelo.png", Texture.class), xSuelo+1440,
                0);
        game.batch.draw(game.manager.get("nubes.png", Texture.class), xNubes,
                210);
        game.batch.draw(game.manager.get("nubes.png", Texture.class), xNubes+1440,
                210);
        game.batch.draw(game.manager.get("cielo.png", Texture.class), xCielo,
                543);
        game.batch.draw(game.manager.get("cielo.png", Texture.class), xCielo+1440,
                543);
        game.batch.end();
// Stage batch: Actors
        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.draw();
        // process user input
        if (Gdx.input.justTouched()) {
            boolean salto = player.impulso();
            if (salto){
                game.manager.get("flap.wav", Sound.class).play();
            }
        }

        stage.act();
// Comprova que el jugador no es surt de la pantalla.
// Si surt per la part inferior, game over

        if (player.getBounds().y < 135) {
            player.setY( 135 );
            player.ground=true;
            player.saltoDoble=false;
        }
        if (player.getBounds().y > 135) {
            player.ground=false;
        }
        if (player.getBounds().y > 730) {
            player.setY( 730 );

        }

        if (player.ground){
            player.setSize(113,92);
        } else {
            player.setSize(124,158);
        }

        // Comprova si cal generar un obstacle nou
        if ( (TimeUtils.nanosToMillis(TimeUtils.nanoTime())) - lastObstacleTime > ((int) (Math.random()*20000+450))) {
            spawnObstacle();
        }
// Comprova si les tuberies colisionen amb el jugador
        Iterator<Rock> iter = obstacles.iterator();
        while (iter.hasNext()) {
            Rock rock = iter.next();
            if (rock.getBounds().overlaps(player.getBounds())) {
                dead = true;
            }
        }
// Treure de l'array les tuberies que estan fora de pantalla
        iter = obstacles.iterator();
        while (iter.hasNext()) {
            Rock rock = iter.next();
            if (rock.getX() <= 100) {
                score++;
                sumarSaltoExtra++;
                if (sumarSaltoExtra==10){
                    sumarSaltoExtra=0;
                    player.saltos++;
                }
            }
            if (rock.getX() < 100) {
                obstacles.removeValue(rock, true);
            }
            if (rock.getX() < -200) {
                rock.remove();
            }
        }
        game.batch.begin();
        game.smallFont.draw(game.batch, "Score: " + (int)score, 10, 770);
        game.smallFont.draw(game.batch, "Saltos extras: " + player.saltos, 1200, 770);
        game.batch.end();


//La puntuació augmenta amb el temps de joc
        if(dead)
        {
            game.lastScore = (int)score;
            if(game.lastScore > game.topScore) {
                game.manager.get("win.wav", Sound.class).play();
                game.topScore = game.lastScore;
                game.record=true;
            } else {
                game.manager.get("fail.wav", Sound.class).play();

            }
            game.setScreen(new GameOverScreen(game));
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
