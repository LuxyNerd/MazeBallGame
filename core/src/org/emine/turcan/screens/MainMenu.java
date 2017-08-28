package org.emine.turcan.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.emine.turcan.MazeBall;



/**
 * Created by emine on 04.02.17.
 */

public class MainMenu implements Screen {


    private final MazeBall game;
    private Stage buttonStage;

    private TextureAtlas level1Atlas;
    private Skin level1Skin;
    private TextButton level1Button;

    private TextureAtlas level2Atlas;
    private Skin level2Skin;
    private TextButton level2Button;

    private TextureAtlas level3Atlas;
    private Skin level3Skin;
    private TextButton level3Button;

    /*Diese wurden nicht Präsentiert*/

    private TextureAtlas level4Atlas;
    private Skin level4Skin;
    private TextButton level4Button;

    private TextureAtlas level5Atlas;
    private Skin level5Skin;
    private TextButton level5Button;

    private TextureAtlas level6Atlas;
    private Skin level6Skin;
    private TextButton level6Button;



    public MainMenu(final MazeBall game) {

        this.game = game;
    }

    @Override
    public void show() {


        buttonStage = new Stage();
        buttonStage.clear();

        Label label = new Label("my MazeBall Game", new Label.LabelStyle(game.fontTitle, new Color(255,255,255,1)));
        label.setPosition(Gdx.graphics.getWidth()/2 - label.getWidth()/2, Gdx.graphics.getHeight() - 100);


        Gdx.input.setInputProcessor(buttonStage);
        buttonStage.addActor(label);


        //Level 1 Button
        level1Atlas = new TextureAtlas("button/button.pack");
        level1Skin = new Skin();
        level1Skin.addRegions(level1Atlas);
        TextButton.TextButtonStyle level1Style = new TextButton.TextButtonStyle();
        level1Style.up = level1Skin.getDrawable("buttonOff");
        level1Style.down = level1Skin.getDrawable("buttonOn");
        level1Style.font = game.font;

        level1Button = new TextButton("Level 1", level1Style);
        //** Button text and style **//
        level1Button.setHeight(Gdx.graphics.getHeight()/3);
        level1Button.setWidth(Gdx.graphics.getWidth()/4);

        level1Button.setPosition(Gdx.graphics.getWidth()/4-level1Button.getWidth()/2, Gdx.graphics.getHeight()/2 + Gdx.graphics.getHeight()/6 - label.getHeight() - 100);

        level1Button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameLevel1(game)); // bei diesem Level beginnt das Spiel
                dispose();
            }
        });

        buttonStage.addActor(level1Button);


        //Level 2 Button
        if(game.level1done) {
            level2Atlas = new TextureAtlas("button/button.pack");
        } else {
            level2Atlas = new TextureAtlas("button/button_disabled.pack");
        }

        level2Skin = new Skin();
        level2Skin.addRegions(level2Atlas);
        TextButton.TextButtonStyle level2Style = new TextButton.TextButtonStyle();
        level2Style.up = level2Skin.getDrawable("buttonOff");
        level2Style.down = level2Skin.getDrawable("buttonOn");
        level2Style.font = game.font;

        level2Button = new TextButton("Level 2", level2Style);
        if(!game.level1done) {
            level2Button.setTouchable(Touchable.disabled);
        }

        level2Button.setHeight(Gdx.graphics.getHeight()/3);
        level2Button.setWidth(Gdx.graphics.getWidth()/4);

        level2Button.setPosition(Gdx.graphics.getWidth()/4-level2Button.getWidth()/2, level1Button.getY() - 150);

        level2Button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameLevel2(game));
                dispose();
            }
        });
        buttonStage.addActor(level2Button);

        //Level 3 Button
        if(game.level2done) {
            level3Atlas = new TextureAtlas("button/button.pack");
        } else {
            level3Atlas = new TextureAtlas("button/button_disabled.pack");
        }

        level3Skin = new Skin();
        level3Skin.addRegions(level3Atlas);
        TextButton.TextButtonStyle level3Style = new TextButton.TextButtonStyle();
        level3Style.up = level3Skin.getDrawable("buttonOff");
        level3Style.down = level3Skin.getDrawable("buttonOn");
        level3Style.font = game.font;

        level3Button = new TextButton("Level 3", level3Style);
        if(!game.level2done) {
            level3Button.setTouchable(Touchable.disabled);
        }

        level3Button.setHeight(Gdx.graphics.getHeight()/3);
        level3Button.setWidth(Gdx.graphics.getWidth()/4);

        level3Button.setPosition(Gdx.graphics.getWidth()/4-level3Button.getWidth()/2, level2Button.getY() - 150);

        level3Button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameLevel3(game));
                dispose();
            }
        });
        buttonStage.addActor(level3Button);

        /*Ab hier sind nicht mehr in meiner Präsentation*/
        //Level 4 Button
        if(game.level3done) {
            level4Atlas = new TextureAtlas("button/button.pack");
        } else {
            level4Atlas = new TextureAtlas("button/button_disabled.pack");
        }

        level4Skin = new Skin();
        level4Skin.addRegions(level4Atlas);
        TextButton.TextButtonStyle level4Style = new TextButton.TextButtonStyle();
        level4Style.up = level4Skin.getDrawable("buttonOff");
        level4Style.down = level4Skin.getDrawable("buttonOn");
        level4Style.font = game.font;

        level4Button = new TextButton("Level 4", level4Style);
        if(!game.level3done) {
            level4Button.setTouchable(Touchable.disabled);
        }

        level4Button.setHeight(Gdx.graphics.getHeight()/3);
        level4Button.setWidth(Gdx.graphics.getWidth()/4);

        level4Button.setPosition(Gdx.graphics.getWidth()/2-level2Button.getWidth()/2, Gdx.graphics.getHeight()/2 + Gdx.graphics.getHeight()/6 - label.getHeight() - 100);

        level4Button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameLevel4(game));
                dispose();
            }
        });

        buttonStage.addActor(level4Button);

        //Level 5 Button
        if(game.level4done) {
            level5Atlas = new TextureAtlas("button/button.pack");
        } else {
            level5Atlas = new TextureAtlas("button/button_disabled.pack");
        }

        level5Skin = new Skin();
        level5Skin.addRegions(level5Atlas);
        TextButton.TextButtonStyle level5Style = new TextButton.TextButtonStyle();
        level5Style.up = level5Skin.getDrawable("buttonOff");
        level5Style.down = level5Skin.getDrawable("buttonOn");
        level5Style.font = game.font;

        level5Button = new TextButton("Level 5", level5Style);
        if(!game.level4done) {
            level5Button.setTouchable(Touchable.disabled);
        }

        level5Button.setHeight(Gdx.graphics.getHeight()/3);
        level5Button.setWidth(Gdx.graphics.getWidth()/4);

        level5Button.setPosition(Gdx.graphics.getWidth()/2-level2Button.getWidth()/2,level1Button.getY() - 150);

        level5Button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameLevel5(game));
                dispose();
            }
        });
        buttonStage.addActor(level5Button);

        //Level 6 Button
        if(game.level5done) {
            level6Atlas = new TextureAtlas("button/button.pack");
        } else {
            level6Atlas = new TextureAtlas("button/button_disabled.pack");
        }

        level6Skin = new Skin();
        level6Skin.addRegions(level6Atlas);
        TextButton.TextButtonStyle level6Style = new TextButton.TextButtonStyle();
        level6Style.up = level6Skin.getDrawable("buttonOff");
        level6Style.down = level6Skin.getDrawable("buttonOn");
        level6Style.font = game.font;

        level6Button = new TextButton("Level 6", level6Style);
        if(!game.level5done) {
            level6Button.setTouchable(Touchable.disabled);
        }

        level6Button.setHeight(Gdx.graphics.getHeight()/3);
        level6Button.setWidth(Gdx.graphics.getWidth()/4);

        level6Button.setPosition(Gdx.graphics.getWidth()/2-level3Button.getWidth()/2, level2Button.getY() - 150);

        level6Button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameLevel6(game));
                dispose();
            }
        });
        buttonStage.addActor(level6Button);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.135f, .206f, .235f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        buttonStage.act();
        game.batch.begin();
        buttonStage.draw();

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        buttonStage.dispose();
        level1Atlas.dispose();
        level1Skin.dispose();
        level2Atlas.dispose();
        level2Skin.dispose();
        level3Atlas.dispose();
        level3Skin.dispose();
        level4Atlas.dispose();
        level4Skin.dispose();
        level5Atlas.dispose();
        level5Skin.dispose();
        level6Atlas.dispose();
        level6Skin.dispose();
    }
}
