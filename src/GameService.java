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

    /*
     * METHODS
     */

    /*
     * Return codes:
     * -10 = king is currently in check
     * -9 = positions in castling are in check
     * -8 = king would be in check after move
     * -7 = not a valid move for the piece
     * -6 = pieces in the path
     * -5 = pieces already moved (castling)
     * -4 = cannot capture own piece
     * -3 = not the player's piece
     * -2 = no piece to move
     * -1 = out of bounds
     * 0 = normal move
     * 1 = capture move
     * 2 = castling move
     * 3 = promotion move
     */
    public int checkMove(Position from, Position to, ColorEnum currentPlayer) {
        // Check 'from' position board bounds
        if (from.getRow() < 0 || from.getRow() >= 8 || from.getColumn() < 0 || from.getColumn() >= 8) return -1;
        // Get the piece at 'from' position
        Piece pieceToMove = board.getBoard()[from.getRow()][from.getColumn()];
        // Check 'from' position piece
        if (pieceToMove == null) return -2;
        // Check the piece color
        if (pieceToMove.getColor() != currentPlayer) return -3;
        // Check 'to' position board bounds
        if (to.getRow() < 0 || to.getRow() >= 8 || to.getColumn() < 0 || to.getColumn() >= 8) return -1;
        // Get the piece at 'to' position
        Piece targetPiece = board.getBoard()[to.getRow()][to.getColumn()];
        // Check 'to' position piece
        if (targetPiece != null) {
            // Check color piece
            if (targetPiece.getColor() == currentPlayer) {
                // Castling: must be king moving towards a rook of same color
                if (pieceToMove.getName() != PieceEnum.KING || targetPiece.getName() != PieceEnum.ROOK) return -4;
                // King and rook must be on the same row
                if (from.getRow() != to.getRow()) return -7;
                // King and rook must not have moved
                if (pieceToMove.getWasMoved() || targetPiece.getWasMoved()) return -5;
                // Path between king and rook (excluding endpoints) must be clear
                if (!checkPiecesInPath(from, to)) return -6;
                // Determine direction the king will move (towards the rook)
                int step = Integer.signum(to.getColumn() - from.getColumn());
                // King final destination is two squares towards the rook
                Position kingStepPosition = new Position(from.getRow(), from.getColumn() + step);
                Position kingFinalPosition = new Position(from.getRow(), from.getColumn() + 2 * step);
                // The king cannot be in check at the start
                if (isKingInCheck(currentPlayer)) return -10;
                // The king cannot pass through a square that is under attack
                if (simulateMoveAndCheck(from, kingStepPosition, currentPlayer)) return -9;
                // The king cannot end in check
                if (simulateMoveAndCheck(from, kingFinalPosition, currentPlayer)) return -8;
                // If all checks pass, it's a valid castling move
                return 2;
            } else {
                // Check if the move is valid for the piece
                if (!pieceToMove.possibleCaptures(from).contains(to)) return -7;
                // If the piece cannot jump, check for pieces in the path
                if (!pieceToMove.getCanJump()) {
                    if (!checkPiecesInPath(from, to)) return -6;
                }
                // Check if it's promotion move
                if (pieceToMove.getName() == PieceEnum.PAWN) {
                    // Check if the pawn reaches the last row
                    if ((pieceToMove.getColor() == ColorEnum.WHITE && to.getRow() == 0) ||
                        (pieceToMove.getColor() == ColorEnum.BLACK && to.getRow() == 7)) return 3;
                }
                // Check if putting own king in check
                if (simulateMoveAndCheck(from, to, currentPlayer)) return -8;
                // If all checks pass, it's a valid capture move
                return 1;
            }
        } else {
            // Check if the move is valid for the piece
            if (!pieceToMove.possibleMovements(from).contains(to)) return -7;
            // If the piece cannot jump, check for pieces in the path
            if (!pieceToMove.getCanJump()) {
                if (!checkPiecesInPath(from, to)) return -6;
            }
            // Check if it's promotion move
            if (pieceToMove.getName() == PieceEnum.PAWN) {
                // Check if the pawn reaches the last row
                if ((pieceToMove.getColor() == ColorEnum.WHITE && to.getRow() == 0) ||
                    (pieceToMove.getColor() == ColorEnum.BLACK && to.getRow() == 7)) return 3;
            }
            // Check if putting own king in check
            if (simulateMoveAndCheck(from, to, currentPlayer)) return -8;            
            // If all checks pass, it's a valid normal move
            return 0;
        }
    }

    public boolean simulateMoveAndCheck(Position from, Position to, ColorEnum currentPlayer) {
        Piece movingPiece = board.getBoard()[from.getRow()][from.getColumn()];
        Piece targetPiece = board.getBoard()[to.getRow()][to.getColumn()];
        // Simulate the move
        board.getBoard()[to.getRow()][to.getColumn()] = movingPiece;
        board.getBoard()[from.getRow()][from.getColumn()] = null;
        // Check if the king is in check after the move
        boolean isInCheck = isKingInCheck(currentPlayer);
        // Revert the move
        board.getBoard()[from.getRow()][from.getColumn()] = movingPiece;
        board.getBoard()[to.getRow()][to.getColumn()] = targetPiece;
        return isInCheck;
    }

    public boolean checkPiecesInPath(Position from, Position to) {
        // Get the deltas of row and column movement
        int deltaRow = to.getRow() - from.getRow();
        int deltaColumn = to.getColumn() - from.getColumn();
        // Get the direction of row and column movement
        int stepRow = Integer.signum(deltaRow);
        int stepColumn = Integer.signum(deltaColumn);
        // Iterate through the path from 'from' to 'to', excluding both endpoints
        for (int row = from.getRow() + stepRow, column = from.getColumn() + stepColumn;
                row != to.getRow() || column != to.getColumn();
                row += stepRow, column += stepColumn) {
            // Check if there is a piece in the current position
            if (board.getBoard()[row][column] != null) return false;
        }
        // If the loop completes, there are no pieces in the path
        return true;
    }
    
    public boolean isKingInCheck(ColorEnum kingColor) {
        Piece piece;
        ArrayList<Position> captureMoves;
        // Get the king's position
        Position kingPosition = findKingPosition(kingColor);
        // Iterate through rows and columns to find opponent pieces
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                // Get the piece at the current position (or empty space too)
                piece = board.getBoard()[row][column];
                // Check if it's an opponent piece
                if (piece != null && piece.getColor() != kingColor) {
                    // Store the capture movements of the piece
                    captureMoves = piece.possibleCaptures(new Position(row, column));
                    // Check if this piece can move to the king's position
                    if (captureMoves.contains(kingPosition)) {
                        // If the piece can jump, no need to check path
                        if (piece.getCanJump()) return true;
                        // Check if there are pieces in the path to the king
                        if (checkPiecesInPath(new Position(row, column), kingPosition)) return true;
                    }
                }
            }
        }
        return false;
    }

    public Position findKingPosition(ColorEnum kingColor) {
        Piece piece;
        // Iterate through rows and columns to find the king piece
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                // Get the piece at the current position
                piece = board.getBoard()[row][column];
                // Check if it's the king of the specified color
                if (piece != null && piece.getName() == PieceEnum.KING && piece.getColor() == kingColor) {
                    return new Position(row, column);
                }
            }
        }
        return null; // Should never happen if the king is on the board
    }

    public boolean isCheckmate() {
        return false; 
    }

    /*
     * GETTERS AND SETTERS
     */

    public Board getBoard() { return board; }
}
