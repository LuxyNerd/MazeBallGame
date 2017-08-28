package org.emine.turcan.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import org.emine.turcan.MazeBall;

/**
 * Created by emine on 04.02.17.
 */

public class UsernameInputListener implements Input.TextInputListener {
    private final MazeBall game;

    public UsernameInputListener(MazeBall game) {
        this.game = game;
    }

    @Override
    public void input(String text) {
        if(text.equals("")) {
            Gdx.input.getTextInput(new UsernameInputListener(game), "Choose an username", "", "Username");
        } else {
            game.prefs.putString("userName", text);
            game.prefs.flush();
        }
    }

    @Override
    public void canceled() {
        Gdx.input.getTextInput(new UsernameInputListener(game), "Choose an username", "", "Username");
    }
}
