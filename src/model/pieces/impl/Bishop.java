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
        int row = position.getRow();
        int column = position.getColumn();
        for (int i = 1; i < 7; i++) {
            if (row + i <= 7) {
                if (column + i <= 7) output.add(new Position(row + i, column + i));
                if (column - i >= 0) output.add(new Position(row + i, column - i));
            }
            if (row - i >= 0) {
                if (column + i <= 7) output.add(new Position(row - i, column + i));
                if (column - i >= 0) output.add(new Position(row - i, column - i));
            }
        }
        return output;
    }

    public ArrayList<Position> possibleCaptures(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int row = position.getRow();
        int column = position.getColumn();
        for (int i = 1; i < 7; i++) {
            if (row + i <= 7) {
                if (column + i <= 7) output.add(new Position(row + i, column + i));
                if (column - i >= 0) output.add(new Position(row + i, column - i));
            }
            if (row - i >= 0) {
                if (column + i <= 7) output.add(new Position(row - i, column + i));
                if (column - i >= 0) output.add(new Position(row - i, column - i));
            }
        }
        return output;
    }
}
