package Piezas;

import Piezas.Types.Parts;
import Utils.Coordinates;

import java.util.List;

public class Rotations {

    private static final List<List<Coordinates>> res = new java.util.ArrayList<>();

    public static List<List<Coordinates>> rotate(Parts.Types types, List<List<Coordinates>> part, int rotation){
        res.addAll(part);
        part.clear();
        return switch (types){
            case L -> L(rotation);
            case L_Inv -> L_Inv(rotation);
            case T -> T(rotation);
            case S -> S(rotation);
            case Z -> Z(rotation);
            case I -> I(rotation);
            case Cube -> Cube(rotation);
        };
    }

    private static List<List<Coordinates>> L(int rotation){

        switch (rotation){
            case 0 -> {

            }
            case 1 -> {

            }
            case 2 -> {

            }
            case 3 -> {

            }
        }

        return res;
    }
    private static List<List<Coordinates>> L_Inv(int rotation){

        return res;
    }
    private static List<List<Coordinates>> T(int rotation){

        return res;
    }
    private static List<List<Coordinates>> S(int rotation){

        return res;
    }
    private static List<List<Coordinates>> Z(int rotation){

        return res;
    }
    private static List<List<Coordinates>> I(int rotation){

        return res;
    }
    private static List<List<Coordinates>> Cube(int rotation){

        return res;
    }

}
