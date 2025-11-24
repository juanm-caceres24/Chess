package src;

import src.user_interface.UserInterface;
import src.user_interface.impl.Console;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        UserInterface userInterface = new Console(game);
        game.initializePieces();
        userInterface.showBoard();
    }
}
