package model;

import java.util.ArrayList;
import java.util.Random;

public class Queen extends Figure{
    public Queen(char color, int positionX, int positionY, String type, char symb){
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
        //add directions
        ArrayList<Direction> dirs = new ArrayList<>();
        dirs.add(new Direction("right",  1, 0));
        dirs.add(new Direction("left",  -1, 0));
        dirs.add(new Direction("up", 0, 1));
        dirs.add(new Direction("down",  0, -1));
        dirs.add(new Direction("rightup",  1, 1));
        dirs.add(new Direction("rightdown",  1, -1));
        dirs.add(new Direction("leftup",  -1, 1));
        dirs.add(new Direction("leftdown",  -1, -1));

        while(dirs.toArray().length > 0) {
            for (int i = 0; i < dirs.toArray().length;i++) {
                if (board.getFigureOnPosition(positionX + dirs.get(i).x, positionY + dirs.get(i).y).symb == 'n') {
                    resultPositions.add((positionY + dirs.get(i).y) * 10 + positionX + dirs.get(i).x);
                } else if (board.getFigureOnPosition(positionX + dirs.get(i).x, positionY + dirs.get(i).y).color == enemyColor) {
                    resultPositions.add((positionY + dirs.get(i).y) * 10 + positionX + dirs.get(i).x);
                    dirs.remove(dirs.get(i));
                    i--;
                } else {
                    dirs.remove(dirs.get(i));
                    i--;
                }
            }
            for(Direction dir : dirs){
                if(dir.x != 0) {
                    dir.x += dir.x > 0 ? 1 : -1;
                }
                if(dir.y != 0) {
                    dir.y += dir.y > 0 ? 1 : -1;
                }
            }
        }
        //logging
        //for(int i : resultPositions){
        //    System.out.println(i%10 + " " + i/10);
        //}

        return move(board, resultPositions, enemyColor);
    }


}
