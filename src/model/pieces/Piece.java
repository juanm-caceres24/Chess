package src.model.pieces;

import java.util.ArrayList;

import src.utils.Position;
import src.utils.enums.ColorEnum;
import src.utils.enums.PieceEnum;

public abstract class Piece {

    protected PieceEnum name;
    protected ColorEnum color;
    // Indicates if the piece can jump over other pieces
    protected boolean canJump;
    // Indicates if the piece has moved at least once
    protected boolean wasMoved;

    public Piece(PieceEnum name, ColorEnum color, boolean canJump) {
        this.name = name;
        this.color = color;
        this.canJump = canJump;
        wasMoved = false;
    }

    // Returns an ArrayList of possible movements for the piece at the given position
    public abstract ArrayList<Position> possibleMovements(Position pos);

    // Returns an ArrayList of possible captures for the piece at the given position
    public abstract ArrayList<Position> possibleCaptures(Position pos);

    public final PieceEnum getName() { return name; }
    public final ColorEnum getColor() { return color; }
    public final boolean getCanJump() { return canJump; }
    public final boolean getWasMoved() { return wasMoved; }
    public final void setWasMoved(boolean wasMoved) { this.wasMoved = wasMoved; }
}
