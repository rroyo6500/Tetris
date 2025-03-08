package Piezas.Types;

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

    public Parts(){}

    public static Types getType(){
        int rand = (int)(Math.random()*7);
        return switch (rand) {
            case 0 -> Types.L;
            case 1 -> Types.L_Inv;
            case 2 -> Types.T;
            case 3 -> Types.S;
            case 4 -> Types.Z;
            case 5 -> Types.I;
            case 6 -> Types.Cube;
            default -> null;
        };
    }

    public static List<List<Integer>> getPart(Types type){
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

    private static final List<List<Integer>> L = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(2); add(0); }});
        add(new ArrayList<>(){{ add(2); add(0); }});
        add(new ArrayList<>(){{ add(2); add(2); }});
    }};
    private static final List<List<Integer>> L_Inv = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(0); add(4); }});
        add(new ArrayList<>(){{ add(0); add(4); }});
        add(new ArrayList<>(){{ add(4); add(4); }});
    }};
    private static final List<List<Integer>> T = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(0); add(6); add(0); }});
        add(new ArrayList<>(){{ add(6); add(6); add(6); }});
    }};
    private static final List<List<Integer>> S = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(0); add(8); add(8); }});
        add(new ArrayList<>(){{ add(8); add(8); add(0); }});
    }};
    private static final List<List<Integer>> Z = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(10); add(10); add(0); }});
        add(new ArrayList<>(){{ add(0); add(10); add(10); }});
    }};
    private static final List<List<Integer>> I = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(12); }});
        add(new ArrayList<>(){{ add(12); }});
        add(new ArrayList<>(){{ add(12); }});
        add(new ArrayList<>(){{ add(12); }});
    }};
    private static final List<List<Integer>> Cube = new ArrayList<>(){{
        add(new ArrayList<>(){{ add(14); add(14); }});
        add(new ArrayList<>(){{ add(14); add(14); }});
    }};

}
