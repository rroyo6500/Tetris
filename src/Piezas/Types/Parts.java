package Piezas.Types;

import Utils.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parts {

    public enum Types {
        L(Parts.L, Parts.L.get(2).getFirst()),
        L_Inv(Parts.L_Inv, Parts.L_Inv.get(2).getLast()),
        T(Parts.T, Parts.T.get(1).get(1)),
        S(Parts.S, Parts.S.get(1).get(1)),
        Z(Parts.Z, Parts.Z.get(1).get(1)),
        I(Parts.I, Parts.I.get(2).getFirst()),
        Cube(Parts.Cube, null);

        private final List<List<Coordinates>> part;
        private final Coordinates center;

        Types(List<List<Coordinates>> part, Coordinates center) {
            this.part = part;
            this.center = center;
        }

        public List<List<Coordinates>> getPart() {
            return part;
        }
        public Coordinates getCenter() {
            return center;
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

    private static final List<List<Coordinates>> L = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList(new Coordinates(7, 0), null),
                    Arrays.asList(new Coordinates(7, 1), null),
                    Arrays.asList(new Coordinates(7, 2, true), new Coordinates(8, 2))
            ));
    private static final List<List<Coordinates>> L_Inv = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList(null, new Coordinates(7, 0)),
                    Arrays.asList(null, new Coordinates(7, 1)),
                    Arrays.asList(new Coordinates(6, 2), new Coordinates(7, 2, true))
            ));
    private static final List<List<Coordinates>> T = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList(null, new Coordinates(7, 0), null),
                    Arrays.asList(new Coordinates(6, 1), new Coordinates(7, 1, true), new Coordinates(8, 1))
            ));
    private static final List<List<Coordinates>> S = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList(null, new Coordinates(7, 0), new Coordinates(8, 1)),
                    Arrays.asList(new Coordinates(6, 1), new Coordinates(7, 1, true), null)
            ));
    private static final List<List<Coordinates>> Z = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList(new Coordinates(6, 0), new Coordinates(7, 0), null),
                    Arrays.asList(null, new Coordinates(7, 1, true), new Coordinates(8, 1))
            ));
    private static final List<List<Coordinates>> I = new ArrayList<>(
            Arrays.asList(
                    List.of(new Coordinates(7, 0)),
                    List.of(new Coordinates(7, 1)),
                    List.of(new Coordinates(7, 2, true)),
                    List.of(new Coordinates(7, 3))
            ));
    private static final List<List<Coordinates>> Cube = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList(new Coordinates(7, 0), new Coordinates(8, 0)),
                    Arrays.asList(new Coordinates(7, 1), new Coordinates(8, 1))
            ));
}

/*
Arrays.asList(
                    Arrays.asList(null, new Coordinates(7, 0), null),
                    Arrays.asList(new Coordinates(6, 1), new Coordinates(7, 1, true), new Coordinates(8, 1))
            ));
 */