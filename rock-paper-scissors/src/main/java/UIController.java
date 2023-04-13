import java.util.ArrayList;

public class UIController {
    private ArrayList<String> menuOptions = new ArrayList<>();

    public UIController() {
        menuOptions.add("Type '2 players' or 'computer' to play .");
        menuOptions.add("Type 'history' to view your game history.");
        menuOptions.add("Type 'quit' to stop playing.");
    }
    public void displayMenu() {
        System.out.println("MAIN MENU");
        System.out.println("=====");
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
}
