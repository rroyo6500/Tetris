package Tetris.Piezas;

import Tetris.Utils.Coordinates;
import Tetris.Var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part implements Var, Parts {

    public Part() {
        newPart(false);
        copyPart();
    }

    private Types type;
    private Coordinates center;
    private final List<Coordinates> PART = new ArrayList<>();

    // --------

    private Types nextPart;

    /**
     * @return Lista de coordenadas de la siguiente pieza
     */
    public  Types getNextPart() {
        return nextPart;
    }

    // --------

    /**
     * Genera una nueva pieza jugable.
     */
    public void newPart(boolean copy){
        // Se consigue una nueva pieza jugable (aleatoria de entre las disponibles).
        nextPart = Types.values()[((int)(Math.random()*Types.values().length))];
        assert nextPart != null;

        if (copy) {
            BOARD.reset();
            copyPart();
        }
    }

    /**
     * Copia la pieza pregenerada de 'nextPart' y Limpiamos las líneas completas en caso de haber
     */
    public void copyPart(){
        // Limpiamos las líneas completadas en caso de haber
        int countOfLines = 0;
        for (int y = (BOARD.getHEIGHT() - 1); y >= 0; y--) {
            int count = 0;
            for (int x = 0; x < BOARD.getWIDTH(); x++) {
                if (BOARD.getPos(x, y) != 0) {
                    count++;
                }
            }
            if (count == BOARD.getWIDTH()) {
                BOARD.reset(y);
                BOARD.moveDown(y);
                countOfLines++;
                y++;
            }
        }

        LEVEL.addXP(countOfLines);
        XP.setText(LEVEL.getXP() + " EXP");
        COMPLETED_LINES.setText(LEVEL.getCompleteLines() + " Lines");

        // Copiamos el tipo de pieza de 'nextPart'
        type = nextPart;

        // Limpiamos la anterior pieza y añadimos la nueva.
        PART.clear();
        for (Coordinates c : type.getPart()){
            PART.add(new Coordinates(c.x(), c.y(), c.isCenter()));
        }

        // Añadimos el centro de la nueva pieza.
        center = type.getCenter(PART);

        // Comprueba si hay espacio dispoible para que aparezca la pieza.
        // En caso de no haber si indicara al jugador que la partida ha finalizado.
        for (Coordinates c : PART) {
            if ((BOARD.getPos(c.x(), c.y()) % 2) != 0) {

                GAME_TIMER.stopTimers();

                START_BUTTON.setVisible(false);
                STOP_BUTTON.setVisible(false);
                GAMEOVER_PANEL.setVisible(true);
                GAMEOVER_PANEL.repaint();

                break;
            }
        }

        // Generamos una nueva pieza en 'nextPart'
        newPart(false);

        // Pintamos la nueva pieza en el tabblero
        printPart();

        NEXT_PART_PANEL.repaint();
    }

    // Logica

    /**
     * Pinta la pieza en el tablero
     */
    private void printPart(){
        // Pintamos la ID de la pieza en las coordenadas de cada uno de los bloques de la pieza (dentro del tabblero).
        for (Coordinates c : PART) {
            BOARD.setPos(c.x(), c.y(), type.getID());
        }
    }

    /**
     * Elimina la pieza del tablero.
     */
    private void clearPart(){
        for (Coordinates c : PART) {
            BOARD.setPos(c.x(), c.y(), BOARD.getBASE());
        }
    }

    /**
     * Congela la pieza en su posicion actual y crea una nueva pieza.
     */
    private void freeze(){
        for (Coordinates c : PART) {
            BOARD.setPos(c.x(), c.y(), (type.getID() - 1));
        }
        copyPart();
    }

    // Movimiento

    /**
     * Mueve la pieza actual +1 en la coordenada Y (Abajo).
     * Al colisionar con una pieza anterior o el borde inferior del tablero congela la pieza.
     */
    public void down(){
        clearPart();

        for (Coordinates c : PART) {
            try {
                if ((BOARD.getPos(c.x(), (c.y() + 1)) != 0)) {
                    freeze();
                    return;
                }
            } catch (Exception _) {
                freeze();
                return;
            }
        }

        for (Coordinates c : PART) {
            c.setY(c.y() + 1);
        }

        printPart();
    }

    /**
     * Mueve la pieza actual -1 en la coordenada X (Izquierda).
     */
    public void left(){
        clearPart();

        for (Coordinates c : PART) {
            try {
                if (BOARD.getPos((c.x() - 1), c.y()) != 0) {
                    printPart();
                    return;
                }
            } catch (Exception _) {
                printPart();
                return;
            }
        }

        for (Coordinates c : PART) {
            c.setX(c.x() - 1);
        }

        printPart();
    }

    /**
     * Mueve la pieza actual +1 en la coordenada X (Derecha).
     */
    public void right(){
        clearPart();

        for (Coordinates c : PART) {
            try {
                if (BOARD.getPos((c.x() + 1), c.y()) != 0) {
                    printPart();
                    return;
                }
            } catch (Exception _) {
                printPart();
                return;
            }
        }

        for (Coordinates c : PART) {
            c.setX(c.x() + 1);
        }

        printPart();
    }

    // Rotacion

    /**
     * Rota la pieza 90º ( ↻ ).
     */
    public void rotate(){
        // Comprobamos que la pieza no es un Cubo (este no puede rotar)
        if (type == Types.Cube) return;

        // Limpiamos la pieza del tablero
        clearPart();

        // Creamos una copia de la pieza y rotamos sus coordenadas para comprobar si entra antes de rotar la pieza original.
        List<Coordinates> resCoord = new ArrayList<>();
        for (Coordinates c : PART) {
            resCoord.add(new Coordinates(c.x(), c.y(), c.isCenter()));
        }
        rotateCoords(resCoord);

        // Comprobamos que las coordenadas no esten ocupadas.
        boolean comp = false;
        for (Coordinates c : resCoord) {
            try{
                if (BOARD.getPos(c.x(), c.y()) != 0) {
                    comp = true;
                }
            } catch (Exception _) {
                comp = true;
            }
        }

        // Comprobamos que la pieza puede rotar. En caso de que no (comp == true) se vuelve a pintar la pieza sin rotar.
        if (comp) {
            printPart();
            return;
        }

        // Rotamos la pieza original.
        rotateCoords(PART);

        // Pintamos la pieza rotada
        printPart();
    }

    /**
     * Rota las coordenadas de la pieza (matrix) adjunta.
     * @param matrix Pieza a la cual se le rotaran las coordenadas (alrededor del bloque centro)
     */
    private void rotateCoords(List<Coordinates> matrix){
        for (Coordinates c : matrix) {
            if (!c.isCenter()) {
                switch (type) {
                    case S, Z: {
                        if (c.x() == (center.x()-1) && c.y() == (center.y()-1)) c.setCoords(center.x() + 1, c.y());
                        else if (c.x() == (center.x()+1) && c.y() == (center.y()-1)) c.setCoords(c.x(),center.y() + 1);
                        else if (c.x() == (center.x()+1) && c.y() == (center.y()+1)) c.setCoords(center.x() - 1, c.y());
                        else if (c.x() == (center.x()-1) && c.y() == (center.y()+1)) c.setCoords(c.x(), center.y() - 1);
                        break;
                    }
                    case I, L, L_Inv: {
                        if (c.x() == (center.x()-2) && c.y() == center.y()) c.setCoords(center.x(), c.y() - 2);
                        else if (c.x() == center.x() && c.y() == (center.y()-2)) c.setCoords(c.x() + 2, center.y());
                        else if (c.x() == (center.x()+2) && c.y() == center.y()) c.setCoords(center.x(), c.y() + 2);
                        else if (c.x() == center.x() && c.y() == (center.y()+2)) c.setCoords(c.x() - 2, center.y());
                        break;
                    }
                }
                if (c.x() == (center.x()-1) && c.y() == center.y()) c.setCoords(center.x(), c.y() - 1);
                else if (c.x() == center.x() && c.y() == (center.y()-1)) c.setCoords(c.x() + 1, center.y());
                else if (c.x() == (center.x()+1) && c.y() == center.y()) c.setCoords(center.x(), c.y() + 1);
                else if (c.x() == center.x() && c.y() == (center.y()+1)) c.setCoords(c.x() - 1, center.y());
            }
        }
    }

}

