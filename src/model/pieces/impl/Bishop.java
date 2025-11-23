package src.model.pieces.impl;

import java.util.ArrayList;

import src.model.pieces.Piece;
import src.utils.Position;
import src.utils.enums.ColorEnum;
import src.utils.enums.PieceEnum;

public class Bishop extends Piece {

    public Bishop(ColorEnum color) {
        super(PieceEnum.BISHOP, color, false);
    }

    public ArrayList<Position> possibleMovements(Position position) {
        ArrayList<Position> output = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        for (int i = 1; i < 7; i++) {
            if (y + i <= 7) {
                if (x + i <= 7)
                    output.add(new Position(x + i, y + i));
                if (x - i >= 0)
                    output.add(new Position(x - i, y + i));
            }
            if (y - i >= 0) {
                if (x + i <= 7)
                    output.add(new Position(x + i, y - i));
                if (x - i >= 0)
                    output.add(new Position(x - i, y - i));
            }
        }
        return output;
    }

    public ArrayList<Position> possibleTakes(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = position.getX();
        int y = position.getY();
        for (int i = 1; i < 7; i++) {
            if (y + i <= 7) {
                if (x + i <= 7)
                    output.add(new Position(x + i, y + i));
                if (x - i >= 0)
                    output.add(new Position(x - i, y + i));
            }
            if (y - i >= 0) {
                if (x + i <= 7)
                    output.add(new Position(x + i, y - i));
                if (x - i >= 0)
                    output.add(new Position(x - i, y - i));
            }
        }
        return output;
    }
}
