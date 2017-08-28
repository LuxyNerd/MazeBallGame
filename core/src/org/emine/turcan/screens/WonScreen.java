package org.emine.turcan.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import org.emine.turcan.MazeBall;

import java.util.TreeMap;

/**
 * Created by emine on 04.02.17.
 */

public class WonScreen implements Screen {

    private final MazeBall game;
    private final long score;
    private Stage stage;
    private String levelPrefix;

    public WonScreen(MazeBall game, long score, String levelPrefix) {
        this.game = game;
        this.score = score;
        this.levelPrefix = levelPrefix;
    }

    @Override
    public void show() {

        TreeMap<Long, String> hiScoreMap = new TreeMap<Long, String>();

        for(int i = 0; i < 10; i++) {
            if(game.prefs.getLong(levelPrefix + "score" + i) > 0) {
                hiScoreMap.put(game.prefs.getLong(levelPrefix + "score" + i), game.prefs.getString(levelPrefix + "name" + i));
            }
        }

        hiScoreMap.put(Long.valueOf(score), game.prefs.getString("userName"));

        for(int i = 0; i < 10; i++) {
            if(hiScoreMap.keySet().size() > i) {
                Long key = (Long) hiScoreMap.keySet().toArray()[i];
                String value = hiScoreMap.get(key);
                game.prefs.putLong(levelPrefix + "score" + i, key.longValue());
                game.prefs.putString(levelPrefix + "name" + i, value);
                game.prefs.flush();
            }
        }


        stage = new Stage();
        stage.clear();

        Label titleLabel = new Label("You've won!", new Label.LabelStyle(game.fontTitle, new Color(255,255,255,1)));
        titleLabel.setPosition(Gdx.graphics.getWidth()/2 - titleLabel.getWidth()/2, Gdx.graphics.getHeight() - 100);
        stage.addActor(titleLabel);

        Label hiscoreLabel = new Label("Hiscore", new Label.LabelStyle(game.font, new Color(255,255,255,1)));
        hiscoreLabel.setPosition(Gdx.graphics.getWidth()/2 - hiscoreLabel.getWidth()/2, Gdx.graphics.getHeight() - 100 - titleLabel.getHeight());
        stage.addActor(hiscoreLabel);

        Label[] hiScoreLabels = new Label[10];

        for(int x = 0; x < 2; x++) {
            for(int y = 0; y < 5; y++) {
                int index = (x*5 + y);

                Long scoreI = Long.valueOf(0l);
                String userName = "";

                Color rowColor = new Color(255, 255, 255, 1);

                if(hiScoreMap.keySet().size() > index) {
                    scoreI = (Long) hiScoreMap.keySet().toArray()[index];
                    userName = hiScoreMap.get(scoreI);
                    if(this.score == scoreI) {
                        rowColor = new Color(0, 255, 0, 1);
                    }
                }


                String hiScoreString = "";
                if(scoreI != 0) {
                    hiScoreString = userName + " (" + game.utils.convertMillisToDateString(scoreI.longValue()) + ")";
                }

                int xCoord = 100 + (x * Gdx.graphics.getWidth() / 2);
                int yCoord = ((int) hiscoreLabel.getY()) - ((int) hiscoreLabel.getHeight()) - 50 - (y * 75);
                hiScoreLabels[index] = new Label((index+1) + ". " + hiScoreString, new Label.LabelStyle(game.font, rowColor));
                hiScoreLabels[index].setPosition(xCoord, yCoord);
                stage.addActor(hiScoreLabels[index]);
            }
        }

        Label continueLabel = new Label("Your Score: " + game.utils.convertMillisToDateString(score) + " - Touch to continue...", new Label.LabelStyle(game.font, new Color(255, 255, 255, 1)));
        continueLabel.setPosition(Gdx.graphics.getWidth()/2 - continueLabel.getWidth()/2, 50);
        stage.addActor(continueLabel);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.135f, .206f, .235f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        game.batch.begin();
        stage.draw();
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenu(game));
            dispose();
        }
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

        stage.dispose();
    }
}