interface Parts {

    /**
     * Lista de piezas jugables.
     * Devuelve la pieza (Lista de coordenadas iniciales)
     */
    enum Types{
        L(Parts.L, 2, 2),
        L_Inv(Parts.L_Inv, 4, 3),
        T(Parts.T, 6, 2),
        S(Parts.S, 8, 3),
        Z(Parts.Z, 10, 3),
        I(Parts.I, 12, 2),
        Cube(Parts.Cube, 14);

        private final List<Coordinates> PART;
        private final int ID;
        private int center;

        Types(List<Coordinates> part, int ID, Integer center) {
            this.PART = part;
            this.ID = ID;
            this.center = center;
        }
        Types(List<Coordinates> part, int ID) {
            this.PART = part;
            this.ID = ID;
        }

        /**
         * @return Lista de coordenadas de la pieza
         */
        public List<Coordinates> getPart() {
            return PART;
        }

        /**
         * @return ID de la pieza
         */
        public int getID() {
            return ID;
        }

        /**
         * @param part List of selected Part
         * @return Center of selected Part
         */
        public Coordinates getCenter(List<Coordinates> part) {
            return this == Cube ? null : part.get(center);
        }

    }

    // Piezas jugables:

    /*
    []
    []
    [][]
     */
    List<Coordinates> L = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(7, 1),
                    new Coordinates(7, 2, true),
                    new Coordinates(8, 2)
            )
    );

    /*
      []
      []
    [][]
     */
    List<Coordinates> L_Inv = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(7, 1),
                    new Coordinates(6, 2),
                    new Coordinates(7, 2, true)
            )
    );

    /*
      []
    [][][]
     */
    List<Coordinates> T = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(6, 1),
                    new Coordinates(7, 1, true),
                    new Coordinates(8, 1)
            )
    );

    /*
      [][]
    [][]
     */
    List<Coordinates> S = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(8,  0),
                    new Coordinates(6, 1),
                    new Coordinates(7, 1, true)
            )
    );

    /*
    [][]
      [][]
     */
    List<Coordinates> Z = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(6, 0),
                    new Coordinates(7, 0),
                    new Coordinates(8, 1),
                    new Coordinates(7, 1, true)
            )
    );

    /*
    [][][][]
     */
    List<Coordinates> I = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(7, 1),
                    new Coordinates(7, 2, true),
                    new Coordinates(7, 3)
            )
    );

    /*
    [][]
    [][]
     */
    List<Coordinates> Cube = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(8, 0),
                    new Coordinates(7, 1),
                    new Coordinates(8, 1)
            )
    );

}
