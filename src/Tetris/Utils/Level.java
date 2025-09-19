package Tetris.Utils;

import Tetris.Var;

public class Level implements Var {

    private final int MAX_LEVEL;
    private int velocity;
    private int xp = 0;
    private int actualLevel = 1;
    private int completeLines = 0;

    public Level(int maxLevel, int velocity) {
        this.MAX_LEVEL = maxLevel;
        this.velocity = velocity;
    }

    /**
     * Aumenta los puntos conseguidos basándose en las líneas completadas (seguidas)
     * y aumenta el nivel y velocidad del juego
     * @param completeLines Cantidad de líneas completadas de una sola vez
     */
    public void addXP(int completeLines) {
        if (completeLines >= 4) {
            xp += (150 * 8);
        } else {
            xp += (150 * completeLines);
        }
        this.completeLines += completeLines;

        while (xp >= (1800 * actualLevel) && actualLevel != MAX_LEVEL) {
            velocity -= 20;
            actualLevel++;

            LEVEL_LABEL.setText("LvL " + getActualLevel());
            GAME_TIMER.restartTimers();
        }
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public int getVelocity() {
        return velocity + 1;
    }

    public int getXP() {
        return xp;
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
        xp = 0;
        completeLines = 0;
        XP.setText("XP: 0");
        LEVEL_LABEL.setText("LvL 1");
        COMPLETED_LINES.setText("0 Lines");
    }

}
