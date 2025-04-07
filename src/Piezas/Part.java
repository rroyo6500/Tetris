package Piezas;

import Piezas.Types.Parts;
import Utils.Board;
import Utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Part {

    private final Board<Integer> board;
    private List<List<Coordinates>> part;
    private Coordinates center;
    private Parts.Types type;
    private int rotation;
    private int ID;

    public Part(Board<Integer> board){
        this.board = board;
        newPart();
    }

    public void newPart(){
        type = Parts.getType();
        part = Parts.getPart(Parts.Types.S);
        center = Parts.getCenter(Parts.Types.S);
        ID = Parts.getID();
        rotation = 0;
    }

    public void down(){
        clearPart();

        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (part.get(i).get(j) != null) {
                    try {
                        if ((board.getPos(part.get(i).get(j).getX(),
                                part.get(i).get(j).getY() + 1) % 2) != 0) {
                            freeze();
                            return;
                        }
                    } catch (Exception _) {
                        freeze();
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (part.get(i).get(j) != null) {
                    part.get(i).get(j).setY(part.get(i).get(j).getY() + 1);
                }
            }
        }
        print(part);
    }

    public void Left(){
        clearPart();

        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (part.get(i).get(j) != null) {
                    try {
                        if ((board.getPos(part.get(i).get(j).getX() - 1,
                                part.get(i).get(j).getY()) % 2) != 0) {
                            print(part);
                            return;
                        }
                    } catch (Exception _) {
                        print(part);
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (part.get(i).get(j) != null) {
                    part.get(i).get(j).setX(part.get(i).get(j).getX() - 1);
                }
            }
        }
        print(part);
    }

    public void Right(){
        clearPart();

        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (part.get(i).get(j) != null) {
                    try {
                        if ((board.getPos(part.get(i).get(j).getX() + 1,
                                part.get(i).get(j).getY()) % 2) != 0) {
                            print(part);
                            return;
                        }
                    } catch (Exception _) {
                        print(part);
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (part.get(i).get(j) != null) {
                    part.get(i).get(j).setX(part.get(i).get(j).getX() + 1);
                }
            }
        }
        print(part);
    }

    public void rotate(){
        if (type == Parts.Types.Cube) { return; }
        clearPart();

        List<List<Coordinates>> resCoord = new ArrayList<>();
        resCoord.addAll(part);
        resCoord = rotateList(resCoord);

        if (comp(resCoord)) {
            print(part);
            return;
        }

        part = rotateList(Rotations.rotate(part, center));

        print(part);
    }

    private <T> List<List<T>> rotateList(List<List<T>> matrix){
        int numRows = matrix.size();
        int numCols = matrix.getFirst().size();

        List<List<T>> rotatedMatrix = new ArrayList<>();

        for (int col = 0; col < numCols; col++) {
            rotatedMatrix.add(new ArrayList<>());
        }

        for (int row = numRows - 1; row >= 0; row--) {
            for (int col = 0; col < numCols; col++) {
                rotatedMatrix.get(col).add(matrix.get(row).get(col));
            }
        }

        return rotatedMatrix;

    }

    private void clearPart(){
        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (part.get(i).get(j) != null) {
                    board.setPos(part.get(i).get(j).getX(), part.get(i).get(j).getY(),
                            board.getBase());
                }
            }
        }
    }

    private void freeze(){
        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (part.get(i).get(j) != null) {
                    board.setPos(part.get(i).get(j).getX(), part.get(i).get(j).getY(), (ID - 1));
                }
            }
        }
        newPart();
    }

    private boolean comp(List<List<Coordinates>> coordinates){
        boolean comp = false;
        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = 0; j < coordinates.getFirst().size(); j++) {
                if (coordinates.get(i).get(j) != null) {
                    if (board.getPos(coordinates.get(i).get(j).getX(), coordinates.get(i).get(j).getY()) != 0)
                        comp = true;
                }
            }
        }
        return comp;
    }

    private void print(List<List<Coordinates>> part){
        int i_ = part.size();
        int j_ = part.getFirst().size();
        for (int i = 0; i < i_; i++) {
            for (int j = 0; j < j_; j++) {
                if (part.get(i).get(j) != null) {
                    board.setPos(
                            part.get(i).get(j).getX(), part.get(i).get(j).getY(),
                            ID);
                }
            }
        }
    }

}