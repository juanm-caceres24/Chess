package src.user_interface;

import src.Game;
import src.utils.Position;

public abstract class UserInterface {

    protected Game game;

    public UserInterface(Game game) {
        this.game = game;
    }

    public abstract void showBoard();
    public abstract void showData();
    public abstract Position[] requestMove();
    public abstract void showError(int errorCode);
}
