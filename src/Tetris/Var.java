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

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, 575, 5);
            g.fillRect(0, 295, 575, 5);
            g.fillRect(0, 5, 5, 290);
            g.fillRect(570, 5, 5, 290);

            g.setColor(Color.GRAY);
            g.fillRect(5, 5, 565, 5);
            g.fillRect(5, 290, 565, 5);
            g.fillRect(5, 10, 5, 280);
            g.fillRect(565, 10, 5, 280);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(10, 10, 555, 5);
            g.fillRect(10, 285, 555, 5);
            g.fillRect(10, 15, 5, 270);
            g.fillRect(560, 15, 5, 270);

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
