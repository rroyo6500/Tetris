package Piezas;

import Piezas.Types.Parts;
import Utils.Coordinates;

import java.util.List;

public class Rotations {

    private static final List<List<Coordinates>> res = new java.util.ArrayList<>();

    public static List<List<Coordinates>> rotate(List<List<Coordinates>> part, Coordinates center, Parts.Types type) {
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

        switch (type) {
            case S, Z: {
                LUp(center, x, y);
                RUp(center, x, y);
                RDown(center, x, y);
                LDown(center, x, y);
            }
            case L, L_Inv, T, I: {
                left(center, x, y);
                right(center, x, y);
                up(center, x, y);
                down(center, x, y);
                break;
            }
        }

        return res;
    }

    private static void left(Coordinates center, int x, int y) {
        try {
            if (res.get(y).get(x - 1) != null) {
                res.get(y).get(x - 1).setX(center.getX());
                res.get(y).get(x - 1).setY(center.getY() - 1);

                res.get(y).get(x - 2).setX(center.getX());
                res.get(y).get(x - 2).setY(center.getY() - 2);
            }
        } catch (Exception _) {}
    }
    private static void right(Coordinates center, int x, int y) {
        try {
            if (res.get(y).get(x + 1) != null) {
                res.get(y).get(x + 1).setX(center.getX());
                res.get(y).get(x + 1).setY(center.getY() + 1);

                res.get(y).get(x + 2).setX(center.getX());
                res.get(y).get(x + 2).setY(center.getY() + 2);
            }
        } catch (Exception _) {}
    }
    private static void up(Coordinates center, int x, int y) {
        try {
            if (res.get(y - 1).get(x) != null) {
                res.get(y - 1).get(x).setX(center.getX() + 1);
                res.get(y - 1).get(x).setY(center.getY());

                res.get(y - 2).get(x).setX(center.getX() + 2);
                res.get(y - 2).get(x).setY(center.getY());
            }
        } catch (Exception _) {}
    }
    private static void down(Coordinates center, int x, int y) {
        try {
            if (res.get(y + 1).get(x) != null) {
                res.get(y + 1).get(x).setX(center.getX() - 1);
                res.get(y + 1).get(x).setY(center.getY());

                res.get(y + 2).get(x).setX(center.getX() - 2);
                res.get(y + 2).get(x).setY(center.getY());
            }
        } catch (Exception _) {}
    }

    private static void LUp(Coordinates center, int x, int y) {
        try {
            if (res.get(y - 1).get(x - 1) != null) {
                res.get(y - 1).get(x - 1).setX(center.getX() + 1);
            }
        } catch (Exception _) {}
    }

    private static void RUp(Coordinates center, int x, int y) {
        try {
            if (res.get(y - 1).get(x + 1) != null) {
                res.get(y - 1).get(x + 1).setY(center.getY() + 1);
            }
        } catch (Exception _) {}
    }

    private static void RDown(Coordinates center, int x, int y) {
        try {
            if (res.get(y + 1).get(x + 1) != null) {
                res.get(y + 1).get(x + 1).setX(center.getX() - 1);
            }
        } catch (Exception _) {}
    }

    private static void LDown(Coordinates center, int x, int y) {
        try {
            if (res.get(y + 1).get(x - 1) != null) {
                res.get(y + 1).get(x - 1).setY(center.getY() - 1);
            }
        } catch (Exception _) {}
    }

}
