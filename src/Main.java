import Piezas.Part;
import Utils.Board;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new Tetris_IG();
    }
}

class Tetris_IG extends JFrame {

    Board<Integer> board = new Board<>(15, 30, 0);
    Part part = new Part(board);

    public Tetris_IG() {

        //

        setLayout(null);
        setTitle("Tetris");
        setBounds(0, 0, 450, 900);
        setVisible(true);
        setLocationRelativeTo(null);
    }

}