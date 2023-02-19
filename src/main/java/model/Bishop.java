package model;

import java.util.ArrayList;
import java.util.Random;

public class Bishop extends Figure{
    public Bishop(char color, int positionX, int positionY, String type, char symb){
        super(color, positionX, positionY, type, symb);
    }

    @Override
    public boolean moveFigure(Board board){
        ArrayList<Integer> resultPositions = new ArrayList<>();
        //check color
        char enemyColor;
        if(color == 'w'){
            enemyColor = 'b';
        }else {
            enemyColor = 'w';
        }

        for(int pos = 1; positionX + pos < 9 && positionY + pos < 9; pos++) {
            if(board.getFigureOnPosition(positionX + pos, positionY + pos).symb == 'e'){
                break;
            }
            if (board.getFigureOnPosition(positionX + pos, positionY + pos).symb == 'n') {
                resultPositions.add((positionY + pos) * 10 + positionX + pos);
            }
            if(board.getFigureOnPosition(positionX + pos, positionY + pos).color == enemyColor){
                resultPositions.add((positionY + pos) * 10 + positionX + pos);
                break;
            }
        }
        for(int pos = 1; positionX - pos > 0 && positionY - pos > 0; pos++) {
            if(board.getFigureOnPosition(positionX - pos, positionY - pos).symb == 'e'){
                break;
            }
            if (board.getFigureOnPosition(positionX - pos, positionY - pos).symb == 'n') {
                resultPositions.add((positionY - pos) * 10 + positionX - pos);
            }
            if(board.getFigureOnPosition(positionX - pos, positionY - pos).color == enemyColor){
                resultPositions.add((positionY - pos) * 10 + positionX - pos);
                break;
            }
        }
        for(int pos = 1; positionX - pos > 0 && positionY + pos < 9; pos++) {
            if(board.getFigureOnPosition(positionX - pos, positionY + pos).symb  == 'e'){
                break;
            }
            if (board.getFigureOnPosition(positionX - pos, positionY + pos).symb == 'n') {
                resultPositions.add((positionY + pos) * 10 + positionX - pos);
            }
            if(board.getFigureOnPosition(positionX - pos, positionY + pos).color == enemyColor){
                resultPositions.add((positionY + pos) * 10 + positionX - pos);
                break;
            }
        }
        for(int pos = 1; positionX + pos < 9 && positionY - pos > 0; pos++) {
            if(board.getFigureOnPosition(positionX + pos, positionY - pos).symb  == 'e'){
                break;
            }
            if (board.getFigureOnPosition(positionX + pos, positionY - pos).symb == 'n') {
                resultPositions.add((positionY - pos) * 10 + positionX + pos);
            }
            if(board.getFigureOnPosition(positionX + pos, positionY - pos).color == enemyColor){
                resultPositions.add((positionY - pos) * 10 + positionX + pos);
                break;
            }
        }

        return move(board, resultPositions, enemyColor);
    }
}
