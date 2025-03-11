package Piezas;

import Utils.Coordinates;

import java.util.List;

public class Rotations {

    private static final List<List<Coordinates>> res = new java.util.ArrayList<>();

    public static List<List<Coordinates>> rotate(List<List<Coordinates>> part, Coordinates center) {
        res.clear();
        res.addAll(part);
        part.clear();

        int x = 0, y = 0;
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < res.getFirst().size(); j++) {
                if (res.get(i).get(j) != null && res.get(i).get(j).isCenter()) {
                    y = i;
                    x = j;
                }
            }
        }

        left(center, x, y);
        right(center, x, y);
        up(center, x, y);
        down(center, x, y);

        return res;
    }

    private static void left(Coordinates center, int x, int y) {
        try {
            if (res.get(y).get(x - 1) != null) {
                res.get(y).get(x - 1).setX(center.getX());
                res.get(y).get(x - 1).setY(center.getY() - 1);
            }
        } catch (Exception _) {}
    }
    private static void right(Coordinates center, int x, int y) {
        try {
            if (res.get(y).get(x + 1) != null) {
                res.get(y).get(x + 1).setX(center.getX());
                res.get(y).get(x + 1).setY(center.getY() + 1);
            }
        } catch (Exception _) {}
    }
    private static void up(Coordinates center, int x, int y) {
        try {
            if (res.get(y - 1).get(x) != null) {
                res.get(y - 1).get(x).setX(center.getX() + 1);
                res.get(y - 1).get(x).setY(center.getY());
            }
        } catch (Exception _) {}
    }
    private static void down(Coordinates center, int x, int y) {
        try {
            if (res.get(y + 1).get(x) != null) {
                res.get(y + 1).get(x).setX(center.getX() - 1);
                res.get(y + 1).get(x).setY(center.getY());
            }
        } catch (Exception _) {}
    }

}
