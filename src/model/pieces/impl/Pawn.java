package src.model.pieces.impl;

import java.util.ArrayList;

import src.model.pieces.Piece;
import src.utils.Position;
import src.utils.enums.ColorEnum;
import src.utils.enums.PieceEnum;

public class Pawn extends Piece {
    
    public Pawn(ColorEnum color) {
        super(PieceEnum.PAWN, color, false);
    }

    public ArrayList<Position> possibleMovements(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int row = position.getRow();
        int column = position.getColumn();
        if (row + 1 <= 7 && color == ColorEnum.BLACK) output.add(new Position(row + 1, column));
        if (row - 1 >= 0 && color == ColorEnum.WHITE) output.add(new Position(row - 1, column));
        if (row + 2 <= 7 && color == ColorEnum.BLACK && !wasMoved) output.add(new Position(row + 2, column));
        if (row - 2 >= 0 && color == ColorEnum.WHITE && !wasMoved) output.add(new Position(row - 2, column));
        return output;
    }

    public ArrayList<Position> possibleCaptures(Position position) {
        ArrayList<Position> output = new ArrayList<Position>();
        int row = position.getRow();
        int column = position.getColumn();
        if (row + 1 <= 7 && column + 1 <= 7 && color == ColorEnum.BLACK) output.add(new Position(row + 1, column + 1));
        if (row - 1 >= 0 && column + 1 <= 7 && color == ColorEnum.WHITE) output.add(new Position(row - 1, column + 1));
        if (row + 1 <= 7 && column - 1 >= 0 && color == ColorEnum.BLACK) output.add(new Position(row + 1, column - 1));
        if (row - 1 >= 0 && column - 1 >= 0 && color == ColorEnum.WHITE) output.add(new Position(row - 1, column - 1));
        return output;
    }
}
