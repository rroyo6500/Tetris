package Utils;

import java.util.ArrayList;
import java.util.List;

public class Board<T> {

    private final List<List<T>> board;
    private final T base;
    private final int With, Height;

    public Board(int With, int Heigth, T Base){
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

    public void setPos(int X, int Y, T newElement){ board.get(Y).set(X, newElement); }

    public T getPos(int X, int Y){ return board.get(Y).get(X); }
    public T getBase(){ return base; }
    public List<List<T>> getBoard(){ return board; }
    public int getWith(){ return With; }
    public int getHeight(){ return Height; }

    public void moveUp(){
        for (int i = 0; i < Height; i++) {
            for (int j = 0; j < With; j++) {
                try {
                    board.get(i).set(j, board.get(i + 1).get(j));
                } catch (Exception _){
                    board.get(i).set(j, base);
                }
            }
        }
    }
    public void moveDown(){
        for (int i = (Height - 1); i >= 0; i--) {
            for (int j = 0; j < With; j++) {
                try {
                    board.get(i).set(j, board.get(i - 1).get(j));
                } catch (Exception _){
                    board.get(i).set(j, base);
                }
            }
        }
    }
    public void moveLeft(){
        for (int i = 0; i < Height; i++) {
            for (int j = 0; j < With; j++) {
                try {
                    board.get(i).set(j, board.get(i).get(j + 1));
                } catch (Exception _){
                    board.get(i).set(j, base);
                }
            }
        }
    }
    public void moveRight(){
        for (int i = 0; i < Height; i++) {
            for (int j = (With - 1); j >= 0; j--) {
                try {
                    board.get(i).set(j, board.get(i).get(j - 1));
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
        for (List<T> f : board){
            System.out.println(f);
        }
    }

}
