import Piezas.Part;
import Utils.Board;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new Tetris_IG();
    }
}

class Tetris_IG extends JFrame {

    Board<Integer> board = new Board<>(15, 30, 0);
    Part part = new Part(board);

    public Tetris_IG() {

        JPanel Tablero = new JPanel();
        Tablero.setBackground(Color.BLACK);
        Tablero.setBounds(10, 25, 450, 900);
        Tablero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        add(Tablero);

        JPanel Preview = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                //

            }
        };
        Preview.setBackground(Color.BLACK);
        Preview.setBounds(470, 25, 300, 200);
        Preview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        add(Preview);

        setLayout(null);
        setTitle("Tetris");
        setBounds(0, 0, 800, 1000);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}