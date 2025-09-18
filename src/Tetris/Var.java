package Tetris;

import Tetris.Piezas.Part;
import Tetris.Utils.Board;
import Tetris.Utils.Level;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public interface Var {

    // --------
    // Interfaz Grafica

    JLabel _xpLabel = new JLabel();
    JLabel _levelLabel = new JLabel("LvL 1");
    JLabel _linesLabel = new JLabel();

    JButton _startButton = new JButton("Start");
    JButton _stopButton = new JButton("Stop");

    JPanel _gamePanel = new JPanel(){
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int x = 0, y = 0;
            for (List<Integer> f : _board.getBoard()){
                for (Integer c : f){

                    switch (c){
                        case 1, 2 -> g.setColor(Color.ORANGE);
                        case 3, 4 -> g.setColor(Color.BLUE);
                        case 5, 6 -> g.setColor(Color.MAGENTA);
                        case 7, 8 -> g.setColor(Color.GREEN);
                        case 9, 10 -> g.setColor(Color.RED);
                        case 11, 12 -> g.setColor(Color.CYAN);
                        case 13, 14 -> g.setColor(Color.YELLOW);
                        default -> g.setColor(Color.BLACK);
                    }

                    g.fillRect(x, y, 20, 20);

                    x += 20;
                }
                x = 0;
                y += 20;
            }

        }
    };

    JPanel _nextPartPanel = new JPanel(){
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Board b_Next = new Board(3, 4, 0);
            b_Next.add(_part.getNextPart().getPart(), _part.getNextPart().getID(), -6, 0);

            int x, y = switch (_part.getNextPart()){
                case L, L_Inv -> 62;
                case T, S, Z, Cube -> 75;
                case I -> 50;
            };
            for (List<Integer> f : b_Next.getBoard()){

                x = switch (_part.getNextPart()){
                    case L, Cube -> 75;
                    case L_Inv, T, S, Z, I -> 87;
                };

                for (Integer c : f){

                    switch (c){
                        case 2 -> g.setColor(Color.ORANGE);
                        case 4 -> g.setColor(Color.BLUE);
                        case 6 -> g.setColor(Color.MAGENTA);
                        case 8 -> g.setColor(Color.GREEN);
                        case 10 -> g.setColor(Color.RED);
                        case 12 -> g.setColor(Color.CYAN);
                        case 14 -> g.setColor(Color.YELLOW);
                        default -> g.setColor(Color.BLACK);
                    }

                    g.fillRect(x, y, 26, 26);

                    x += 26;
                }
                y += 26;
            }

        }
    };

    JPanel _gameOverPanel = new JPanel(){
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(10, 10, 555, 5);
            g.fillRect(10, 285, 555, 5);
            g.fillRect(10, 15, 5, 270);
            g.fillRect(560, 15, 5, 270);
            g.fillRect(223, 230, 130, 5);
            g.fillRect(223, 235, 5, 60);
            g.fillRect(348, 235, 5, 60);

            g.setColor(Color.GRAY);
            g.fillRect(5, 5, 565, 5);
            g.fillRect(5, 290, 565, 5);
            g.fillRect(5, 10, 5, 280);
            g.fillRect(565, 10, 5, 280);
            g.fillRect(228, 235, 120, 5);
            g.fillRect(228, 240, 5, 55);
            g.fillRect(343, 240, 5, 55);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, 575, 5);
            g.fillRect(0, 295, 575, 5);
            g.fillRect(0, 5, 5, 290);
            g.fillRect(570, 5, 5, 290);
            g.fillRect(233, 240, 110, 5);
            g.fillRect(233, 245, 5, 50);
            g.fillRect(338, 245, 5, 50);

            // GAME OVER

            g.setColor(Color.RED);
            g.fillRect(34, 69, 18, 9);
            g.fillRect(43, 78, 18, 9);
            g.fillRect(115, 105, 9, 18);
            g.fillRect(124, 96, 9, 18);
            g.fillRect(187, 69, 18, 9);
            g.fillRect(196, 78, 18, 9);
            g.fillRect(232, 87, 9, 18);
            g.fillRect(241, 78, 9, 18);
            g.fillRect(305, 114, 9, 18);
            g.fillRect(314, 105, 9, 18);
            g.fillRect(368, 96, 18, 9);
            g.fillRect(377, 105, 18, 9);
            g.fillRect(449, 87, 9, 18);
            g.fillRect(458, 78, 9, 18);
            g.fillRect(494, 78, 9, 18);
            g.fillRect(503, 69, 9, 18);

            g.setColor(Color.CYAN);
            g.fillRect(34, 78, 9, 36);
            g.fillRect(133, 96, 9, 36);
            g.fillRect(160, 87, 9, 36);
            g.fillRect(232, 132, 36, 9);
            g.fillRect(350, 87, 9, 36);
            g.fillRect(377, 114, 36, 9);
            g.fillRect(449, 132, 36, 9);
            g.fillRect(494, 105, 9, 36);

            g.setColor(Color.BLUE);
            g.fillRect(79, 114, 9, 27);
            g.fillRect(70, 132, 9, 9);
            g.fillRect(106, 114, 9, 27);
            g.fillRect(97, 132, 9, 9);
            g.fillRect(178, 78, 9, 27);
            g.fillRect(187, 78, 9, 9);
            g.fillRect(241, 69, 27, 9);
            g.fillRect(259, 78, 9, 9);
            g.fillRect(332, 78, 27, 9);
            g.fillRect(332, 69, 9, 9);
            g.fillRect(368, 69, 9, 27);
            g.fillRect(377, 69, 9, 9);
            g.fillRect(458, 69, 27, 9);
            g.fillRect(476, 78, 9, 9);
            g.fillRect(512, 69, 27, 9);
            g.fillRect(530, 78, 9, 9);

            g.setColor(Color.YELLOW);
            g.fillRect(34, 114, 18, 18);
            g.fillRect(124, 78, 18, 18);
            g.fillRect(151, 123, 18, 18);
            g.fillRect(223, 69, 18, 18);
            g.fillRect(305, 87, 18, 18);
            g.fillRect(404, 96, 18, 18);
            g.fillRect(440, 69, 18, 18);
            g.fillRect(521, 87, 18, 18);

            g.setColor(Color.MAGENTA);
            g.fillRect(52, 69, 27, 9);
            g.fillRect(61, 78, 9, 9);
            g.fillRect(106, 87, 9, 27);
            g.fillRect(115, 96, 9, 9);
            g.fillRect(196, 114, 9, 27);
            g.fillRect(205, 123, 9, 9);
            g.fillRect(223, 114, 9, 27);
            g.fillRect(232, 123, 9, 9);
            g.fillRect(314, 123, 27, 9);
            g.fillRect(323, 132, 9, 9);
            g.fillRect(386, 123, 27, 9);
            g.fillRect(395, 132, 9, 9);
            g.fillRect(440, 114, 9, 27);
            g.fillRect(449, 123, 9, 9);
            g.fillRect(503, 87, 9, 27);
            g.fillRect(494, 96, 9, 9);

            g.setColor(Color.GREEN);
            g.fillRect(61, 105, 9, 18);
            g.fillRect(70, 114, 9, 18);
            g.fillRect(106, 78, 18, 9);
            g.fillRect(115, 69, 18, 9);
            g.fillRect(160, 69, 9, 18);
            g.fillRect(169, 78, 9, 18);
            g.fillRect(232, 105, 9, 18);
            g.fillRect(241, 114, 9, 18);
            g.fillRect(332, 132, 18, 9);
            g.fillRect(341, 123, 18, 9);
            g.fillRect(377, 78, 9, 18);
            g.fillRect(386, 87, 9, 18);
            g.fillRect(449, 105, 9, 18);
            g.fillRect(458, 114, 9, 18);
            g.fillRect(521, 114, 9, 18);
            g.fillRect(530, 123, 9, 18);

            g.setColor(Color.ORANGE);
            g.fillRect(43, 132, 27, 9);
            g.fillRect(61, 123, 9, 9);
            g.fillRect(124, 114, 9, 27);
            g.fillRect(133, 132, 9, 9);
            g.fillRect(187, 87, 9, 9);
            g.fillRect(196, 87, 9, 27);
            g.fillRect(241, 105, 9, 9);
            g.fillRect(241, 96, 27, 9);
            g.fillRect(305, 78, 27, 9);
            g.fillRect(323, 69, 9, 9);
            g.fillRect(413, 69, 9, 27);
            g.fillRect(422, 87, 9, 9);
            g.fillRect(458, 96, 27, 9);
            g.fillRect(458, 105, 9, 9);
            g.fillRect(512, 105, 27, 9);
            g.fillRect(512, 114, 9, 9);

        }
    };

    // Variables Principales

    Board _board = new Board(15, 30, 0);
    Level _level = new Level(50);
    Part _part = new Part();

    // Timers

    GameTimers _gameTimer = new GameTimers();

    class GameTimers {

        public GameTimers(){}

        private Timer _gameTimer = new Timer();
        private Timer _fpsTimer = new Timer();

        private boolean isStarted = false;

        public void startGame() {
            isStarted = true;

            _fpsTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    _gamePanel.repaint();
                }
            }, 0, 1000 / 60);

            _gameTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    _part.down();
                }
            }, 0 , _level.getVelocity());
        }

        public void restartTimers(){
            _fpsTimer.cancel();
            _gameTimer.cancel();

            _fpsTimer = new Timer();
            _gameTimer = new Timer();

            startGame();
        }

        public void stopTimers() {
            _gameTimer.cancel();
            _fpsTimer.cancel();

            _fpsTimer = new Timer();
            _gameTimer = new Timer();

            isStarted = false;

            _level.restart();
            _part.newPart(true);
            _gamePanel.repaint();
        }

        public boolean isStarted() {
            return isStarted;
        }

    }

}
