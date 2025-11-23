package src.model.pieces.impl;

import java.util.ArrayList;

import src.model.pieces.Piece;
import src.utils.Position;
import src.utils.enums.ColorEnum;
import src.utils.enums.PieceEnum;

public class Knight extends Piece {

    public Knight(ColorEnum color) {
        super(PieceEnum.KNIGHT, color, true);
    }

    public ArrayList<Position> possibleMovements(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = position.getX();
        int y = position.getY();
        if (y + 1 <= 7) {
            if (x + 2 <= 7)
                output.add(new Position(x + 2, y + 1));
            if (x - 2 >= 0)
                output.add(new Position(x - 2, y + 1));
        }
        if (y - 1 >= 0) {
            if (x + 2 <= 7)
                output.add(new Position(x + 2, y - 1));
            if (x - 2 >= 0)
                output.add(new Position(x - 2, y - 1));
        }
        if (y + 2 <= 7) {
            if (x + 1 <= 7)
                output.add(new Position(x + 1, y + 2));
            if (x - 1 >= 0)
                output.add(new Position(x - 1, y + 2));
        }
        if (y - 2 >= 0) {
            if (x + 1 <= 7)
                output.add(new Position(x + 1, y - 2));
            if (x - 1 >= 0)
                output.add(new Position(x - 1, y - 2));
        }
        return output;
    }

    public ArrayList<Position> possibleTakes(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = position.getX();
        int y = position.getY();
        if (y + 1 <= 7) {
            if (x + 2 <= 7)
                output.add(new Position(x + 2, y + 1));
            if (x - 2 >= 0)
                output.add(new Position(x - 2, y + 1));
        }
        if (y - 1 >= 0) {
            if (x + 2 <= 7)
                output.add(new Position(x + 2, y - 1));
            if (x - 2 >= 0)
                output.add(new Position(x - 2, y - 1));
        }
        if (y + 2 <= 7) {
            if (x + 1 <= 7)
                output.add(new Position(x + 1, y + 2));
            if (x - 1 >= 0)
                output.add(new Position(x - 1, y + 2));
        }
        if (y - 2 >= 0) {
            if (x + 1 <= 7)
                output.add(new Position(x + 1, y - 2));
            if (x - 1 >= 0)
                output.add(new Position(x - 1, y - 2));
        }
        return output;
    }
}
