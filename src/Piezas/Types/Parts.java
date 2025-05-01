package Piezas.Types;

import Utils.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parts {

    public enum Types {
        L(Parts.L, 2),
        L_Inv(Parts.L_Inv, 3),
        T(Parts.T, 2),
        S(Parts.S, 3),
        Z(Parts.Z, 3),
        I(Parts.I, 2),
        Cube(Parts.Cube);

        private final List<Coordinates> part;
        private int center;

        Types(List<Coordinates> part, int center) {
            this.part = part;
            this.center = center;
        }
        Types(List<Coordinates> part) { this.part = part; }

        public List<Coordinates> getPart() {
            return part;
        }
        public Coordinates getCenter(List<Coordinates> part) {
            if (this == Cube) return null;
            else return part.get(center);
        }
    }

    public static Types getType(){
        int rand = (int)(Math.random()*Types.values().length);
        return Types.values()[rand];
    }

    public static int getID(){
        int rand = (int)(Math.random()*Types.values().length);
        return switch (rand){
            case 0 -> 2;
            case 1 -> 4;
            case 2 -> 6;
            case 3 -> 8;
            case 4 -> 10;
            case 5 -> 12;
            case 6 -> 14;
            default -> throw new IllegalStateException("Unexpected value: " + rand);
        };
    }

    private static final List<Coordinates> L = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(7, 1),
                    new Coordinates(7, 2, true),
                    new Coordinates(8, 2)
            ));
    private static final List<Coordinates> L_Inv = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(7, 1),
                    new Coordinates(6, 2),
                    new Coordinates(7, 2, true)
            ));
    private static final List<Coordinates> T = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(6, 1),
                    new Coordinates(7, 1, true),
                    new Coordinates(8, 1)
            ));
    private static final List<Coordinates> S = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(8,  0),
                    new Coordinates(6, 1),
                    new Coordinates(7, 1, true)
            ));
    private static final List<Coordinates> Z = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(6, 0),
                    new Coordinates(7, 0),
                    new Coordinates(8, 1),
                    new Coordinates(7, 1, true)
            ));
    private static final List<Coordinates> I = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(7, 1),
                    new Coordinates(7, 2, true),
                    new Coordinates(7, 3)
            ));
    private static final List<Coordinates> Cube = new ArrayList<>(
            Arrays.asList(
                    new Coordinates(7, 0),
                    new Coordinates(8, 0),
                    new Coordinates(7, 1),
                    new Coordinates(8, 1)
            ));
}