package model;

import java.util.Random;

public class NaF extends Figure{
    public NaF(char color, int positionX, int positionY, String type, char symb){
        super(color, positionX, positionY, type, symb);
    }

    @Override
    public boolean moveFigure(Board board) {
        return false;
    }
}
