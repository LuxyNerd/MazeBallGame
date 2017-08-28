package org.emine.turcan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import org.emine.turcan.objects.UsernameInputListener;
import org.emine.turcan.objects.Utils;
import org.emine.turcan.screens.MainMenu;

/**
 * @author Emine Turcan [emine@turcan.de]
 * Version 1.2
 */

public class MazeBall extends Game {


	public SpriteBatch batch;
	public BitmapFont font;
    public BitmapFont fontTitle;
	public Preferences prefs;
    public Utils utils;

	public boolean level1done;
	public boolean level2done;
	public boolean level3done; // ab hier nicht mehr in Präsi
	public boolean level4done;
	public boolean level5done;
	public boolean level6done;

	@Override
	public void create () {
		prefs = Gdx.app.getPreferences("MazeBall");
        utils = new Utils();

        Gdx.input.getTextInput(new UsernameInputListener(this), "Choose an username", "", "Username");

		batch = new SpriteBatch();

		level1done = false;
		level2done = false;
		level3done = false;
		level4done = false;
		level5done = false;

		FileHandle fontFile = Gdx.files.internal("font/Calibri.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 42;
		font = generator.generateFont(parameter);

        FreeTypeFontGenerator.FreeTypeFontParameter parameterTitle = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterTitle.size = 72;
        fontTitle = generator.generateFont(parameterTitle);
        generator.dispose();

		this.setScreen(new MainMenu(this));


	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}