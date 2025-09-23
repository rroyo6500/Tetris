package Tetris.Utils;

public class Coordinates {

    private int x, y;
    private final boolean IS_CENTER;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
        this.IS_CENTER = false;
    }

    public Coordinates(int x, int y, boolean isCenter){
        this.x = x;
        this.y = y;
        this.IS_CENTER = isCenter;
    }

    public int x(){ return x; }
    public int y(){ return y; }

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isCenter(){ return IS_CENTER; }
}
