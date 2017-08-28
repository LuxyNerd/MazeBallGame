package org.emine.turcan.objects;

/**
 * Created by emine on 04.02.17.
 */

public class Hole {
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

    public Hole(int x, int y, int width, int height) {
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


    public boolean holeIsUnderObject(float xLL, float yLL, float xLU, float yLU, float xRL, float yRL, float xRU, float yRU) {


        if (    (xLL >= this.xLL && xLL <= this.xLL + width/2 && yLL >= this.yLL && yLL <= this.yLL + height / 2)   ||      //LL ist im unteren, linken Quadranten
                (xLU >= this.xLL && xLU <= this.xLL + width/2 && yLU <= this.yLU && yLU >= this.yLU - height / 2)   ||      //LU ist im oberen, linken Quadranten
                (xRL >= this.xRL - width/2 && xRL <= this.xRL && yRL >= this.yRL && yRL <= this.yRL + height / 2)   ||      //RL ist im unteren, rechten Quadranten
                (xRU >= this.xRU - width/2 && xRU <= this.xRU && yRU >= this.yRU - height/2 && yRU <= this.yRU)) {          //RU ist im oberen, rechten Quadranten
            return true;
        } else {
            return false;
        }


    }
}
