package Main;

import Piezas.Part;
import Utils.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris_IG extends JFrame {

    int Velocidad = 500;
    static JPanel Tablero;
    static Timer gameTimer;

    static Board<Integer> board = new Board<>(15, 30, 0);
    static Part part = new Part(board);

    static Thread rotationThread = new Thread(() -> {});
    static Thread startThread = new Thread(() -> {});

    private static void rotationT(){
        if (!startThread.isAlive()){
            rotationThread = new Thread(() -> part.rotate());
            rotationThread.start();
        }
    }

    public Tetris_IG() {

        Tablero = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int x = 0, y = 0;

                for(java.util.List<Integer> R : board.getBoard()){
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

        JButton startGame = new JButton("Start");
        startGame.setBounds(470, 225, 300, 50);
        startGame.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        startGame.setBackground(Color.GREEN);
        startGame.addActionListener(_ -> start());
        startGame.setFocusable(true);
        startGame.addKeyListener(keyAdapter());
        add(startGame);

        JButton stopGame = new JButton("Stop");
        stopGame.setBounds(470, 275, 300, 50);
        stopGame.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        stopGame.setBackground(Color.RED);
        stopGame.addActionListener(_ -> stop());
        add(stopGame);

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
                if (!rotationThread.isAlive()){
                    startThread = new Thread(() -> part.down());
                    startThread.start();
                }
            }
        }, 0, Velocidad + 1);
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Tablero.repaint();
            }
        }, 0, 1000 / 60);
    }

    public static void stop(){
        board.reset();
        part.newPart();
        gameTimer.cancel();
        SwingUtilities.invokeLater(Tablero::repaint);
    }

    public static KeyAdapter keyAdapter(){
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == 37) part.Left();
                else if (e.getKeyCode() == 40) part.down();
                else if (e.getKeyCode() == 39) part.Right();
                else if (e.getKeyCode() == 38) rotationT();

            }
        };
    }

}