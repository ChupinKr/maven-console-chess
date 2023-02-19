package model;

import java.util.ArrayList;
import java.util.Random;

public class Pawn  extends Figure{
    public Pawn(char color, int positionX, int positionY, String type, char symb){
        super(color, positionX, positionY, type, symb);
    }

    @Override
    public boolean moveFigure(Board board){
        ArrayList<Integer> resultPositions = new ArrayList<>();
        //check color
        char enemyColor;
        int startPos;
        int step;
        if (color == 'w') {
            enemyColor = 'b';
            startPos = 2;
            step = 1;

        } else {
            enemyColor = 'w';
            startPos = 7;
            step = -1;
        }
        //move check
        if (board.getFigureOnPosition(positionX, positionY + step).symb == 'n')
        {
            if (positionY == startPos && board.getFigureOnPosition(positionX, positionY + 2 * step).symb == 'n') {
                resultPositions.add((positionY + 2 * step) * 10 + positionX);
            }
            resultPositions.add((positionY + step) * 10 + positionX);
        }

        //attack check
        if (board.getFigureOnPosition(positionX + 1, positionY + step).color == enemyColor)
            resultPositions.add((positionY + step) * 10 + positionX + 1);
        if (board.getFigureOnPosition(positionX - 1, positionY + step).color == enemyColor)
            resultPositions.add((positionY + step) * 10 + positionX - 1);

        //for(int i : resultPositions){
        //    System.out.println(i%10 + " " + i/10);
        //}

        return move(board, resultPositions, enemyColor);
    }

}
