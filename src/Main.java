package src;

import src.user_interface.UserInterface;
import src.user_interface.impl.Console;
import src.utils.Position;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        UserInterface userInterface = new Console(game);
        game.initializePieces();
        userInterface.showBoard();
        userInterface.showData();
        Position[] positions;
        // Game loop
        while (!game.getGameService().isCheckmate()) {
            // Get move from user
            positions = userInterface.requestMove();
            // Check the format of the input
            if (positions == null) {
                userInterface.showError(0);
                continue;
            }
            // Attempt to move piece
            int moveResult = game.movePiece(positions[0], positions[1]);
            // Only switch player and record board display if the move was successful
            if (moveResult >= 0) {
                userInterface.showBoard();
                userInterface.showData();
                game.switchPlayer();
            } else {
                // Show error message for invalid move
                userInterface.showError(moveResult);
            }
        }
    }
}
