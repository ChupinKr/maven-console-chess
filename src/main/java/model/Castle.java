package model;

import java.util.ArrayList;
import java.util.Random;

public class Castle extends Figure{
    public Castle(char color, int positionX, int positionY, String type, char symb){
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

        for(int x = positionX + 1; x < 9; x++) {
            if(board.getFigureOnPosition(x, positionY).symb == 'e'){
                break;
            }
            if (board.getFigureOnPosition(x, positionY).symb == 'n') {
                resultPositions.add((positionY) * 10 + x);
            }
            if(board.getFigureOnPosition(x, positionY).color == enemyColor){
                resultPositions.add((positionY) * 10 + x);
                break;
            }
        }
        for(int x = positionX - 1; x > 0; x--) {
            if(board.getFigureOnPosition(x, positionY).symb == 'e'){
                break;
            }
            if (board.getFigureOnPosition(x, positionY).symb == 'n') {
                resultPositions.add((positionY) * 10 + x);
            }
            if(board.getFigureOnPosition(x, positionY).color == enemyColor){
                resultPositions.add((positionY) * 10 + x);
                break;
            }
        }
        for(int y = positionY + 1; y < 9; y++) {
            if(board.getFigureOnPosition(positionX, y).symb == 'e'){
                break;
            }
            if (board.getFigureOnPosition(positionX, y).symb == 'n') {
                resultPositions.add((y) * 10 + positionX);
            }
            if(board.getFigureOnPosition(positionX, y).color == enemyColor){
                resultPositions.add((y) * 10 + positionX);
                break;
            }
        }
        for(int y = positionY - 1; y > 0; y--) {
            if(board.getFigureOnPosition(positionX, y).symb == 'e'){
                break;
            }
            if (board.getFigureOnPosition(positionX, y).symb == 'n') {
                resultPositions.add((y) * 10 + positionX);
            }
            if(board.getFigureOnPosition(positionX, y).color == enemyColor){
                resultPositions.add((y) * 10 + positionX);
                break;
            }
        }
        return move(board, resultPositions, enemyColor);
    }
}
