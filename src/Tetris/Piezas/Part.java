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
    private final List<Coordinates> part = new ArrayList<>();

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
        nextPart = getRandomType();
        assert nextPart != null;

        if (copy) {
            _board.reset();
            copyPart();
        }
    }

    /**
     * Copia la pieza pregenerada de 'nextPart'
     */
    public void copyPart(){
        clearCompleteLines();

        // Copiamos el tipo de pieza de 'nextPart'
        type = nextPart;

        // Limpiamos la anterior pieza y añadimos la nueva.
        part.clear();
        for (Coordinates c : type.getPart()){
            part.add(new Coordinates(c.x(), c.y(), c.isCenter()));
        }

        // Añadimos el centro de la nueva pieza.
        center = type.getCenter(part);

        // Comprueba si hay espacio dispoible para que aparezca la pieza.
        // En caso de no haber si indicara al jugador que la partida ha finalizado.
        for (Coordinates c : part) {
            if ((_board.getPos(c.x(), c.y()) % 2) != 0) {

                _gameTimer.stopTimers();

                _startButton.setVisible(false);
                _stopButton.setVisible(false);
                _gameOverPanel.setVisible(true);
                _gameOverPanel.repaint();

                break;
            }
        }

        // Generamos una nueva pieza en 'nextPart'
        newPart(false);

        // Pintamos la nueva pieza en el tabblero
        printPart();

        _nextPartPanel.repaint();
    }

    // Logica

    /**
     * Pinta la pieza en el tablero
     */
    private void printPart(){
        // Pintamos la ID de la pieza en las coordenadas de cada uno de los bloques de la pieza (dentro del tabblero).
        for (Coordinates c : part)
            _board.setPos(c.x(), c.y(), type.getID());
    }

    /**
     * Comprueba si hay lineas completas.
     * En caso de haber las limpia y baja todo el tablero.
     */
    private void clearCompleteLines(){
        int countOfLines = 0;
        for (int y = (_board.getHeight() - 1); y >= 0; y--) {
            int count = 0;
            for (int x = 0; x < _board.getWith(); x++) {
                if (_board.getPos(x, y) != 0) {
                    count++;
                }
            }
            if (count == _board.getWith()) {
                _board.reset(y);
                _board.moveDown(y);
                countOfLines++;

                y++;
            }
        }

        _level.addXP(countOfLines);
        _xpLabel.setText(_level.getXP() + " EXP");
        _linesLabel.setText(_level.getCompleteLines() + " Lines");
    }

    /**
     * Elimina la pieza del tablero.
     */
    private void clearPart(){
        for (Coordinates c : part)
            _board.setPos(c.x(), c.y(), _board.getBase());
    }

    /**
     * Comprueba si la pieza tiene espacio suficiente para moverse / rotar.
     * @return 'True' si hay espacio suficiente.
     *         'False' si no hay espacio.
     */
    private boolean compSpace(List<Coordinates> part){
        boolean comp = true;

        // Comprobbamos que las coordenadas no estan ocupadas.
        for (Coordinates c : part) {
            try{
                if (_board.getPos(c.x(), c.y()) != 0)
                    comp = false;
            } catch (Exception _) {
                comp = false;
            }
        }

        return comp;
    }

    /**
     * Congela la pieza en su posicion actual y crea una nueva pieza.
     */
    private void freeze(){
        for (Coordinates c : part) {
            _board.setPos(c.x(), c.y(), (type.getID() - 1));
        }
        copyPart();
    }

    // Movimiento

    /**
     * Mueve la pieza actual 1 hacia abajo en el tablero. Al colisionar congela la pieza.
     */
    public void down(){
        clearPart();

        for (Coordinates c : part) {
            try {
                if (_board.getPos(c.x(), (c.y() + 1)) != 0) {
                    freeze();
                    return;
                }
            } catch (Exception _) {
                freeze();
                return;
            }
        }

        for (Coordinates c : part) {
            c.setY(c.y() + 1);
        }

        printPart();
    }

    /**
     * Mueve la pieza actual 1 hacia la izquierda en el tablero.
     */
    public void Left(){
        clearPart();

        for (Coordinates c : part) {
            try {
                if (_board.getPos((c.x() - 1), c.y()) != 0) {
                    printPart();
                    return;
                }
            } catch (Exception _) {
                printPart();
                return;
            }
        }

        for (Coordinates c : part) {
            c.setX(c.x() - 1);
        }

        printPart();
    }

    /**
     * Mueve la pieza actual 1 hacia la derecha en el tablero.
     */
    public void Right(){
        clearPart();

        for (Coordinates c : part) {
            try {
                if (_board.getPos((c.x() + 1), c.y()) != 0) {
                    printPart();
                    return;
                }
            } catch (Exception _) {
                printPart();
                return;
            }
        }

        for (Coordinates c : part) {
            c.setX(c.x() + 1);
        }

        printPart();
    }

    // Rotacion

    /**
     * Rota la pieza 90º ( ↻ ).
     */
    public void rotate(){
        // Comprobamos que la pieza no es un Cubo (esto no puede rotar)
        if (type == Types.Cube) {
            return;
        }
        // Limpiamos la pieza del tablero
        clearPart();

        // Creamos una copia de la piezay rotamos sus coordenadas para comprobar si entra antes de rotar la pieza original.
        List<Coordinates> resCoord = new ArrayList<>();
        for (Coordinates c : part) {
            resCoord.add(new Coordinates(c.x(), c.y(), c.isCenter()));
        }
        rotateCoords(resCoord);

        // Comprobamos que la pieza puede rotar.
        if (!compSpace(resCoord)) {
            printPart();
            return;
        }

        // rotamos la pieza original.
        rotateCoords(part);

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
     * @return Random part type from Types enum
     */
    default Types getRandomType(){
        int rand = (int)(Math.random()*Types.values().length);
        return Types.values()[rand];
    }

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

        private final List<Coordinates> part;
        private final int ID;
        private int center;

        Types(List<Coordinates> part, int ID, Integer center) {
            this.part = part;
            this.ID = ID;
            this.center = center;
        }
        Types(List<Coordinates> part, int ID) {
            this.part = part;
            this.ID = ID;
        }

        /**
         * @return Lista de coordenadas de la pieza
         */
        public List<Coordinates> getPart() {
            return part;
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
            ));

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
            ));

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
            ));

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
            ));

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
            ));

    /*
    [][][][]
     */
    List<Coordinates> I = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(7, 1),
                    new Coordinates(7, 2, true),
                    new Coordinates(7, 3)
            ));

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
            ));

}
