package model;

public class Logger {
    public static char numToSymbPosX(int num){
        switch (num){
            case (1):
                return 'A';
            case (2):
                return 'B';
            case (3):
                return 'C';
            case (4):
                return 'D';
            case (5):
                return 'E';
            case (6):
                return 'F';
            case (7):
                return 'G';
            case (8):
                return 'H';
            default:
        }
        return 'n';
    }
    public static void printBoard(Board board, int move, String status){
        System.out.printf(status + "\n");
    }
}
