import java.util.ArrayList;
import java.util.List;

public class UIController {
    private ArrayList<String> menuOptions = new ArrayList<>();

    public UIController() {
        menuOptions.add("Type '2 players' or 'vs. computer' to play .");
        menuOptions.add("Type 'history' to view your game history.");
        menuOptions.add("Type 'state' to view your game state.");
        menuOptions.add("Type 'quit' to stop playing.");
    }
    public void displayMenu() {
        System.out.println("=== MAIN MENU ===");
        menuOptions.forEach(option -> System.out.println(option));
    }

    public void displayShapeOptions() {
        System.out.println("Type 'rock', 'paper', or 'scissors' to play."
        );
        System.out.println("Type 'quit' to go back to the main menu.");
    }
    public void displayWelcome() {
        System.out.println("Welcome to Rock, Paper, Scissors!\n");
    }

    public void displayGameOver(int gameState, String gameMode, Player player1, Player player2) {
        System.out.format("%s picks: %s \n", player1.getName(), player1.getSelectedShape());
        System.out.format("%s picks: %s \n", player2.getName(), player2.getSelectedShape());
        if (gameMode.equals("vs. computer")) {
            System.out.println(gameState == 0 ? "You Won!" : gameState == 1 ? "You Lose!" : "Tie Game");
        } else {
            System.out.println(gameState == 0 ? player1.getName() + " Won!" : gameState == 1 ? player2.getName() + " Won!" : "Tie Game!");
        }
    }

    public void displayPlayerForm() {
        System.out.println("Please enter your name:");
    }

    public void displayHistory(List<String> history) {
        System.out.println("=== GAME HISTORY ===");
        history.forEach(h -> System.out.println(h));
    }

    public void displayInvalidInput() {
        System.out.println("!<< Please enter valid input >>");
    }

    public void displayPlayerState(Player player1, Player player2) {
        System.out.format("=== %s State ===\n", player1.getName().toUpperCase());
        System.out.println(player1);
        System.out.format("=== %s State ===\n", player2.getName().toUpperCase());
        System.out.println(player2);
    }
}
