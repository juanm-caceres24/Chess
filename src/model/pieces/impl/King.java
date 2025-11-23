package src.model.pieces.impl;

import java.util.ArrayList;

import src.model.pieces.Piece;
import src.utils.Position;
import src.utils.enums.ColorEnum;
import src.utils.enums.PieceEnum;

public class King extends Piece {

    public King(ColorEnum color) {
        super(PieceEnum.KING, color, false);
    }

    public ArrayList<Position> possibleMovements(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = position.getX();
        int y = position.getY();
        if (y + 1 <= 7) {
            output.add(new Position(x, y + 1));
            if (x + 1 <= 7)
                output.add(new Position(x + 1, y + 1));
            if (x - 1 >= 0)
                output.add(new Position(x - 1, y + 1));
        }
        if (y - 1 >= 0) {
            output.add(new Position(x, y - 1));
            if (x + 1 <= 7)
                output.add(new Position(x + 1, y - 1));
            if (x - 1 >= 0)
                output.add(new Position(x - 1, y - 1));
        }
        if (x + 1 <= 7)
            output.add(new Position(x + 1, y));
        if (x - 1 >= 0)
            output.add(new Position(x - 1, y));
        return output;
    }

    public ArrayList<Position> possibleTakes(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = position.getX();
        int y = position.getY();
        if (y + 1 <= 7) {
            output.add(new Position(x, y + 1));
            if (x + 1 <= 7)
                output.add(new Position(x + 1, y + 1));
            if (x - 1 >= 0)
                output.add(new Position(x - 1, y + 1));
        }
        if (y - 1 >= 0) {
            output.add(new Position(x, y - 1));
            if (x + 1 <= 7)
                output.add(new Position(x + 1, y - 1));
            if (x - 1 >= 0)
                output.add(new Position(x - 1, y - 1));
        }
        if (x + 1 <= 7)
            output.add(new Position(x + 1, y));
        if (x - 1 >= 0)
            output.add(new Position(x - 1, y));
        return output;
    }
}
