package src;

import java.util.ArrayList;

import src.model.Board;
import src.utils.Movement;
import src.utils.Position;
import src.utils.enums.ColorEnum;
import src.utils.enums.PieceEnum;
import src.model.pieces.Piece;
import src.model.pieces.impl.Bishop;
import src.model.pieces.impl.King;
import src.model.pieces.impl.Knight;
import src.model.pieces.impl.Pawn;
import src.model.pieces.impl.Queen;
import src.model.pieces.impl.Rook;

public class Game {

    private Board board;
    private GameService gameService;
    private ColorEnum currentPlayer;
    private ArrayList<Piece> whiteCapturedPieces;
    private ArrayList<Piece> blackCapturedPieces;
    private ArrayList<Movement> moveHistory;

    public Game() {
        board = new Board();
        gameService = new GameService(board);
        currentPlayer = ColorEnum.WHITE;
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();
        moveHistory = new ArrayList<>();
    }

    public void initializePieces() {
        for (int i = 0; i < 8; i++) {
            // Pawns
            board.getBoard()[1][i] = new Pawn(ColorEnum.BLACK);
            board.getBoard()[6][i] = new Pawn(ColorEnum.WHITE);
            switch (i) {
                case 0, 7:
                    // Rooks
                    board.getBoard()[0][i] = new Rook(ColorEnum.BLACK);
                    board.getBoard()[7][i] = new Rook(ColorEnum.WHITE);
                    break;
                case 1, 6:
                    // Knights
                    board.getBoard()[0][i] = new Knight(ColorEnum.BLACK);
                    board.getBoard()[7][i] = new Knight(ColorEnum.WHITE);
                    break;
                case 2, 5:
                    // Bishops
                    board.getBoard()[0][i] = new Bishop(ColorEnum.BLACK);
                    board.getBoard()[7][i] = new Bishop(ColorEnum.WHITE);
                    break;
                case 3:
                    // Queens
                    board.getBoard()[0][i] = new Queen(ColorEnum.BLACK);
                    board.getBoard()[7][i] = new Queen(ColorEnum.WHITE);
                    break;
                case 4:
                    // Kings
                    board.getBoard()[0][i] = new King(ColorEnum.BLACK);
                    board.getBoard()[7][i] = new King(ColorEnum.WHITE);
                    break;
            }
        }
    }

    public boolean movePiece(Position from, Position to) {
        int code = gameService.checkMove(from, to, currentPlayer);
        // Negative codes indicate invalid moves/errors
        if (code < 0) return false;
        switch (code) {
            case 0: { // Normal move
                // Move piece
                board.getBoard()[to.getRow()][to.getColumn()] = board.getBoard()[from.getRow()][from.getColumn()];
                board.getBoard()[from.getRow()][from.getColumn()] = null;
                // Mark te piece as moved
                boolean oldWasMoved = board.getBoard()[to.getRow()][to.getColumn()].getWasMoved();
                if (!oldWasMoved) {
                    board.getBoard()[to.getRow()][to.getColumn()].setWasMoved(true);
                }
                // Record movement
                moveHistory.add(new Movement(from, to, oldWasMoved));
                return true;
            }
            case 1: { // Capture move
                // Record captured piece
                Piece capturedPiece = board.getBoard()[to.getRow()][to.getColumn()];
                if (capturedPiece.getColor() == ColorEnum.WHITE) {
                    whiteCapturedPieces.add(capturedPiece);
                } else {
                    blackCapturedPieces.add(capturedPiece);
                }
                // Move piece (capture handled by overwrite)
                board.getBoard()[to.getRow()][to.getColumn()] = board.getBoard()[from.getRow()][from.getColumn()];
                board.getBoard()[from.getRow()][from.getColumn()] = null;
                // Mark te piece as moved
                boolean oldWasMoved = board.getBoard()[to.getRow()][to.getColumn()].getWasMoved();
                if (!oldWasMoved) {
                    board.getBoard()[to.getRow()][to.getColumn()].setWasMoved(true);
                }
                // Record movement
                moveHistory.add(new Movement(from, to, oldWasMoved));
                return true;
            }
            case 2: { // Castling
                // 'from' should be king, 'to' should be rook, so swap if not
                if (board.getBoard()[from.getRow()][from.getColumn()].getName() != PieceEnum.KING ||
                    board.getBoard()[to.getRow()][to.getColumn()].getName() != PieceEnum.ROOK) {
                    Position tmp = from;
                    from = to;
                    to = tmp;
                }
                // Get the direction
                int step = Integer.signum(to.getColumn() - from.getColumn());
                int kingFinalColumn = from.getColumn() + 2 * step;
                int rookFinalColumn = from.getColumn() + step;
                // Move king
                board.getBoard()[from.getRow()][kingFinalColumn] = board.getBoard()[from.getRow()][from.getColumn()];
                board.getBoard()[from.getRow()][from.getColumn()] = null;
                // Move rook
                board.getBoard()[from.getRow()][rookFinalColumn] = board.getBoard()[to.getRow()][to.getColumn()];
                board.getBoard()[to.getRow()][to.getColumn()] = null;
                // Mark both moved
                board.getBoard()[from.getRow()][kingFinalColumn].setWasMoved(true);
                board.getBoard()[from.getRow()][rookFinalColumn].setWasMoved(true);
                // Record movement
                moveHistory.add(new Movement(from, to, false));
                return true;
            }
            case 3: { // Promotion
                // Replace pawn with a queen by default
                Piece pawn = board.getBoard()[from.getRow()][from.getColumn()];
                if (pawn == null || pawn.getName() != PieceEnum.PAWN) return false;
                ColorEnum color = pawn.getColor();
                // Create new queen of same color
                board.getBoard()[to.getRow()][to.getColumn()] = new Queen(color);
                board.getBoard()[from.getRow()][from.getColumn()] = null;
                // Mark the new queen as moved
                board.getBoard()[to.getRow()][to.getColumn()].setWasMoved(true);
                return true;
            }
            default:
                return false;
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == ColorEnum.WHITE) ? ColorEnum.BLACK : ColorEnum.WHITE;
    }

    /*
     * GETTERS AND SETTERS
     */

    public Board getBoard() { return board; }
    public GameService getGameService() { return gameService; }
    public ColorEnum getCurrentPlayer() { return currentPlayer; }
    public ArrayList<Piece> getWhiteCapturedPieces() { return whiteCapturedPieces; }
    public ArrayList<Piece> getBlackCapturedPieces() { return blackCapturedPieces; }
    public ArrayList<Movement> getMoveHistory() { return moveHistory; }
}
