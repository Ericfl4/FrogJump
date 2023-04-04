package com.mygdx.frogjump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

public class Frog extends Game {
	SpriteBatch batch;
	BitmapFont smallFont, bigFont;
	AssetManager manager;
	int topScore;
	int lastScore;
	boolean record = false;
	public void create() {

		batch = new SpriteBatch();
// Create bitmap fonts from TrueType font
		FreeTypeFontGenerator generator = new
				FreeTypeFontGenerator(Gdx.files.internal("Grozel.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new
				FreeTypeFontGenerator.FreeTypeFontParameter();
		params.size = 22;
		params.borderWidth = 2;
		params.borderColor = Color.BLACK;
		params.color = Color.WHITE;
		smallFont = generator.generateFont(params); // font size 22 pixels
		params.size = 50;
		params.borderWidth = 5;
		bigFont = generator.generateFont(params); // font size 50 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		manager = new AssetManager();
		manager.load("frog.png", Texture.class);
		manager.load("frog_jump1.png", Texture.class);
		manager.load("frog_jump2.png", Texture.class);
		manager.load("rock.png", Texture.class);
		manager.load("rock2.png", Texture.class);
		manager.load("rock3.png", Texture.class);
		manager.load("rock4.png", Texture.class);
		manager.load("background.png", Texture.class);
		manager.load("suelo.png", Texture.class);
		manager.load("nubes.png", Texture.class);
		manager.load("cielo.png", Texture.class);
		manager.load("medal.png", Texture.class);
		manager.load("flap.wav", Sound.class);
		manager.load("win.wav", Sound.class);

		manager.finishLoading();

		this.setScreen(new MainMenuScreen(this));
		topScore = 0;
		lastScore = 0;
	}
}
