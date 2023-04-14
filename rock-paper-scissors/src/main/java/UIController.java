import java.util.ArrayList;
import java.util.List;

public class UIController {
    private ArrayList<String> menuOptions = new ArrayList<>();

    public UIController() {
        menuOptions.add("Type '2 players' or 'computer' to play .");
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

    public void displayGameOver(int state, String player1Shape, String player2Shape) {
        System.out.println("Computer picks: " + player2Shape);
        System.out.println("You picks: " + player1Shape);
        System.out.println(state == 0 ? "You Won!" : state == 1 ? "You Lose!" : "Tie Game");
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

    public void displayPlayerState(Player player) {
        System.out.println("=== Player State ===");
        System.out.println(player);
    }
}
