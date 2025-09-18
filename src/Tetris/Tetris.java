package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tetris extends JFrame implements Var {

    public Tetris() {

        // Game Over

        _gameOverPanel.setBackground(Color.BLACK);
        _gameOverPanel.setLayout(null);
        _gameOverPanel.setBounds(5, 200, 575, 300);
        _gameOverPanel.setVisible(false);
        add(_gameOverPanel);

        JButton goAcceptB = new JButton("Accept");
        goAcceptB.setBounds(238, 245, 100, 50);
        goAcceptB.setBackground(Color.GREEN);
        goAcceptB.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        goAcceptB.addActionListener(_ -> {

            _gameTimer.stopTimers();

            _startButton.setVisible(true);
            _stopButton.setVisible(true);
            _gameOverPanel.setVisible(false);

        });
        _gameOverPanel.add(goAcceptB);

        // Game

        JPanel panel = getJPanel();
        add(panel);

        _gamePanel.setBounds(10, 25, 300, 600);
        _gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _gamePanel.setFocusable(true);
        _gamePanel.requestFocusInWindow();
        panel.add(_gamePanel);

        _nextPartPanel.setBounds(320, 25, 250, 200);
        _nextPartPanel.setBackground(Color.BLACK);
        panel.add(_nextPartPanel);

        JPanel xpBackground = new JPanel();
        xpBackground.setBackground(Color.BLACK);
        xpBackground.setLayout(null);
        xpBackground.setBounds(320, 606, 248, 19);
        panel.add(xpBackground);

        _xpLabel.setBounds(10, 0, 248, 19);
        _xpLabel.setForeground(Color.WHITE);
        xpBackground.add(_xpLabel);

        JPanel LvLBackground = new JPanel();
        LvLBackground.setBackground(Color.BLACK);
        LvLBackground.setLayout(null);
        LvLBackground.setBounds(320, 577, 44, 19);
        panel.add(LvLBackground);

        _levelLabel.setBounds(3, 0, 44, 19);
        _levelLabel.setForeground(Color.WHITE);
        LvLBackground.add(_levelLabel);

        JPanel linesBackground = new JPanel();
        linesBackground.setBackground(Color.BLACK);
        linesBackground.setLayout(null);
        linesBackground.setBounds(425, 577, 100, 19);
        panel.add(linesBackground);

        _linesLabel.setBounds(10, 0, 100, 19);
        _linesLabel.setForeground(Color.WHITE);
        linesBackground.add(_linesLabel);

        // Botones
        // Iniciar Juego / Detener Juego

        _startButton.setBounds(320, 250, 250, 50);
        _startButton.setBackground(Color.GREEN);
        _startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _startButton.addActionListener(_ -> {
            if (!_gameTimer.isStarted()){
                _gamePanel.requestFocusInWindow();
                _gameTimer.startGame();
            }
        });
        panel.add(_startButton);

        _stopButton.setBounds(320, 325, 250, 50);
        _stopButton.setBackground(Color.RED);
        _stopButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _stopButton.addActionListener(_ -> {
            if (_gameTimer.isStarted()){
                _gameTimer.stopTimers();
            }
        });
        panel.add(_stopButton);

        // Movimiento Pieza

        _gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == 40) {
                    _part.down();
                } else if (e.getKeyCode() == 39) {
                    _part.Right();
                } else if (e.getKeyCode() == 37) {
                    _part.Left();
                } else if (e.getKeyCode() == 38) {
                    _part.rotate();
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

            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 700);
        return panel;
    }

}