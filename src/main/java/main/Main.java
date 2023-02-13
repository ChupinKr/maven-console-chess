package main;

import model.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int move = 0;
        String status = "Start game";
        Board board = new Board();

        printBoard(board,move,status);

        while (board.moveAction(board.move)){
            status = "Continue game, move " + (board.move == 'w' ? "white" : "black") + ", move num " + move;
            printBoard(board,move,status);
            move++;
            Thread.sleep(1000);
        }

        status = "Game end, no moves " + (board.move == 'w' ? "white" : "black");
        printBoard(board,move,status);
    }

    public static void printBoard(Board board, int move, String status){
        System.out.printf(status + "\n");
    }
}
