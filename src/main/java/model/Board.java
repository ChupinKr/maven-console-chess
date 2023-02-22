package model;

import java.util.ArrayList;
import java.util.Random;

import static model.Logger.numToSymbPosX;

public class Board{
    Random r = new Random();
    ArrayList<Figure> blackFigs;
    ArrayList<Figure> whiteFigs;

    public char move = 'w';
    public Board(){
        //add black figures on start game
        blackFigs = new ArrayList<>();
        blackFigs.add(new Castle('b', 1, 8, "castle", '♜'));
        blackFigs.add(new Knight('b', 2, 8, "knight", '♞'));
        blackFigs.add(new Bishop('b', 3, 8, "bishop", '♝'));
        blackFigs.add(new Queen('b', 4, 8, "queen", '♛'));
        blackFigs.add(new King('b', 5, 8, "king", '♚'));
        blackFigs.add(new Bishop('b', 6, 8, "bishop", '♝'));
        blackFigs.add(new Knight('b', 7, 8, "knight", '♞'));
        blackFigs.add(new Castle('b', 8, 8, "castle", '♜'));
        for(int i = 1; i < 9; i++){
            blackFigs.add(new Pawn('b', i, 7, "pawn", '♟'));
        }
        //add white figures on start game
        whiteFigs = new ArrayList<>();
        whiteFigs.add(new Castle('w',1, 1, "castle", '♖'));
        whiteFigs.add(new Knight('w',2, 1, "knight", '♘'));
        whiteFigs.add(new Bishop('w',3, 1, "bishop", '♗'));
        whiteFigs.add(new Queen('w',4, 1, "queen", '♕'));
        whiteFigs.add(new King('w',5, 1, "king", '♔'));
        whiteFigs.add(new Bishop('w',6, 1, "bishop", '♗'));
        whiteFigs.add(new Knight('w',7, 1, "knight", '♘'));
        whiteFigs.add(new Castle('w',8, 1, "castle", '♖'));
        for(int i = 1; i < 9; i++){
            whiteFigs.add(new Pawn('w', i, 2, "pawn", '♙'));
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
            return new NaF('n', posX, posY, "error", 'e');
        }

        return new NaF('n', posX, posY, "none", 'n');
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

            if(myFigures.get(rand).moveFigure(this)){
                //change queue move
                if (color == 'w'){
                    move = 'b';
                }else{
                    move = 'w';
                }
                return true;
            }else {
                System.out.println("---No moves--- for " + myFigures.get(rand).type +" : " + numToSymbPosX(myFigures.get(rand).positionX) +  myFigures.get(rand).positionY);
                myFigures.remove(myFigures.get(rand));
            }
        }

        return false;
    }




}
