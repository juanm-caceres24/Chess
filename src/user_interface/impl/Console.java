package src.user_interface.impl;

import src.Game;
import src.model.Board;
import src.user_interface.UserInterface;

public class Console extends UserInterface {

    public Console(Game game) {
        super(game);
    }

    /*
     * IMPLEMENTED METHODS
     */

    @Override
    public void showBoard() {
        Board board = game.getBoard();
        clearConsole();
        System.out.printf("||===||===========|===========|===========|===========|===========|===========|===========|===========||\n");
        for (int row = 0; row < 8; row++) {
            if (row > 0) {
                System.out.printf("||---||-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------||\n");
            }
            System.out.printf("||   ||");
            for (int i = 0; i < 8; i++) {
                if (board.getBoard()[row][i] != null) {
                    System.out.printf(" %9s |", board.getBoard()[row][i].getName());
                } else {
                    System.out.printf("           |");
                }
            }
            System.out.printf("|\n");
            System.out.printf("|| %d ||", 8 - row);
            for (int i = 0; i < 8; i++) {
                if (board.getBoard()[row][i] != null) {
                    System.out.printf(" %9s |", board.getBoard()[row][i].getColor());
                } else {
                    System.out.printf("           |");
                }
            }
            System.out.printf("|\n");
            System.out.printf("||   ||           |           |           |           |           |           |           |           ||\n");
        }
        System.out.printf("||===||===========|===========|===========|===========|===========|===========|===========|===========||\n");
        System.out.printf("||   ||     A     |     B     |     C     |     D     |     E     |     F     |     G     |     H     ||\n");
        System.out.printf("||===||===========|===========|===========|===========|===========|===========|===========|===========||\n");
    }

    /*
     * OWN METHODS
     */

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
