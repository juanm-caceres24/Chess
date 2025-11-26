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
        // Game loop
        while (!game.getGameService().isCheckmate()) {
            Position[] positions = userInterface.requestMove();
            // Only switch player and record board display if the move was successful
            if (game.movePiece(positions[0], positions[1])) {
                userInterface.showBoard();
                userInterface.showData();
                game.switchPlayer();
            } else {
                userInterface.showError(2);
            }
        }
    }
}
