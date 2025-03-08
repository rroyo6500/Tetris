package Piezas;

import Piezas.Types.Parts;
import Utils.Board;
import Utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Part {

    private final Board<Integer> board;
    private List<List<Integer>> part;
    private List<List<Coordinates>> coordinates;

    public Part(Board<Integer> board){
        this.board = board;
        newPart();
    }

    public void newPart(){
        Parts.Types type = Parts.getType();
        if (type == null) return;
        part = Parts.getPart(type);

        final int rand = part.getFirst().size() == 2 ? (int)(Math.random()*2) : 0;
        coordinates = new ArrayList<>(){{
            for (int i = 0; i < part.size(); i++) {
                int finalI = i;
                add(new ArrayList<>(){{
                    for (int j = 0; j < part.getFirst().size(); j++) {
                        switch (part.getFirst().size()){
                            case 1:{
                                if (part.get(finalI).get(j) != 0) {
                                    add(new Coordinates(7, finalI));
                                } else add(null);
                                break;
                            }
                            case 2:{
                                switch (rand){
                                    case 0: {
                                        if (part.get(finalI).get(j) != 0) {
                                            if (j == 0) add(new Coordinates(7, finalI));
                                            else if (j == 1) add(new Coordinates(8, finalI));
                                        } else add(null);
                                        break;
                                    }
                                    case 1: {
                                        if (part.get(finalI).get(j) != 0) {
                                            if (j == 0) add(new Coordinates(6, finalI));
                                            else if (j == 1) add(new Coordinates(7, finalI));
                                        } else add(null);
                                        break;
                                    }
                                }
                                break;
                            }
                            case 3:{
                                if (part.get(finalI).get(j) != 0) {
                                    if (j == 0) add(new Coordinates(6, finalI));
                                    else if (j == 1) add(new Coordinates(7, finalI));
                                    else if (j == 2) add(new Coordinates(8, finalI));
                                } else add(null);
                            }
                        }
                    }
                }});
            }
        }};
        if (!comp()) print();
        else System.exit(0);
    }

    public void down(){

        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = 0; j < coordinates.getFirst().size(); j++) {
                if (coordinates.get(i).get(j) != null) {
                    board.setPos(coordinates.get(i).get(j).getX(), coordinates.get(i).get(j).getY(),
                            board.getBase());
                }
            }
        }

        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = 0; j < coordinates.getFirst().size(); j++) {
                if (coordinates.get(i).get(j) != null) {
                    if ((board.getPos(coordinates.get(i).get(j).getX(),
                                    coordinates.get(i).get(j).getY() + 1) % 2) != 0)
                    {
                        freeze();
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = 0; j < coordinates.getFirst().size(); j++) {
                if (coordinates.get(i).get(j) != null) {
                    coordinates.get(i).get(j).setY(coordinates.get(i).get(j).getY() + 1);
                }
            }
        }
        print();
    }

    private void freeze(){
        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = 0; j < coordinates.getFirst().size(); j++) {
                if (coordinates.get(i).get(j) != null) {
                    board.setPos(coordinates.get(i).get(j).getX(), coordinates.get(i).get(j).getY(),
                            (part.get(i).get(j) - 1));
                }
            }
        }
        newPart();
    }

    private boolean comp(){
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

    private void print(){
        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.getFirst().size(); j++) {
                if (coordinates.get(i).get(j) != null) {
                    board.setPos(
                            coordinates.get(i).get(j).getX(), coordinates.get(i).get(j).getY(),
                            part.get(i).get(j));
                }
            }
        }
    }

}