package Tetris;

import Tetris.Piezas.Part;
import Tetris.Utils.Board;
import Tetris.Utils.Coordinates;

import java.util.List;

public interface Var {

    Board _board = new Board(15, 30, 0);
    Part _part = new Part();

    List<Coordinates> _nextPart = _part.getNextPart().getPart();
    int _nextPartID = _part.getNextPart().getID();

}
