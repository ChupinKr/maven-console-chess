package model;

import java.util.ArrayList;
import java.util.Random;

public class King extends Figure {

    public King(char color, int positionX, int positionY, String type, char symb) {
        super(color, positionX, positionY, type, symb);
    }

    @Override
    public boolean moveFigure(Board board) {
        ArrayList<Integer> resultPositions = new ArrayList<>();
        //check color
        char enemyColor;
        if (color == 'w') {
            enemyColor = 'b';
        } else {
            enemyColor = 'w';
        }

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (board.getFigureOnPosition(positionX + x, positionY + y).symb == 'n'
                        || board.getFigureOnPosition(positionX + x, positionY + y).color == enemyColor
                ) {
                    resultPositions.add((positionY + y) * 10 + positionX + x);
                }
            }
        }
        return move(board, resultPositions, enemyColor);
    }
}

