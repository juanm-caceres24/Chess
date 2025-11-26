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
        int row = position.getRow();
        int column = position.getColumn();
        if (row + 1 <= 7) {
            if (column + 2 <= 7) output.add(new Position(row + 1, column + 2));
            if (column - 2 >= 0) output.add(new Position(row + 1, column - 2));
        }
        if (row - 1 >= 0) {
            if (column + 2 <= 7) output.add(new Position(row - 1, column + 2));
            if (column - 2 >= 0) output.add(new Position(row - 1, column - 2));
        }
        if (row + 2 <= 7) {
            if (column + 1 <= 7) output.add(new Position(row + 2, column + 1));
            if (column - 1 >= 0) output.add(new Position(row + 2, column - 1));
        }
        if (row - 2 >= 0) {
            if (column + 1 <= 7) output.add(new Position(row - 2, column + 1));
            if (column - 1 >= 0) output.add(new Position(row - 2, column - 1));
        }
        return output;
    }

    public ArrayList<Position> possibleCaptures(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int row = position.getRow();
        int column = position.getColumn();
        if (row + 1 <= 7) {
            if (column + 2 <= 7) output.add(new Position(row + 1, column + 2));
            if (column - 2 >= 0) output.add(new Position(row + 1, column - 2));
        }
        if (row - 1 >= 0) {
            if (column + 2 <= 7) output.add(new Position(row - 1, column + 2));
            if (column - 2 >= 0) output.add(new Position(row - 1, column - 2));
        }
        if (row + 2 <= 7) {
            if (column + 1 <= 7) output.add(new Position(row + 2, column + 1));
            if (column - 1 >= 0) output.add(new Position(row + 2, column - 1));
        }
        if (row - 2 >= 0) {
            if (column + 1 <= 7) output.add(new Position(row - 2, column + 1));
            if (column - 1 >= 0) output.add(new Position(row - 2, column - 1));
        }
        return output;
    }
}
