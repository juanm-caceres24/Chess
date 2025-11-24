package src;

import java.util.ArrayList;

import src.model.Board;
import src.utils.Position;
import src.utils.enums.ColorEnum;
import src.utils.enums.PieceEnum;
import src.model.pieces.Piece;

public class GameService {

    private Board board;
    
    public GameService(Board board) {
        this.board = board;
    }

    public boolean checkMove(Position from, Position to, ColorEnum currentPlayer) {

        // Check 'from' position board bounds
        if (from.getRow() < 0 || from.getRow() >= 8 || from.getColumn() < 0 || from.getColumn() >= 8) return false;
        // Get the piece at 'from' position
        Piece pieceToMove = board.getBoard()[from.getRow()][from.getColumn()];
        // Check 'from' position piece
        if (pieceToMove == null) return false;
        // Check the piece color
        if (pieceToMove.getColor() != currentPlayer) return false;

        // Check 'to' position board bounds
        if (to.getRow() < 0 || to.getRow() >= 8 || to.getColumn() < 0 || to.getColumn() >= 8) return false;
        // Get the piece at 'to' position
        Piece targetPiece = board.getBoard()[to.getRow()][to.getColumn()];
        // Check 'to' position piece
        if (targetPiece != null) {
            // Check color piece
            if (targetPiece.getColor() == currentPlayer) {
                // Check castling move
                if (pieceToMove.getName() != PieceEnum.KING || pieceToMove.getName() != PieceEnum.ROOK) return false;
                // Check if the pieces have been moved before
                if (pieceToMove.getWasMoved() || !targetPiece.getWasMoved()) return false;
                // Check if the path between king and rook is clear
                if (!checkPiecesInPath(from, to)) return false;
                // Check if the king is in check or passes through check --- TO BE IMPLEMENTED ---
            } else {
                // 
            }
        } else {

        }
    }

    public boolean checkPiecesInPath(Position from, Position to) {
        ArrayList<Position> pathPositions = new ArrayList<>();
        int rowDirection = Integer.signum(to.getRow() - from.getRow());
        int columnDirection = Integer.signum(to.getColumn() - from.getColumn());
        int currentRow = from.getRow() + rowDirection;
        int currentColumn = from.getColumn() + columnDirection;
        while (currentRow != to.getRow() || currentColumn != to.getColumn()) {
            pathPositions.add(new Position(currentRow, currentColumn));
            currentRow += rowDirection;
            currentColumn += columnDirection;
        }
        for (Position position : pathPositions) {
            if (board.getBoard()[position.getRow()][position.getColumn()] != null) {
                return false;
            }
        }
        return true;
    }
}
