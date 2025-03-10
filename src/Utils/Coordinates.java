package Utils;

public class Coordinates {

    private int X, Y;
    private boolean isCenter;

    public Coordinates(int x, int y){
        this.X = x;
        this.Y = y;
        this.isCenter = false;
    }

    public Coordinates(int x, int y, boolean isCenter){
        this.X = x;
        this.Y = y;
        this.isCenter = isCenter;
    }

    public int getX(){ return X; }
    public int getY(){ return Y; }

    public void setX(int x){ X = x; }
    public void setY(int y){ Y = y; }

    public boolean isCenter(){ return isCenter; }
    public void setCenter(boolean center){ isCenter = center; }

}
