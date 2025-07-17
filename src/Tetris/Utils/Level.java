package Tetris.Utils;

import Tetris.Var;

public class Level implements Var {

    private final int maxLevel;
    public Level(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    private int xP = 0;
    private int actualLevel = 1;
    private int velocity = 1000;
    private int completeLines = 0;

    /**
     * Aumenta los puntos conseguidos basándose en las líneas completadas (seguidas)
     * @param completeLines Cantidad de líneas completadas de una sola vez
     */
    public void addXP(int completeLines) {
        int xp = 0;
        if (completeLines >= 4) {
            xp += (150 * 8);
        } else {
            xp += (150 * completeLines);
        }
        xP += xp;
        this.completeLines += completeLines;

        levelUp(xP);
    }

    /**
     * Aumenta el nivel
     * @param xP xP conseguida
     */
    private void levelUp(int xP) {
        while (xP >= (1800 * actualLevel) && actualLevel != maxLevel) {
            velocity -= 20;
            actualLevel++;

            _levelLabel.setText("LvL " + getActualLevel());
            _gameTimer.restartTimers();
        }
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public int getVelocity() {
        return velocity + 1;
    }

    public int getXP() {
        return xP;
    }

    public int getCompleteLines() {
        return completeLines;
    }

    /**
     * Reinicia los valores
     */
    public void restart() {
        velocity = 1000;
        actualLevel = 1;
        xP = 0;
        completeLines = 0;
        _xpLabel.setText("XP: 0");
        _levelLabel.setText("LvL 1");
        _linesLabel.setText("0 Lines");
    }

}
