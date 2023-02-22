package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public abstract class Figure extends Logger{
    Random r = new Random();
    String type;
    public int positionX;
    public int positionY;
    public char symb;
    char color;
    public Figure(char color, int positionX, int positionY, String type, char symb){
        this.type = type;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.symb = symb;
    }
    /*
    private Character getSymbolByColorType(char color, String type){
        if(color == 'w'){
            if (type == "king")
                return '♔';
            if (type == "queen")
                return '♕';
            if (type == "bishop")
                return '♗';
            if (type == "knight")
                return '♘';
            if (type == "castle")
                return '♖';
            if (type == "pawn")
                return '♙';
        }else{
            if (type == "king")
                return '♚';
            if (type == "queen")
                return '♛';
            if (type == "bishop")
                return '♝';
            if (type == "knight")
                return '♞';
            if (type == "castle")
                return '♜';
            if (type == "pawn")
                return '♟';
        }
        if(type == "error")
            return 'e';
        if(type == "empty")
            return ' ';

        return 'e';
    }
    */

    public boolean move(Board board, ArrayList<Integer> resultPositions, char enemyColor){
        if (resultPositions.toArray().length > 0) {

            // random of king moves
            int rand = resultPositions.toArray().length > 1 ? r.nextInt(resultPositions.toArray().length - 1) : 0;
            int randPosX = resultPositions.get(rand) % 10;
            int randPosY = resultPositions.get(rand) / 10;

            if(board.getFigureOnPosition(randPosX, randPosY).color == enemyColor){
                if(enemyColor == 'w'){
                    board.whiteFigs.remove(board.getFigureOnPosition(randPosX, randPosY));
                }else{
                    board.blackFigs.remove(board.getFigureOnPosition(randPosX, randPosY));
                }
            }

            Figure targetFigure = board.getFigureOnPosition(positionX, positionY);

            System.out.println("Move: " + targetFigure.type + " : " + numToSymbPosX(positionX) + positionY + " -> " + numToSymbPosX(randPosX)  + randPosY);

            targetFigure.positionX = randPosX;
            targetFigure.positionY = randPosY;


            return true;
        }else{
            return false;
        }
    }
    public abstract boolean moveFigure(Board board);

}

