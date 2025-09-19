package Tetris.Utils;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<List<Integer>> BOARD;
    private final int BASE;
    private final int WIDTH, HEIGHT;

    public Board(int Width, int Heigth, int Base){
        this.BASE = Base;
        this.WIDTH = Width;
        this.HEIGHT = Heigth;
        this.BOARD = new ArrayList<>(){{
            for (int i = 0; i < Heigth; i++) {
                add(new ArrayList<>(){{
                    for (int j = 0; j < Width; j++) add(Base);
                }});
            }
        }};
    }

    public void setPos(int X, int Y, int newElement){
        try {
            BOARD.get(Y).set(X, newElement);
        } catch (Exception _) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @param ListOfCoordinates Lista de coordenadas
     * @param ID Identificador (Numero que se añadirá al tablero
     * @param cX Correccion en coordenada X (Izquierda (-) | Derecha (+))
     * @param cY Correccion en coordenada Y (Arriba (-) | Abajo (+))
     */
    public void add(List<Coordinates> ListOfCoordinates, int ID, int cX, int cY){
        for (Coordinates c : ListOfCoordinates) {
            setPos((c.x() + cX), (c.y() + cY), ID);
        }
    }

    public int getPos(int X, int Y){ return BOARD.get(Y).get(X); }
    public int getBASE(){ return BASE; }
    public List<List<Integer>> getBOARD(){ return BOARD; }
    public int getWIDTH(){ return WIDTH; }
    public int getHEIGHT(){ return HEIGHT; }

    public void moveDown(int y){
        for (int i = y; i >= 0; i--) {
            for (int j = 0; j < WIDTH; j++) {
                try {
                    BOARD.get(i).set(j, BOARD.get(i - 1).get(j));
                } catch (Exception _){
                    BOARD.get(i).set(j, BASE);
                }
            }
        }
    }

    public void reset(){
        for (int i = 0; i < BOARD.size(); i++) {
            for (int j = 0; j < BOARD.getFirst().size(); j++) {
                BOARD.get(i).set(j, BASE);
            }
        }
    }
    public void reset(int y){
        for (int j = 0; j < BOARD.getFirst().size(); j++) {
            BOARD.get(y).set(j, BASE);
        }
    }

}
