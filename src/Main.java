import Piezas.Part;
import Utils.Board;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        new Tetris_IG();
    }
}

class Tetris_IG extends JFrame {

    Board<Integer> board = new Board<>(15, 30, 0);
    Part part = new Part(board);

    JPanel Tablero;
    Timer gameTimer;

    public Tetris_IG() {

        Tablero = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int x = 0, y = 0;

                for(List<Integer> R : board.getBoard()){
                    for(Integer C : R){
                        if (C == 1 || C == 2) {
                            g.setColor(Color.BLUE);
                            g.fillRect(x, y, 30, 30);
                        } else if (C == 3 || C == 4) {
                            g.setColor(Color.RED);
                            g.fillRect(x, y, 30, 30);
                        } else if (C == 5 || C == 6) {
                            g.setColor(Color.GREEN);
                            g.fillRect(x, y, 30, 30);
                        } else if (C == 7 || C == 8) {
                            g.setColor(Color.MAGENTA);
                            g.fillRect(x, y, 30, 30);
                        } else if (C == 9 || C == 10) {
                            g.setColor(Color.CYAN);
                            g.fillRect(x, y, 30, 30);
                        } else if (C == 11 || C == 12) {
                            g.setColor(Color.YELLOW);
                            g.fillRect(x, y, 30, 30);
                        } else if (C == 13 || C == 14) {
                            g.setColor(Color.ORANGE);
                            g.fillRect(x, y, 30, 30);
                        }
                        x += 30;
                    }
                    x = 0;
                    y += 30;
                }
            }
        };
        Tablero.setBackground(Color.BLACK);
        Tablero.setBounds(10, 25, 450, 900);
        add(Tablero);
        JPanel Border = new JPanel();
        Border.setBackground(Color.GRAY);
        Border.setBounds(6, 21, 458, 908);
        add(Border);

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

        start();

        setLayout(null);
        setTitle("Tetris");
        setBounds(0, 0, 800, 1000);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(){
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                part.down();
                Tablero.repaint();
            }
        }, 0, 50);
    }

}