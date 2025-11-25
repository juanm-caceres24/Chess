package src;

import src.model.Board;
import src.utils.Position;
import src.utils.enums.ColorEnum;
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

    public Game() {
        board = new Board();
        gameService = new GameService(board);
        currentPlayer = ColorEnum.WHITE;
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
        switch (gameService.checkMove(from, to, currentPlayer)) {
            
        }
        return false;
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
}
