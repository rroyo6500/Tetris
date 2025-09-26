package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tetris extends JFrame implements Var {

    public Tetris() {

        // Game Over

        GAMEOVER_PANEL.setBackground(Color.BLACK);
        GAMEOVER_PANEL.setLayout(null);
        GAMEOVER_PANEL.setBounds(5, 200, 575, 300);
        GAMEOVER_PANEL.setVisible(false);
        add(GAMEOVER_PANEL);

        JButton gameOverAcceptButton = new JButton("Accept");
        gameOverAcceptButton.setBounds(238, 245, 100, 50);
        gameOverAcceptButton.setBackground(Color.GREEN);
        gameOverAcceptButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gameOverAcceptButton.addActionListener(_ -> {

            GameTimers.stopTimers();

            START_BUTTON.setVisible(true);
            STOP_BUTTON.setVisible(true);
            PAUSE_BUTTON.setVisible(true);
            GAMEOVER_PANEL.setVisible(false);

        });
        GAMEOVER_PANEL.add(gameOverAcceptButton);

        // Game

        JPanel panel = getJPanel();
        add(panel);

        GAME_PANEL.setBounds(10, 25, 300, 600);
        GAME_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GAME_PANEL.setFocusable(true);
        GAME_PANEL.requestFocusInWindow();
        panel.add(GAME_PANEL);

        NEXT_PART_PANEL.setBounds(320, 25, 250, 200);
        NEXT_PART_PANEL.setBackground(Color.BLACK);
        panel.add(NEXT_PART_PANEL);

        JPanel xpBackground = new JPanel();
        xpBackground.setBackground(Color.BLACK);
        xpBackground.setLayout(null);
        xpBackground.setBounds(320, 606, 248, 19);
        panel.add(xpBackground);

        XP.setBounds(10, 0, 248, 19);
        XP.setForeground(Color.WHITE);
        xpBackground.add(XP);

        JPanel lvlBackground = new JPanel();
        lvlBackground.setBackground(Color.BLACK);
        lvlBackground.setLayout(null);
        lvlBackground.setBounds(320, 577, 44, 19);
        panel.add(lvlBackground);

        LEVEL_LABEL.setBounds(3, 0, 44, 19);
        LEVEL_LABEL.setForeground(Color.WHITE);
        lvlBackground.add(LEVEL_LABEL);

        JPanel linesBackground = new JPanel();
        linesBackground.setBackground(Color.BLACK);
        linesBackground.setLayout(null);
        linesBackground.setBounds(425, 577, 100, 19);
        panel.add(linesBackground);

        COMPLETED_LINES.setBounds(10, 0, 100, 19);
        COMPLETED_LINES.setForeground(Color.WHITE);
        linesBackground.add(COMPLETED_LINES);

        // Botones
        // Iniciar Juego / Detener Juego / Pausar juego

        START_BUTTON.setBounds(320, 250, 250, 50);
        START_BUTTON.setBackground(Color.GREEN);
        START_BUTTON.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        START_BUTTON.addActionListener(_ -> {
            if (!GameTimers.isStarted){
                GAME_PANEL.requestFocusInWindow();
                GameTimers.startGame();
            }
        });
        panel.add(START_BUTTON);

        STOP_BUTTON.setBounds(320, 325, 250, 50);
        STOP_BUTTON.setBackground(Color.RED);
        STOP_BUTTON.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        STOP_BUTTON.addActionListener(_ -> {
            if (GameTimers.isStarted){
                GameTimers.stopTimers();
                LEVEL.restart();
                PART.newPart(true);
                GAME_PANEL.repaint();
            }
        });
        panel.add(STOP_BUTTON);

        PAUSE_BUTTON.setBounds(320, 400, 250, 50);
        PAUSE_BUTTON.setBackground(Color.ORANGE);
        PAUSE_BUTTON.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        PAUSE_BUTTON.addActionListener(_ -> {
            if (GameTimers.isStarted){
                GameTimers.stopTimers();
            } else {
                GAME_PANEL.requestFocusInWindow();
                GameTimers.startGame();
            }
        });
        panel.add(PAUSE_BUTTON);

        // Movimiento Pieza

        GAME_PANEL.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (GameTimers.isStarted){
                    if (e.getKeyCode() == 40) {
                        PART.down();
                    } else if (e.getKeyCode() == 39) {
                        PART.right();
                    } else if (e.getKeyCode() == 37) {
                        PART.left();
                    } else if (e.getKeyCode() == 38) {
                        PART.rotate();
                    }
                }

            }
        });

        // --------
        setLayout(null);
        setTitle("Tetris Game");
        setBounds(0, 0, 600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static JPanel getJPanel() {
        JPanel panel = new JPanel(){
            public void paint(Graphics g) {
                super.paint(g);

                g.setColor(Color.DARK_GRAY);
                g.fillRect(8, 23, 304, 2);
                g.fillRect(8, 625, 304, 2);
                g.fillRect(8, 25, 2, 600);
                g.fillRect(310, 25, 2, 600);
                g.fillRect(318, 23, 252, 2);
                g.fillRect(318, 224, 252, 2);
                g.fillRect(318, 25, 2, 200);
                g.fillRect(568, 25, 2, 200);
                g.fillRect(318, 248, 254, 2);
                g.fillRect(318, 300, 254, 2);
                g.fillRect(318, 250, 2, 50);
                g.fillRect(570, 250, 2, 50);
                g.fillRect(318, 323, 254, 2);
                g.fillRect(318, 375, 254, 2);
                g.fillRect(318, 325, 2, 50);
                g.fillRect(570, 325, 2, 50);
                g.fillRect(318, 604, 252, 2);
                g.fillRect(318, 625, 252, 2);
                g.fillRect(318, 604, 2, 21);
                g.fillRect(568, 604, 2, 21);
                g.fillRect(318, 575, 48, 2);
                g.fillRect(318, 596, 48, 2);
                g.fillRect(318, 575, 2, 21);
                g.fillRect(364, 575, 2, 21);
                g.fillRect(423, 575, 104, 2);
                g.fillRect(423, 596, 104, 2);
                g.fillRect(423, 577, 2, 21);
                g.fillRect(525, 577, 2, 21);
                g.fillRect(318, 398, 254, 2);
                g.fillRect(318, 450, 254, 2);
                g.fillRect(318, 400, 2, 50);
                g.fillRect(570, 400, 2, 50);

                g.setColor(Color.GRAY);
                g.fillRect(6, 21, 308, 2);
                g.fillRect(6, 627, 308, 2);
                g.fillRect(6, 23, 2, 604);
                g.fillRect(312, 23, 2, 604);
                g.fillRect(316, 21, 256, 2);
                g.fillRect(316, 226, 256, 2);
                g.fillRect(316, 23, 2, 204);
                g.fillRect(570, 23, 2, 204);
                g.fillRect(316, 246, 258, 2);
                g.fillRect(316, 302, 258, 2);
                g.fillRect(316, 248, 2, 54);
                g.fillRect(572, 248, 2, 54);
                g.fillRect(316, 321, 258, 2);
                g.fillRect(316, 377, 258, 2);
                g.fillRect(316, 323, 2, 54);
                g.fillRect(572, 323, 2, 54);
                g.fillRect(316, 602, 256, 2);
                g.fillRect(316, 627, 256, 2);
                g.fillRect(316, 604, 2, 23);
                g.fillRect(570, 604, 2, 23);
                g.fillRect(316, 573, 52, 2);
                g.fillRect(316, 598, 52, 2);
                g.fillRect(316, 575, 2, 23);
                g.fillRect(366, 575, 2, 23);
                g.fillRect(421, 573, 108, 2);
                g.fillRect(421, 598, 108, 2);
                g.fillRect(421, 575, 2, 23);
                g.fillRect(527, 575, 2, 23);
                g.fillRect(316, 396, 258, 2);
                g.fillRect(316, 452, 258, 2);
                g.fillRect(316, 398, 2, 54);
                g.fillRect(572, 398, 2, 54);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(4, 19, 570, 2);
                g.fillRect(314, 21, 2, 608);
                g.fillRect(4, 629, 570, 2);
                g.fillRect(4, 21, 2, 608);
                g.fillRect(572, 21, 2, 207);
                g.fillRect(316, 228, 258, 2);
                g.fillRect(316, 244, 260, 2);
                g.fillRect(316, 304, 260, 2);
                g.fillRect(574, 246, 2, 58);
                g.fillRect(316, 319, 260, 2);
                g.fillRect(316, 379, 260, 2);
                g.fillRect(574, 321, 2, 58);
                g.fillRect(316, 230, 2, 2);
                g.fillRect(318, 230, 2, 1);
                g.fillRect(316, 232, 1, 2);
                g.fillRect(316, 242, 2, 2);
                g.fillRect(318, 243, 2, 1);
                g.fillRect(316, 240, 1, 2);
                g.fillRect(316, 306, 2, 2);
                g.fillRect(318, 306, 2, 1);
                g.fillRect(316, 308, 1, 2);
                g.fillRect(316, 317, 2, 2);
                g.fillRect(318, 318, 2, 1);
                g.fillRect(316, 315, 1, 2);
                g.fillRect(316, 381, 2, 2);
                g.fillRect(318, 381, 2, 1);
                g.fillRect(316, 383, 1, 2);
                g.fillRect(316, 600, 258, 2);
                g.fillRect(572, 602, 2, 27);
                g.fillRect(316, 571, 54, 2);
                g.fillRect(368, 573, 2, 27);
                g.fillRect(316, 569, 2, 2);
                g.fillRect(318, 570, 2, 1);
                g.fillRect(316, 567, 1, 2);
                g.fillRect(370, 598, 2, 2);
                g.fillRect(372, 599, 2, 1);
                g.fillRect(370, 596, 1, 2);
                g.fillRect(419, 571, 112, 2);
                g.fillRect(419, 573, 2, 27);
                g.fillRect(529, 573, 2, 27);
                g.fillRect(417, 598, 2, 2);
                g.fillRect(415, 599, 2, 1);
                g.fillRect(418, 596, 1, 2);
                g.fillRect(531, 598, 2, 2);
                g.fillRect(533, 599, 2, 1);
                g.fillRect(531, 596, 1, 2);
                g.fillRect(316, 394, 260, 2);
                g.fillRect(316, 454, 260, 2);
                g.fillRect(574, 396, 2, 58);
                g.fillRect(316, 392, 2, 2);
                g.fillRect(316, 390, 1, 2);
                g.fillRect(318, 393, 2, 1);
                g.fillRect(316, 456, 2, 2);
                g.fillRect(318, 456, 2, 1);
                g.fillRect(316, 458, 1, 2);

            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 700);
        return panel;
    }

}