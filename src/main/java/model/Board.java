package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Board {
    Random r = new Random();
    ArrayList<Figure> blackFigs;
    ArrayList<Figure> whiteFigs;

    public char move = 'w';
    public Board(){
        //add black figures on start game
        blackFigs = new ArrayList<Figure>();
        blackFigs.add(new Figure('b', 1, 8, "castle"));
        blackFigs.add(new Figure('b', 2, 8, "knight"));
        blackFigs.add(new Figure('b', 3, 8, "bishop"));
        blackFigs.add(new Figure('b', 4, 8, "queen"));
        blackFigs.add(new Figure('b', 5, 8, "king"));
        blackFigs.add(new Figure('b', 6, 8, "bishop"));
        blackFigs.add(new Figure('b', 7, 8, "knight"));
        blackFigs.add(new Figure('b', 8, 8, "castle"));
        for(int i = 1; i < 9; i++){
            blackFigs.add(new Figure('b', i, 7, "pawn"));
        }
        //add white figures on start game
        whiteFigs = new ArrayList<Figure>();
        whiteFigs.add(new Figure('w',1, 1, "castle"));
        whiteFigs.add(new Figure('w',2, 1, "knight"));
        whiteFigs.add(new Figure('w',3, 1, "bishop"));
        whiteFigs.add(new Figure('w',4, 1, "queen"));
        whiteFigs.add(new Figure('w',5, 1, "king"));
        whiteFigs.add(new Figure('w',6, 1, "bishop"));
        whiteFigs.add(new Figure('w',7, 1, "knight"));
        whiteFigs.add(new Figure('w',8, 1, "castle"));
        for(int i = 1; i < 9; i++){
            whiteFigs.add(new Figure('w', i, 2, "pawn"));
        }
    }

    public Figure getFigureOnPosition(int posX, int posY){
        for(int i = 0; i < blackFigs.toArray().length; i++){
            if(blackFigs.get(i).positionX == posX && blackFigs.get(i).positionY == posY){
                return blackFigs.get(i);
            }
        }
        for(int i = 0; i < whiteFigs.toArray().length; i++){
            if(whiteFigs.get(i).positionX == posX && whiteFigs.get(i).positionY == posY){
                return whiteFigs.get(i);
            }
        }
        if(posX > 8 || posY > 8 || posX < 1 || posY < 1){
            return new Figure('n', posX, posY, "error");
        }

        return new Figure('n', posX, posY, "empty");
    }

    public boolean moveAction(char color){
        ArrayList<Figure> myFigures;
        if (color == 'w'){
            myFigures = new ArrayList<>(whiteFigs);
        }else{
            myFigures = new ArrayList<>(blackFigs);
        }
        System.out.println("\n-------------------Move " + (color == 'w' ? "white" : "black") + "--------------------------------");
        while(myFigures.toArray().length > 0){
            int rand =  myFigures.toArray().length > 1 ? r.nextInt(myFigures.toArray().length - 1) : 0;

            if(myFigures.get(rand).tryMoveFigure(this)){
                //change queue move
                if (color == 'w'){
                    move = 'b';
                }else{
                    move = 'w';
                }
                return true;
            }else {
                System.out.println("---No moves--- for " + myFigures.get(rand).type +" X:" + myFigures.get(rand).positionX + " Y: " + myFigures.get(rand).positionY);
                myFigures.remove(myFigures.get(rand));
            }
        }

        return false;
    }

}
