package org.emine.turcan.objects;

/**
 * Created by emine on 03.02.17.
 * Diese Klasse beinhaltet das Wallobjekt
 */

public class Wall {
    public int xLU;
    public int yLU;

    public int xRU;
    public int yRU;

    public int xLL;
    public int yLL;

    public int xRL;
    public int yRL;

    public int width;
    public int height;

    public Wall(int x, int y, int width, int height) {
        this.xLL = x;
        this.yLL = y;

        this.width = width;
        this.height = height;

        this.xLU = xLL ;
        this.yLU = yLL + height;

        this.xRL = xLL + width;
        this.yRL = yLL;

        this.xRU = xRL;
        this.yRU = yLU;

    }

    public boolean objectIsInWall(float x, float y) {
        if(x >= this.xLL && x <= this.xRL && y >= this.yLL && y <= this.yLU) {
            return true;
        } else {
            return false;
        }
    }

    public boolean wallIsInObject(float xLL, float xRL, float yLL, float yLU) {

        if((this.xLL >= xLL && this.xLL <= xRL && this.yLL >= yLL && this.yLL <= yLU) ||
                (this.xLU >= xLL && this.xLU <= xRL && this.yLU >= yLL && this.yLU <= yLU) ||
                (this.xRU >= xLL && this.xRU <= xRL && this.yRU >= yLL && this.yRU <= yLU) ||
                (this.xRL >= xLL && this.xRL <= xRL && this.yRL >= yLL && this.yRL <= yLU)) {
            return true;
        } else {
            return false;
        }

    }
}
