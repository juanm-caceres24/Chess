package src.user_interface.impl;

import java.util.Scanner;

import src.Game;
import src.model.Board;
import src.model.pieces.Piece;
import src.user_interface.UserInterface;
import src.utils.Movement;
import src.utils.Position;

public class Console extends UserInterface {

    public Scanner scanner;

    public Console(Game game) {
        super(game);
        this.scanner = new Scanner(System.in);
    }

    /*
     * IMPLEMENTED METHODS
     */

    @Override
    public void showBoard() {
        Board board = game.getBoard();
        clearConsole();
        System.out.printf("||===||===========|===========|===========|===========|===========|===========|===========|===========||===||\n");
        System.out.printf("||   ||     A     |     B     |     C     |     D     |     E     |     F     |     G     |     H     ||   ||\n");
        System.out.printf("||===||===========|===========|===========|===========|===========|===========|===========|===========||===||\n");
        for (int row = 0; row < 8; row++) {
            if (row > 0) {
                System.out.printf("||---||-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------||---||\n");
            }
            System.out.printf("||   ||");
            for (int i = 0; i < 8; i++) {
                if (board.getBoard()[row][i] != null) {
                    System.out.printf(" %9s |", board.getBoard()[row][i].getName());
                } else {
                    System.out.printf("           |");
                }
            }
            System.out.printf("|   ||\n");
            System.out.printf("|| %d ||", 8 - row);
            for (int i = 0; i < 8; i++) {
                if (board.getBoard()[row][i] != null) {
                    System.out.printf(" %9s |", board.getBoard()[row][i].getColor());
                } else {
                    System.out.printf("           |");
                }
            }
            System.out.printf("| %d ||\n", 8 - row);
            System.out.printf("||   ||           |           |           |           |           |           |           |           ||   ||\n");
        }
        System.out.printf("||===||===========|===========|===========|===========|===========|===========|===========|===========||===||\n");
        System.out.printf("||   ||     A     |     B     |     C     |     D     |     E     |     F     |     G     |     H     ||   ||\n");
        System.out.printf("||===||===========|===========|===========|===========|===========|===========|===========|===========||===||\n");
    }

    @Override
    public void showData() {
        System.out.printf("Current Player: %s\n", game.getCurrentPlayer());
        System.out.printf("White Captured Pieces: ");
        if (game.getWhiteCapturedPieces().isEmpty()) {
            System.out.printf("(None)");
        } else {
            for (Piece piece : game.getWhiteCapturedPieces()) {
                System.out.printf("%s ", piece.getName());
            }
        }
        System.out.printf("\n");
        System.out.printf("Black Captured Pieces: ");
        if (game.getBlackCapturedPieces().isEmpty()) {
            System.out.printf("(None)");
        } else {
            for (Piece piece : game.getBlackCapturedPieces()) {
                System.out.printf("%s ", piece.getName());
            }
        }
        System.out.printf("\n");
        System.out.printf("Move History: ");
        if (game.getMoveHistory().isEmpty()) {
            System.out.printf("(None)");
        } else {
            for (Movement movement : game.getMoveHistory()) {
                System.out.printf("[(%d,%d) (%d,%d)] ",
                        movement.getFrom().getRow(), movement.getFrom().getColumn(),
                        movement.getTo().getRow(), movement.getTo().getColumn());
            }
        }
        System.out.printf("\n");
    }

    @Override
    public Position[] requestMove() {
        Position[] movement = new Position[2];
        System.out.printf("Turn of %s. Requesting move: ", game.getCurrentPlayer());
        String input = scanner.nextLine();
        // Check format
        if (input.length() != 5 || input.charAt(2) != ' ') return null;
        String[] splitedInput = input.split(" ");
        movement[0] = new Position(parseInput(splitedInput[0])[0], parseInput(splitedInput[0])[1]);
        movement[1] = new Position(parseInput(splitedInput[1])[0], parseInput(splitedInput[1])[1]);
        return movement;
    }

    @Override
    public void showError(int errorCode) {
        switch (errorCode) {
            case -11 -> { System.out.printf("ERROR: Cannot castle while in check.\n"); }
            case -10 -> { System.out.printf("ERROR: Cannot castle through check.\n"); }
            case -9 -> { System.out.printf("ERROR: Move would put or leave king in check.\n"); }
            case -8 -> { System.out.printf("ERROR: Invalid move for piece.\n"); }
            case -7 -> { System.out.printf("ERROR: Pieces in the path of movement.\n"); }
            case -6 -> { System.out.printf("ERROR: Piece has already moved (castling).\n"); }
            case -5 -> { System.out.printf("ERROR: Cannot capture own piece.\n"); }
            case -4 -> { System.out.printf("ERROR: Not player piece to move.\n"); }
            case -3 -> { System.out.printf("ERROR: No piece at initial position.\n"); }
            case -2 -> { System.out.printf("ERROR: Position out of board bounds.\n"); }
            case -1 -> { System.out.printf("ERROR: Undefined error.\n"); }
            case 0 -> { System.out.printf("ERROR: Input format incorrect.\n"); }
            default -> { System.out.printf("ERROR: Unknown error (code %d).\n", errorCode); }
        }
    }

    /*
     * OWN METHODS
     */

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public int[] parseInput(String input) {
        int[] movementCoords = new int[2];
        switch (input.charAt(0)) {
            case 'A', 'a' -> { movementCoords[1] = 0; }
            case 'B', 'b' -> { movementCoords[1] = 1; }
            case 'C', 'c' -> { movementCoords[1] = 2; }
            case 'D', 'd' -> { movementCoords[1] = 3; }
            case 'E', 'e' -> { movementCoords[1] = 4; }
            case 'F', 'f' -> { movementCoords[1] = 5; }
            case 'G', 'g' -> { movementCoords[1] = 6; }
            case 'H', 'h' -> { movementCoords[1] = 7; }
            default -> { movementCoords[1] = -1; }
        }
        switch (input.charAt(1)) {
            case '1' -> { movementCoords[0] = 7; }
            case '2' -> { movementCoords[0] = 6; }
            case '3' -> { movementCoords[0] = 5; }
            case '4' -> { movementCoords[0] = 4; }
            case '5' -> { movementCoords[0] = 3; }
            case '6' -> { movementCoords[0] = 2; }
            case '7' -> { movementCoords[0] = 1; }
            case '8' -> { movementCoords[0] = 0; }
            default -> { movementCoords[0] = -1; }
        }
        return movementCoords;
    }
}
