package Piezas.Types;

import Utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Parts {

    public enum Types {
        L,
        L_Inv,
        T,
        S,
        Z,
        I,
        Cube
    }

    public static Types getType(){
        int rand = (int)(Math.random()*Types.values().length);
        return switch (rand) {
            case 0 -> Types.L;
            case 1 -> Types.L_Inv;
            case 2 -> Types.T;
            case 3 -> Types.S;
            case 4 -> Types.Z;
            case 5 -> Types.I;
            case 6 -> Types.Cube;
            default -> throw new IllegalStateException("Unexpected value: " + rand);
        };
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

    public static Coordinates getCenter(Types type){
        return switch (type){
            case L -> L.get(2).getFirst();
            case L_Inv -> L_Inv.get(2).getLast();
            case T -> T.get(1).get(1);
            case S -> S.get(1).get(1);
            case Z -> Z.get(1).get(1);
            case I -> I.get(1).getFirst();
            case Cube -> null;
        };
    }

    public static List<List<Coordinates>> getPart(Types type){
        return switch (type){
            case L -> L;
            case L_Inv -> L_Inv;
            case T -> T;
            case S -> S;
            case Z -> Z;
            case I -> I;
            case Cube -> Cube;
        };
    }

    private static final List<List<Coordinates>> L = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(new Coordinates(7, 0)); add(null); }});
        add(new ArrayList<>(){{ add(new Coordinates(7, 1)); add(null); }});
        add(new ArrayList<>(){{ add(new Coordinates(7, 2, true)); add(new Coordinates(8, 2)); }});
    }};
    private static final List<List<Coordinates>> L_Inv = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(null); add(new Coordinates(7, 0)); }});
        add(new ArrayList<>(){{ add(null); add(new Coordinates(7, 1)); }});
        add(new ArrayList<>(){{ add(new Coordinates(6, 2)); add(new Coordinates(7, 2, true)); }});
    }};
    private static final List<List<Coordinates>> T = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(null); add(new Coordinates(7, 0)); add(null); }});
        add(new ArrayList<>(){{ add(new Coordinates(6, 1)); add(new Coordinates(7, 1, true)); add(new Coordinates(8, 1)); }});
    }};
    private static final List<List<Coordinates>> S = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(null); add(new Coordinates(7, 0)); add(new Coordinates(8, 0)); }});
        add(new ArrayList<>(){{ add(new Coordinates(6, 1)); add(new Coordinates(7, 1, true)); add(null); }});
    }};
    private static final List<List<Coordinates>> Z = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(new Coordinates(6, 0)); add(new Coordinates(7, 0)); add(null); }});
        add(new ArrayList<>(){{ add(null); add(new Coordinates(7, 1, true)); add(new Coordinates(8, 1)); }});
    }};
    private static final List<List<Coordinates>> I = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(new Coordinates(7, 0)); }});
        add(new ArrayList<>(){{ add(new Coordinates(7, 1, true)); }});
        add(new ArrayList<>(){{ add(new Coordinates(7, 2)); }});
        add(new ArrayList<>(){{ add(new Coordinates(7, 3)); }});
    }};
    private static final List<List<Coordinates>> Cube = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(new Coordinates(7, 0)); add(new Coordinates(8, 0)); }});
        add(new ArrayList<>(){{ add(new Coordinates(7, 1)); add(new Coordinates(8, 1)); }});
    }};

}
