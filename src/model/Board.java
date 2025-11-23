package src.model;

import src.model.pieces.Piece;

public class Board {

    private final Piece[][] board = new Piece[8][8];

    private Board() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = null;
    }

    /*
     * GETTERS AND SETTERS
     */

    public Piece[][] getBoard() { return board; }
}
