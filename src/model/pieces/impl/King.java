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
        int row = position.getRow();
        int column = position.getColumn();
        if (column + 1 <= 7) {
            output.add(new Position(row, column + 1));
            if (row + 1 <= 7)
                output.add(new Position(row + 1, column + 1));
            if (row - 1 >= 0)
                output.add(new Position(row - 1, column + 1));
        }
        if (column - 1 >= 0) {
            output.add(new Position(row, column - 1));
            if (row + 1 <= 7)
                output.add(new Position(row + 1, column - 1));
            if (row - 1 >= 0)
                output.add(new Position(row - 1, column - 1));
        }
        if (row + 1 <= 7)
            output.add(new Position(row + 1, column));
        if (row - 1 >= 0)
            output.add(new Position(row - 1, column));
        return output;
    }

    public ArrayList<Position> possibleCaptures(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int row = position.getRow();
        int column = position.getColumn();
        if (column + 1 <= 7) {
            output.add(new Position(row, column + 1));
            if (row + 1 <= 7)
                output.add(new Position(row + 1, column + 1));
            if (row - 1 >= 0)
                output.add(new Position(row - 1, column + 1));
        }
        if (column - 1 >= 0) {
            output.add(new Position(row, column - 1));
            if (row + 1 <= 7)
                output.add(new Position(row + 1, column - 1));
            if (row - 1 >= 0)
                output.add(new Position(row - 1, column - 1));
        }
        if (row + 1 <= 7)
            output.add(new Position(row + 1, column));
        if (row - 1 >= 0)
            output.add(new Position(row - 1, column));
        return output;
    }
}
