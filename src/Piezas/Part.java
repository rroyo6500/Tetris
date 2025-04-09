package Piezas;

import Piezas.Types.Parts;
import Utils.Board;
import Utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Part {

    private final Board<Integer> board;
    private List<List<Coordinates>> part = new ArrayList<>();
    private Coordinates center;
    private Parts.Types type;
    private int ID;

    public Part(Board<Integer> board){
        this.board = board;
        newPart();
    }

    public void newPart(){
        part.clear();

        ID = Parts.getID();
        type = Parts.getType();
        center = type.getCenter();

        for (List<Coordinates> f : type.getPart()) {
            part.add(new ArrayList<>(){{
                for (Coordinates c : f) {
                    if (c != null) {
                        add(new Coordinates(c.getX(), c.getY(), c.isCenter()));
                    }
                }
            }});
        }

        print(part);
    }

    public void down(){
        clearPart();

        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    try {
                        if (board.getPos(c.getX(), (c.getY() + 1)) != 0) {
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

        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    c.setY(c.getY() + 1);
                }
            }
        }

        print(part);
    }

    public void Left(){
        clearPart();

        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    try {
                        if (board.getPos((c.getX() - 1), c.getY()) != 0) {
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

        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    c.setX(c.getX() - 1);
                }
            }
        }

        print(part);
    }

    public void Right(){
        clearPart();

        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    try {
                        if (board.getPos((c.getX() + 1), c.getY()) != 0) {
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

        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    c.setX(c.getX() + 1);
                }
            }
        }

        print(part);
    }

    public void rotate(){
        if (type == Parts.Types.Cube) { return; }
        clearPart();

        List<List<Coordinates>> resCoord = new ArrayList<>();
        for (List<Coordinates> f : part) {
            resCoord.add(new ArrayList<>(){{
                for (Coordinates c : f) {
                    if (c != null) {
                        add(new Coordinates(c.getX(), c.getY()));
                    }
                }
            }});
        }
        resCoord = rotateList(Rotations.rotate(resCoord, center, type));

        if (comp(resCoord)) {
            print(part);
            return;
        }

        part = rotateList(Rotations.rotate(part, center, type));

        print(part);
    }

    private <T> List<List<T>> rotateList(List<List<T>> matrix){
        int rows = matrix.size();
        int cols = matrix.getFirst().size();

        List<List<T>> rotatedMatrix = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            rotatedMatrix.add(new ArrayList<>());
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                rotatedMatrix.get(j).add(matrix.get(i).get(j));
            }
        }

        return rotatedMatrix;

    }

    private void clearPart(){
        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    board.setPos(c.getX(), c.getY(), board.getBase());
                }
            }
        }
    }

    private void freeze(){
        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    board.setPos(c.getX(), c.getY(), (ID - 1));
                }
            }
        }
        newPart();
    }

    private boolean comp(List<List<Coordinates>> coordinates){
        boolean comp = false;
        for (List<Coordinates> f : coordinates) {
            for (Coordinates c : f) {
                if (c != null) {
                    try{
                        if (board.getPos(c.getX(), c.getY()) != 0)
                            comp = true;
                    } catch (Exception _) {
                        comp = true;
                    }
                }
            }
        }
        return comp;
    }

    private void print(List<List<Coordinates>> part){
        for (List<Coordinates> f : part) {
            for (Coordinates c : f) {
                if (c != null) {
                    board.setPos(c.getX(), c.getY(), ID);
                }
            }
        }
    }

}