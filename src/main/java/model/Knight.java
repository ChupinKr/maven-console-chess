package model;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Knight extends Figure{
    public Knight(char color, int positionX, int positionY, String type, char symb){
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
        int[] params = {-2,-1,1,2};

        for(int x : params) {
            for(int y : params) {
                if ((board.getFigureOnPosition(positionX + x, positionY + y).symb == 'n'
                        || board.getFigureOnPosition(positionX + x, positionY + y).color == enemyColor)
                        && abs(x) != abs(y)
                ) {
                    resultPositions.add((positionY + y) * 10 + positionX + x);
                }
            }
        }

        return move(board, resultPositions, enemyColor);
    }
}
