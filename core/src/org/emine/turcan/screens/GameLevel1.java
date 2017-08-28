package org.emine.turcan.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.TimeUtils;

import org.emine.turcan.MazeBall;
import org.emine.turcan.objects.Hole;
import org.emine.turcan.objects.Wall;

/**
 * Created by emine on 04.02.17.
 */

public class GameLevel1 implements Screen {

    final MazeBall game;
    public BitmapFont font;
    long startTime;

    //für den Ball
    private Texture ballImage;
    private Texture wallImage;
    private Texture holeImage;
    private Texture winHoleImage;
    private Sound holeSound;
    private Sound winSound;
    private float renderX;
    private float renderY;

    private float speed = 1.0f; // Geschwindigkeit der Kugel

    private int startX = 30;
    private int startY = 500;

    private int holeSize = 70;

    //Walls
    Wall[] walls = new Wall[14];

    //Holes
    Hole[] holes = new Hole[5];

    //WinHole
    Hole winHole;


    public GameLevel1(final MazeBall game) {
        this.game = game;
        startTime = TimeUtils.millis();
        FileHandle fontFile = Gdx.files.internal("font/Calibri.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        font = generator.generateFont(parameter);

        //Ball Bild laden
        ballImage = new Texture(Gdx.files.internal("ball.png"));
        wallImage = new Texture(Gdx.files.internal("wallbrown.png"));
        holeImage = new Texture(Gdx.files.internal("hole.png"));
        winHoleImage = new Texture(Gdx.files.internal("portal.png"));
        holeSound = Gdx.audio.newSound(Gdx.files.internal("hole.wav"));
        winSound = Gdx.audio.newSound(Gdx.files.internal("win.wav"));
        renderX = startX;
        renderY = startY;
        int wallStrength = 20;
        int blockLength = Gdx.graphics.getWidth() / 7;

        //Create Wall
        //Außenwände
        walls[0] = new Wall(0, 0, wallStrength, Gdx.graphics.getHeight());
        walls[1] = new Wall(0, 0, Gdx.graphics.getWidth(), wallStrength);
        walls[2] = new Wall(wallStrength, Gdx.graphics.getHeight()-wallStrength, Gdx.graphics.getWidth(), wallStrength); //oben
        walls[3] = new Wall(Gdx.graphics.getWidth()-wallStrength, wallStrength, wallStrength, Gdx.graphics.getWidth()); // rechts
        //1. V links nach rechts
        walls[4] = new Wall(blockLength, blockLength, wallStrength, 4*blockLength);
        //2. V
        walls[5] = new Wall(2*blockLength, 0, wallStrength, 3*blockLength);
        //3. V
        walls[6] = new Wall(3*blockLength, blockLength, wallStrength, 2*blockLength);
        //4. V
        walls[7] = new Wall(4*blockLength, 3*blockLength, wallStrength, blockLength);
        //5. V
        walls[8] = new Wall(5*blockLength, 2*blockLength, wallStrength, 3*blockLength);
        //6. V
        walls[9] = new Wall(6*blockLength, blockLength, wallStrength, 3*blockLength);
        //1. H unten nach oben
        walls[10] = new Wall(3*blockLength, blockLength, 3*blockLength, wallStrength);
        //2. H unten nach oben
        walls[11] = new Wall(4*blockLength, 2*blockLength, blockLength, wallStrength);
        //3. H unten nach oben
        walls[12] = new Wall(3*blockLength, 3*blockLength, blockLength, wallStrength);
        //4. H unten nach oben
        walls[13] = new Wall(2*blockLength, 4*blockLength-25, 2*blockLength, wallStrength);


        holes[0] = new Hole(blockLength+20, blockLength+20, holeSize, holeSize);
        holes[1] = new Hole(2*blockLength, 3*blockLength+5, holeSize+10, holeSize+10);
        holes[3] = new Hole(2*blockLength+20, blockLength+10, holeSize, holeSize);
        holes[2] = new Hole(4*blockLength, blockLength+20, holeSize, holeSize);
        holes[4] = new Hole(6*blockLength+20, blockLength, holeSize, holeSize);


        winHole = new Hole(Gdx.graphics.getWidth()-130, Gdx.graphics.getHeight()-750, 60, 60);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.135f, .206f, .235f, 1); // red, green, blue and alpha component of that color, each within the range [0, 1]
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //prüft erst, ob die geg Richtung möglich ist(If). falls nicht wird geprüft, ob Beweg. auf der X-Achse(1.ElseIf), wenn das auch nicht möglich ist, dann wird das 2. ElseIf geprüft
        if(canMoveBall(renderX + (Gdx.input.getAccelerometerY()*speed), renderY - (Gdx.input.getAccelerometerX()*speed))) {
            renderX += Gdx.input.getAccelerometerY() * speed;
            renderY -= Gdx.input.getAccelerometerX() * speed;
        } else if (canMoveBall(renderX + (Gdx.input.getAccelerometerY() * speed), renderY)) {
            renderX += Gdx.input.getAccelerometerY() * speed;
        } else if (canMoveBall(renderX, renderY - (Gdx.input.getAccelerometerX() * speed))) {
            renderY -= Gdx.input.getAccelerometerX() * speed;
        }



        game.batch.begin();
        drawHoles();
        game.batch.draw(ballImage,renderX,renderY,50,50);
        drawWalls();
        font.draw(game.batch, game.utils.convertMillisToDateString(TimeUtils.timeSinceMillis(startTime)), 50, Gdx.graphics.getHeight() - 50);
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
        font.dispose();
        ballImage.dispose();
        wallImage.dispose();
        holeImage.dispose();
        holeSound.dispose();
        winSound.dispose();
        winHoleImage.dispose();
    }

