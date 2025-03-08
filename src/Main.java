import Piezas.Part;
import Utils.Board;

public class Main {
    public static void main(String[] args) {

        Board<Integer> board = new Board<>(15, 30, 0);
        Part part = new Part(board);

        part.down();
        part.Left();
        part.Right();

    }
}