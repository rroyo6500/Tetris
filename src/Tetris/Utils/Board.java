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

    /**
     * Añade un nuevo elemento en la posicion indicada
     * @param x Coordenada x en el tablero
     * @param y Coordenada y en el tablero
     * @param newElement Nuevo elemento
     */
    public void setPos(int x, int y, int newElement){
        try {
            BOARD.get(y).set(x, newElement);
        } catch (Exception _) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Añade una lista de cordenadas al tablero. Permite modificar las coordenadas para ubicarlas en el hueco deseado.
     *
     * @param listOfCoordinates Lista de coordenadas
     * @param id                Identificador (Numero que se añadirá al tablero)
     * @param cX                Correccion en coordenada X (Izquierda (-) | Derecha (+))
     * @param cY                Correccion en coordenada Y (Arriba (-) | Abajo (+))
     */
    public void add(List<Coordinates> listOfCoordinates, int id, int cX, int cY){
        for (Coordinates c : listOfCoordinates) {
            setPos((c.x() + cX), (c.y() + cY), id);
        }
    }

    public int getPos(int x, int y){ return BOARD.get(y).get(x); }
    public int getBASE(){ return BASE; }
    public List<List<Integer>> getBOARD(){ return BOARD; }
    public int getWIDTH(){ return WIDTH; }
    public int getHEIGHT(){ return HEIGHT; }

    /**
     * Mueve el tablero hacia abajo
     * @param y numero de veces que se movera
     */
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

    /**
     * Reinicia el tablero entero
     */
    public void reset(){
        for (int i = 0; i < BOARD.size(); i++) {
            for (int j = 0; j < BOARD.getFirst().size(); j++) {
                BOARD.get(i).set(j, BASE);
            }
        }
    }

    /**
     * Reinicia una fila del tablero
     * @param y Fila a reiniciar
     */
    public void reset(int y){
        for (int j = 0; j < BOARD.getFirst().size(); j++) {
            BOARD.get(y).set(j, BASE);
        }
    }

}
