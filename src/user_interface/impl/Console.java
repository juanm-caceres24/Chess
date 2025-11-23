package src.user_interface.impl;

import src.user_interface.UserInterface;

public class Console extends UserInterface {

    public Console() { }

    @Override
    public void showBoard() {
        clearConsole();
        // Implementation to display the board in the console
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
