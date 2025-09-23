package Tetris.Utils;

import Tetris.Var;

public class Level implements Var {

    private final int MAX_LEVEL;
    private final int DEFAULT_VELOCITY;
    private int velocity;
    private int xp = 0;
    private int actualLevel = 1;
    private int completedLines = 0;

    public Level(int maxLevel, int velocity) {
        this.MAX_LEVEL = maxLevel;
        this.DEFAULT_VELOCITY = this.velocity = velocity;
    }

    /**
     * Aumenta los puntos conseguidos basándose en las líneas completadas (seguidas)
     * y aumenta el nivel y velocidad del juego
     * @param completedLines Cantidad de líneas completadas de una sola vez
     */
    public void addXP(int completedLines) {
        if (completedLines == 4) {
            xp += (150 * 8);
        } else {
            xp += (150 * completedLines);
        }
        this.completedLines += completedLines;

        while (xp >= (1800 * actualLevel) && actualLevel != MAX_LEVEL) {
            velocity -= 20;
            actualLevel++;

            LEVEL_LABEL.setText("LvL " + getActualLevel());
            GameTimers.restartTimers();
        }
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getXP() {
        return xp;
    }

    public int getCompletedLines() {
        return completedLines;
    }

    /**
     * Reinicia los valores
     */
    public void restart() {
        velocity = DEFAULT_VELOCITY;
        actualLevel = 1;
        xp = 0;
        completedLines = 0;
        XP.setText("XP: 0");
        LEVEL_LABEL.setText("LvL 1");
        COMPLETED_LINES.setText("0 Lines");
    }

}
