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

public class GameLevel3 implements Screen {

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

    private int startX = Gdx.graphics.getWidth()- 100;
    private int startY = Gdx.graphics.getHeight()-100;

    private int holeSize = 60;
    private int holeSizeBig = 80;

    //Walls
    Wall[] walls = new Wall[10];

    //Holes
    Hole[] holes = new Hole[25];

    //WinHole
    Hole winHole;


    public GameLevel3(final MazeBall game) {
        this.game = game;
        startTime = TimeUtils.millis();
        FileHandle fontFile = Gdx.files.internal("font/Calibri.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        font = generator.generateFont(parameter);

        // Bilder laden
        ballImage = new Texture(Gdx.files.internal("ball.png"));
        wallImage = new Texture(Gdx.files.internal("wallGrass.png"));
        holeImage = new Texture(Gdx.files.internal("hole.png"));
        winHoleImage = new Texture(Gdx.files.internal("portal.png"));
        holeSound = Gdx.audio.newSound(Gdx.files.internal("hole.wav"));
        winSound = Gdx.audio.newSound(Gdx.files.internal("win.wav"));
        renderX = startX;
        renderY = startY;
        int wallStrength = 30;
        int blockLength = Gdx.graphics.getWidth() / 7;

        //Create Wall
        //Außenwände
        walls[0] = new Wall(0, 0, wallStrength, Gdx.graphics.getHeight());
        walls[1] = new Wall(0, 0, Gdx.graphics.getWidth(), wallStrength);
        walls[2] = new Wall(wallStrength, Gdx.graphics.getHeight()-wallStrength, Gdx.graphics.getWidth(), wallStrength); //oben
        walls[3] = new Wall(Gdx.graphics.getWidth()-wallStrength, wallStrength, wallStrength, Gdx.graphics.getWidth()); // rechts
        //1. V links nach rechts
        walls[4] = new Wall(blockLength, 0, wallStrength, 4*blockLength-30);
        walls[5] = new Wall(2*blockLength, blockLength, wallStrength, 4*blockLength);
        walls[6] = new Wall(3*blockLength, 0, wallStrength, 4*blockLength-30);
        walls[7] = new Wall(4*blockLength, blockLength, wallStrength, 4*blockLength);
        walls[8] = new Wall(5*blockLength, 0, wallStrength, 4*blockLength-30);
        walls[9] = new Wall(6*blockLength, blockLength, wallStrength, 4*blockLength);


        holes[0] = new Hole(blockLength+20, 50,  holeSize, holeSize);
        holes[1] = new Hole(2*blockLength+20, blockLength, holeSize, holeSize);
        holes[2] = new Hole(3*blockLength+20, 50, holeSize, holeSize);
        holes[3] = new Hole(4*blockLength+20, 2*blockLength, holeSize, holeSize);
        holes[4] = new Hole(5*blockLength+20, 50, holeSize, holeSize);

        holes[5] = new Hole(6*blockLength+20, blockLength, holeSize, holeSize);
        holes[6] = new Hole(blockLength-60, 50,  holeSize, holeSize);
        holes[7] = new Hole(2*blockLength-60, blockLength, holeSize, holeSize);
        holes[8] = new Hole(3*blockLength-60, 4*blockLength, holeSize, holeSize);
        holes[9] = new Hole(4*blockLength-60, blockLength, holeSize, holeSize);
        holes[10] = new Hole(5*blockLength-80, 4*blockLength, holeSize, holeSize);
        holes[11] = new Hole(6*blockLength-60, blockLength, holeSize, holeSize);

        holes[12] = new Hole(blockLength+80, 4*blockLength,  holeSizeBig, holeSizeBig);
        holes[13] = new Hole(2*blockLength+80,2*blockLength, holeSizeBig, holeSizeBig);
        holes[14] = new Hole(3*blockLength+80, 4*blockLength, holeSizeBig, holeSizeBig);
        holes[15] = new Hole(4*blockLength+80, 3*blockLength, holeSizeBig, holeSizeBig);
        holes[16] = new Hole(5*blockLength+80, 4*blockLength, holeSizeBig, holeSizeBig);

        holes[17] = new Hole(7*blockLength-20, 3*blockLength, holeSize, holeSize);
        holes[18] = new Hole(blockLength-100, 3*blockLength,  holeSize, holeSize);
        holes[19] = new Hole(2*blockLength-80, 3*blockLength, holeSize, holeSize);
        holes[20] = new Hole(3*blockLength-80, 80, holeSize, holeSize);
        holes[21] = new Hole(4*blockLength-80, 3*blockLength, holeSize, holeSize);
        holes[22] = new Hole(5*blockLength-80, 80, holeSize, holeSize);
        holes[23] = new Hole(5*blockLength+20, 3*blockLength-30, holeSize, holeSize);
        holes[24] = new Hole(7*blockLength-80, 3*blockLength, holeSize, holeSize);

        winHole = new Hole(50, 50 , 60, 60);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255/255f, 246/255f, 143/255f, 1); // red, green, blue and alpha component of that color, each within the range [0, 1]
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
        game.level3done = true;
        game.setScreen(new WonScreen(game, TimeUtils.timeSinceMillis(startTime), "level3"));
        dispose();
    }
}

