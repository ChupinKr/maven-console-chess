package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class Figure {
    Random r = new Random();
    String type;
    public int positionX;
    public int positionY;
    public char symb;
    char color;
    public Figure(char color, int positionX, int positionY, String type){
        this.type = type;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.symb = getSymbolByColorType(color,type);
    }
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
    public boolean tryMoveFigure(Board board){
        if(type == "pawn"){
                return movePawn(board);
            }
        if(type == "king"){
            return moveKing(board);
        }
        if(type == "castle"){
            return moveCastle(board);
        }
        if(type == "knight") {
            return moveKnight(board);
        }
        if(type == "bishop") {
            return moveBishop(board);
        }
        if(type == "queen") {
            return moveQueen(board);
        }
        return false;
    }

    public boolean move(Board board, ArrayList<Integer> resultPositions, char enemyColor){
        if (resultPositions.toArray().length > 0) {

            //sout random of pawn moves
            int rand = resultPositions.toArray().length > 1 ? r.nextInt(resultPositions.toArray().length - 1) : 0;

            if(board.getFigureOnPosition(resultPositions.get(rand) % 10, resultPositions.get(rand) / 10).color == enemyColor){
                if(enemyColor == 'w'){
                    board.whiteFigs.remove(board.getFigureOnPosition(resultPositions.get(rand) % 10, resultPositions.get(rand) / 10));
                }else{
                    board.blackFigs.remove(board.getFigureOnPosition(resultPositions.get(rand) % 10, resultPositions.get(rand) / 10));
                }
            }

            Figure targetFigure = board.getFigureOnPosition(positionX, positionY);

            System.out.println("Move: " + targetFigure.type + " XY:" + positionX + " " + positionY + " -> XY:" + resultPositions.get(rand) % 10 + " " + resultPositions.get(rand) / 10);

            targetFigure.positionX = resultPositions.get(rand) % 10;
            targetFigure.positionY = resultPositions.get(rand) / 10;


            return true;
        }else{
            return false;
        }
    }

    public boolean movePawn(Board board) {
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
        if (board.getFigureOnPosition(positionX, positionY + step).symb == ' '
                && board.getFigureOnPosition(positionX, positionY + step).symb != 'e')
        {
            if (positionY == startPos && board.getFigureOnPosition(positionX, positionY + 2 * step).symb == ' ') {
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

    public boolean moveKing(Board board){
        ArrayList<Integer> resultPositions = new ArrayList<>();
        //check color
        char enemyColor;
        if(color == 'w'){
            enemyColor = 'b';
        }else {
            enemyColor = 'w';
        }

        for(int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (board.getFigureOnPosition(positionX + x, positionY + y).symb == ' '
                        || board.getFigureOnPosition(positionX + x, positionY + y).color == enemyColor
                ) {
                    resultPositions.add((positionY + y) * 10 + positionX + x);
                }
            }
        }
        return move(board, resultPositions, enemyColor);
    }

    public boolean moveCastle(Board board){
        ArrayList<Integer> resultPositions = new ArrayList<>();
        //check color
        char enemyColor;
        if(color == 'w'){
            enemyColor = 'b';
        }else {
            enemyColor = 'w';
        }

        for(int x = positionX + 1; x < 9; x++) {
            if(board.getFigureOnPosition(x, positionY).symb != ' ' && board.getFigureOnPosition(x, positionY).color != enemyColor){
                break;
            }
            if (board.getFigureOnPosition(x, positionY).symb == ' ') {
                resultPositions.add((positionY) * 10 + x);
            }
            if(board.getFigureOnPosition(x, positionY).color == enemyColor){
                resultPositions.add((positionY) * 10 + x);
                break;
            }
        }
        for(int x = positionX - 1; x > 0; x--) {
            if(board.getFigureOnPosition(x, positionY).symb != ' ' && board.getFigureOnPosition(x, positionY).color != enemyColor){
                break;
            }
            if (board.getFigureOnPosition(x, positionY).symb == ' ') {
                resultPositions.add((positionY) * 10 + x);
            }
            if(board.getFigureOnPosition(x, positionY).color == enemyColor){
                resultPositions.add((positionY) * 10 + x);
                break;
            }
        }
        for(int y = positionY + 1; y < 9; y++) {
            if(board.getFigureOnPosition(positionX, y).symb != ' ' && board.getFigureOnPosition(positionX, y).color != enemyColor){
                break;
            }
            if (board.getFigureOnPosition(positionX, y).symb == ' ') {
                resultPositions.add((y) * 10 + positionX);
            }
            if(board.getFigureOnPosition(positionX, y).color == enemyColor){
                resultPositions.add((y) * 10 + positionX);
                break;
            }
        }
        for(int y = positionY - 1; y > 0; y--) {
            if(board.getFigureOnPosition(positionX, y).symb != ' ' && board.getFigureOnPosition(positionX, y).color != enemyColor){
                break;
            }
            if (board.getFigureOnPosition(positionX, y).symb == ' ') {
                resultPositions.add((y) * 10 + positionX);
            }
            if(board.getFigureOnPosition(positionX, y).color == enemyColor){
                resultPositions.add((y) * 10 + positionX);
                break;
            }
        }
        return move(board, resultPositions, enemyColor);
    }

    public boolean moveKnight(Board board){
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
                if ((board.getFigureOnPosition(positionX + x, positionY + y).symb == ' '
                        || board.getFigureOnPosition(positionX + x, positionY + y).color == enemyColor)
                        && abs(x) != abs(y)
                ) {
                    resultPositions.add((positionY + y) * 10 + positionX + x);
                }
            }
        }

        return move(board, resultPositions, enemyColor);
    }

    public boolean moveBishop(Board board){
        ArrayList<Integer> resultPositions = new ArrayList<>();
        //check color
        char enemyColor;
        if(color == 'w'){
            enemyColor = 'b';
        }else {
            enemyColor = 'w';
        }

        for(int pos = 1; positionX + pos < 9 && positionY + pos < 9; pos++) {
            if(board.getFigureOnPosition(positionX + pos, positionY + pos).symb != ' '
                    && board.getFigureOnPosition(positionX + pos, positionY + pos).color != enemyColor){
                break;
            }
            if (board.getFigureOnPosition(positionX + pos, positionY + pos).symb == ' ') {
                resultPositions.add((positionY + pos) * 10 + positionX + pos);
            }
            if(board.getFigureOnPosition(positionX + pos, positionY + pos).color == enemyColor){
                resultPositions.add((positionY + pos) * 10 + positionX + pos);
                break;
            }
        }
        for(int pos = 1; positionX - pos > 0 && positionY - pos > 0; pos++) {
            if(board.getFigureOnPosition(positionX - pos, positionY - pos).symb != ' '
                    && board.getFigureOnPosition(positionX - pos, positionY - pos).color != enemyColor){
                break;
            }
            if (board.getFigureOnPosition(positionX - pos, positionY - pos).symb == ' ') {
                resultPositions.add((positionY - pos) * 10 + positionX - pos);
            }
            if(board.getFigureOnPosition(positionX - pos, positionY - pos).color == enemyColor){
                resultPositions.add((positionY - pos) * 10 + positionX - pos);
                break;
            }
        }
        for(int pos = 1; positionX - pos > 0 && positionY + pos < 9; pos++) {
            if(board.getFigureOnPosition(positionX - pos, positionY + pos).symb != ' '
                    && board.getFigureOnPosition(positionX - pos, positionY + pos).color != enemyColor){
                break;
            }
            if (board.getFigureOnPosition(positionX - pos, positionY + pos).symb == ' ') {
                resultPositions.add((positionY + pos) * 10 + positionX - pos);
            }
            if(board.getFigureOnPosition(positionX - pos, positionY + pos).color == enemyColor){
                resultPositions.add((positionY + pos) * 10 + positionX - pos);
                break;
            }
        }
        for(int pos = 1; positionX + pos < 9 && positionY - pos > 0; pos++) {
            if(board.getFigureOnPosition(positionX + pos, positionY - pos).symb != ' '
                    && board.getFigureOnPosition(positionX + pos, positionY - pos).color != enemyColor){
                break;
            }
            if (board.getFigureOnPosition(positionX + pos, positionY - pos).symb == ' ') {
                resultPositions.add((positionY - pos) * 10 + positionX + pos);
            }
            if(board.getFigureOnPosition(positionX + pos, positionY - pos).color == enemyColor){
                resultPositions.add((positionY - pos) * 10 + positionX + pos);
                break;
            }
        }

        return move(board, resultPositions, enemyColor);
    }

    public boolean moveQueen(Board board){
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
                if (board.getFigureOnPosition(positionX + dirs.get(i).x, positionY + dirs.get(i).y).symb == ' ') {
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

