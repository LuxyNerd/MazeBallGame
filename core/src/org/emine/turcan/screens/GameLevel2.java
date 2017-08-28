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

public class GameLevel2 implements Screen {

    final MazeBall game;
    public BitmapFont font;
    long startTime;

    //für den Ball
    private Texture ballImage;
    private Texture wallImage;
    private Texture holeImage;
    private Sound holeSound;
    private Sound winSound;
    private Texture winHoleImage;
    private float renderX;
    private float renderY;

    private float speed = 1.0f; // Geschwindigkeit der Kugel

    private int startX = 12;
    private int startY = 12;

    private int holeSize = 60;

    //Walls
    Wall[] walls = new Wall[43];

    //Holes
    Hole[] holes = new Hole[3];

    //WinHole
    Hole winHole;


    public GameLevel2(final MazeBall game) {
        this.game = game;
        startTime = TimeUtils.millis();
        FileHandle fontFile = Gdx.files.internal("font/Calibri.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        font = generator.generateFont(parameter);

        //Ball Bild laden
        ballImage = new Texture(Gdx.files.internal("ball.png"));
        wallImage = new Texture(Gdx.files.internal("wall.png"));
        holeImage = new Texture(Gdx.files.internal("hole.png"));
        winHoleImage = new Texture(Gdx.files.internal("portal.png"));
        holeSound = Gdx.audio.newSound(Gdx.files.internal("hole.wav"));
        winSound = Gdx.audio.newSound(Gdx.files.internal("win.wav"));
        renderX = startX;
        renderY = startY;
        int wallStrength = 10;
        int blockLength = Gdx.graphics.getWidth() / 11;

        //Create Wall
        //Außenwände
        walls[0] = new Wall(0, 0, wallStrength, Gdx.graphics.getHeight());
        walls[1] = new Wall(0, 0, Gdx.graphics.getWidth(), wallStrength);
        walls[2] = new Wall(wallStrength, Gdx.graphics.getHeight()-wallStrength, Gdx.graphics.getWidth(), wallStrength); //oben
        walls[3] = new Wall(Gdx.graphics.getWidth()-wallStrength, wallStrength, wallStrength, Gdx.graphics.getWidth()); // rechts
        //1. H von unten nach oben
        walls[4] = new Wall(blockLength, 2*blockLength, wallStrength, 3*blockLength);
        //2. H
        walls[5] = new Wall(2*blockLength, blockLength, wallStrength, blockLength);
        walls[6] = new Wall(2*blockLength, 5*blockLength, wallStrength, blockLength);
        //3. H
        walls[7] = new Wall(3*blockLength, blockLength, wallStrength, 3*blockLength);
        walls[8] = new Wall(3*blockLength, 4*blockLength, wallStrength, 2*blockLength);
        //4. H
        walls[9] = new Wall(4*blockLength, blockLength, wallStrength, blockLength);
        walls[10] = new Wall(4*blockLength, 6*blockLength, wallStrength, blockLength);
        //5. H
        walls[11] = new Wall(5*blockLength, 0, wallStrength, blockLength);
        walls[12] = new Wall(5*blockLength, 2*blockLength, wallStrength, 2*blockLength);
        walls[13] = new Wall(5*blockLength, 6*blockLength, wallStrength, blockLength);
        //6. H
        walls[14] = new Wall(6*blockLength, blockLength, wallStrength, blockLength);
        walls[15] = new Wall(6*blockLength, 3*blockLength, wallStrength, blockLength);
        walls[16] = new Wall(6*blockLength, 5*blockLength, wallStrength, blockLength);
        //7. H
        walls[17] = new Wall(7*blockLength, 2*blockLength, wallStrength, blockLength);
        walls[18] = new Wall(7*blockLength, 6*blockLength, wallStrength, blockLength);
        //8. H
        walls[19] = new Wall(8*blockLength, 2*blockLength, wallStrength, 2*blockLength);
        walls[20] = new Wall(8*blockLength, 3*blockLength, wallStrength, blockLength);
        walls[21] = new Wall(8*blockLength, 5*blockLength, wallStrength, 2*blockLength);
        //9. H
        walls[22] = new Wall(9*blockLength, blockLength, wallStrength, 2*blockLength);
        walls[23] = new Wall(9*blockLength, 4*blockLength, wallStrength, 2*blockLength);
        //10. H
        walls[24] = new Wall(10*blockLength, blockLength, wallStrength, blockLength);

        //V
        walls[25] = new Wall(0, blockLength, blockLength, wallStrength);
        walls[26] = new Wall(0, 6*blockLength, 2*blockLength, wallStrength);
        walls[27] = new Wall(blockLength, 2*blockLength, blockLength, wallStrength);
        walls[28] = new Wall(blockLength, 4*blockLength, 2*blockLength, wallStrength);
        walls[29] = new Wall(2*blockLength, 3*blockLength, 2*blockLength, wallStrength);
        walls[30] = new Wall(3*blockLength, 5*blockLength, 2*blockLength, wallStrength);
        walls[31] = new Wall(4*blockLength, 2*blockLength, 2*blockLength, wallStrength);
        walls[32] = new Wall(4*blockLength, 4*blockLength, blockLength, wallStrength);
        walls[33] = new Wall(5*blockLength, 6*blockLength, blockLength, wallStrength);
        walls[34] = new Wall(6*blockLength, 4*blockLength, 2*blockLength, wallStrength);
        walls[35] = new Wall(6*blockLength, 5*blockLength, blockLength, wallStrength);
        walls[36] = new Wall(6*blockLength, blockLength, 2*blockLength, wallStrength);
        walls[37] = new Wall(7*blockLength, 2*blockLength, blockLength, wallStrength);
        walls[38] = new Wall(8*blockLength, 3*blockLength, 2*blockLength, wallStrength);
        walls[39] = new Wall(9*blockLength, 5*blockLength, 2*blockLength, wallStrength);
        walls[40] = new Wall(9*blockLength, 6*blockLength, blockLength, wallStrength);
        walls[41] = new Wall(10*blockLength, 2*blockLength, blockLength, wallStrength);
        walls[42] = new Wall(10*blockLength, 4*blockLength, blockLength, wallStrength);


        holes[0] = new Hole(5*blockLength-50, 4*blockLength, holeSize, holeSize);
        holes[1] = new Hole(9*blockLength, 3*blockLength, holeSize, holeSize);
        holes[2] = new Hole(blockLength+3, 4*blockLength+3, holeSize, holeSize);


        winHole = new Hole(Gdx.graphics.getWidth()-200, Gdx.graphics.getHeight()-200, 60, 60);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(153/255f, 0, 153/255f, 1); // red, green, blue and alpha component of that color, each within the range [0, 1]
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
        game.level2done = true;
        game.setScreen(new WonScreen(game, TimeUtils.timeSinceMillis(startTime), "level2"));
        dispose();
    }
}