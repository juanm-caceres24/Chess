package src.user_interface;

import src.Game;

public abstract class UserInterface {

    protected Game game;

    public UserInterface(Game game) {
        this.game = game;
    }

    public abstract void showBoard();
}
