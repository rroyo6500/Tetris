package Tetris.Utils;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<List<Integer>> board;
    private final int base;
    private final int With, Height;

    public Board(int With, int Heigth, int Base){
        this.base = Base;
        this.With = With;
        this.Height = Heigth;
        this.board = new ArrayList<>(){{
            for (int i = 0; i < Heigth; i++) {
                add(new ArrayList<>(){{
                    for (int j = 0; j < With; j++) add(Base);
                }});
            }
        }};
    }

    public void setPos(int X, int Y, int newElement){
        try {
            board.get(Y).set(X, newElement);
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

    public int getPos(int X, int Y){ return board.get(Y).get(X); }
    public int getBase(){ return base; }
    public List<List<Integer>> getBoard(){ return board; }
    public int getWith(){ return With; }
    public int getHeight(){ return Height; }

    public void moveDown(int y){
        for (int i = y; i >= 0; i--) {
            for (int j = 0; j < With; j++) {
                try {
                    board.get(i).set(j, board.get(i - 1).get(j));
                } catch (Exception _){
                    board.get(i).set(j, base);
                }
            }
        }
    }

    public void reset(){
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                board.get(i).set(j, base);
            }
        }
    }
    public void reset(int y){
        for (int j = 0; j < board.getFirst().size(); j++) {
            board.get(y).set(j, base);
        }
    }

    public void forEach(){
        for (List<Integer> f : board){
            System.out.println(f);
        }
    }

}
