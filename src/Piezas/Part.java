package Piezas;

import Main.Tetris_IG;
import Piezas.Types.Parts;
import Utils.Board;
import Utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Part {

    private final Board<Integer> board;
    private List<Coordinates> part = new ArrayList<>();
    private Coordinates center;
    private Parts.Types type;
    private int ID;

    public Part(Board<Integer> board){
        this.board = board;
        newPart();
    }

    public void newPart(){
        compLine();
        part = new ArrayList<>();

        type = Parts.getType();
        for (Coordinates c : type.getPart()) {
            part.add(new Coordinates(c.x(), c.y(), c.isCenter()));
        }
        ID = Parts.getID();
        center = type.getCenter(part);

        for (Coordinates c : part) {
            if ((board.getPos(c.x(), c.y()) % 2) != 0)
                Tetris_IG.stop();
        }

        print(part);
    }

    private void compLine(){
        while (true) {
            int count = 0;
            for (Integer p : board.getBoard().getLast()) {
                if (p != 0) {
                    count++;
                }
            }
            if (count == board.getWith()) {
                board.reset(board.getBoard().size() - 1);
                board.moveDown();
            } else break;
        }
    }

    public void down(){
        clearPart();

        for (Coordinates c : part) {
            try {
                if (board.getPos(c.x(), (c.y() + 1)) != 0) {
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

        print(part);
    }

    public void Left(){
        clearPart();

        for (Coordinates c : part) {
            try {
                if (board.getPos((c.x() - 1), c.y()) != 0) {
                    print(part);
                    return;
                }
            } catch (Exception _) {
                print(part);
                return;
            }
        }

        for (Coordinates c : part) {
            c.setX(c.x() - 1);
        }

        print(part);
    }

    public void Right(){
        clearPart();

        for (Coordinates c : part) {
            try {
                if (board.getPos((c.x() + 1), c.y()) != 0) {
                    print(part);
                    return;
                }
            } catch (Exception _) {
                print(part);
                return;
            }
        }

        for (Coordinates c : part) {
            c.setX(c.x() + 1);
        }

        print(part);
    }

    public void rotate(){
        if (type == Parts.Types.Cube) { return; }
        clearPart();

        List<Coordinates> resCoord = new ArrayList<>();
        for (Coordinates c : part) {
            resCoord.add(new Coordinates(c.x(), c.y(), c.isCenter()));
        }
        rotateCoords(resCoord);

        if (comp(resCoord)) {
            print(part);
            return;
        }

        rotateCoords(part);

        print(part);
    }

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

    private void clearPart(){
        for (Coordinates c : part) {
            board.setPos(c.x(), c.y(), board.getBase());
        }
    }

    private void freeze(){
        for (Coordinates c : part) {
            board.setPos(c.x(), c.y(), (ID - 1));
        }
        newPart();
    }

    private boolean comp(List<Coordinates> coordinates){
        boolean comp = false;
        for (Coordinates c : part) {
            try{
                if (board.getPos(c.x(), c.y()) != 0)
                    comp = true;
            } catch (Exception _) {
                comp = true;
            }
        }
        return comp;
    }

    private void print(List<Coordinates> part){
        for (Coordinates c : part) {
            board.setPos(c.x(), c.y(), ID);
        }
    }

}