    private void drawWalls() {
        for(Wall wall : walls) {
            game.batch.draw(wallImage, wall.xLL, wall.yLL, wall.width, wall.height);
        }
    }


    private void drawHoles() {
        for(Hole hole : holes) {
            game.batch.draw(holeImage, hole.xLL, hole.yLL, hole.width, hole.height);
        }
        game.batch.draw(winHoleImage,winHole.xLL, winHole.yLL, winHole.width, winHole.height);
    }

    private boolean canMoveBall(float x, float y) {

        float xLL = x;
        float yLL = y;

        float xLU = xLL;
        float yLU = yLL + 50;

        float xRL = xLL + 50;
        float yRL = yLL;

        float xRU = xRL;
        float yRU = yLU;

        for(Wall wall : walls) {


            if (wall.objectIsInWall(xLL, yLL) ||
                    wall.objectIsInWall(xLU, yLU) ||
                    wall.objectIsInWall(xRL, yRL) ||
                    wall.objectIsInWall(xRU, yRU) ||
                    wall.wallIsInObject(xLL, xRL, yLL, yLU)) {
                return false;
            }
        }

        //Durch jedes Loch durchgehen und schaun ob die Kugel "über" einem Loch ist, wenn ja -> Spiel reset!
        for(Hole hole : holes) {
            if(hole.holeIsUnderObject(xLL, yLL, xLU, yLU, xRL, yRL, xRU, yRU)) {
                holeSound.play();
                resetGame();
                break;
            }
        }

        if(winHole.holeIsUnderObject(xLL, yLL, xLU, yLU, xRL, yRL, xRU, yRU)) {
            winSound.play();
            wonGame();
        }

        return true;
    }

    private void resetGame() {
        startTime = TimeUtils.millis();
        renderX = startX;
        renderY = startY;
    }

    private void wonGame() {
        game.level1done = true;
        game.setScreen(new WonScreen(game, TimeUtils.timeSinceMillis(startTime), "level1"));
        dispose();
    }
}
