import java.util.ArrayList;
public class UIController {
    final private ArrayList<String> menuOptions = new ArrayList<>();
    private GameState gameState;
    public UIController() {
        menuOptions.add("Type '2 players' or 'vs. computer' to play .");
        menuOptions.add("Type 'history' to view your game history.");
        menuOptions.add("Type 'state' to view your game state.");
        menuOptions.add("Type 'quit' to stop playing.");
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void displayMenu() {
        System.out.println("=== MAIN MENU ===");
        menuOptions.forEach(System.out::println);
    }

    public void displayShapeOptions() {
        System.out.println("Type 'rock', 'paper', or 'scissors' to play."
        );
        System.out.println("Type 'quit' to go back to the main menu.");
    }
    public void displayWelcome() {
        System.out.println("Welcome to Rock, Paper, Scissors!\n");
    }

    public void displayGameOver() {
        Player player1 = gameState.getPlayer1();
        Player player2 = gameState.getPlayer2();
        int state = gameState.getGameState();
        System.out.format("%s picks: %s \n", player1.getName(), player1.getSelectedShape());
        System.out.format("%s picks: %s \n", player2.getName(), player2.getSelectedShape());
        if (gameState.getGameMode().equals("vs. computer")) {
            System.out.println(state == 0 ? "You Won!" : state == 1 ? "You Lose!" : "Tie Game");
        } else {
            System.out.println(state == 0 ? player1.getName() + " Won!" : state == 1 ? player2.getName() + " Won!" : "Tie Game!");
        }
    }
    public void displayHistory() {
        System.out.println("=== GAME HISTORY ===");
        gameState.getHistory().forEach(System.out::println);
    }

    public void displayInvalidInput() {
        System.out.println("!<< Please enter valid input >>");
    }

    public void displayPlayerState() {
        Player player1 = gameState.getPlayer1();
        Player player2 = gameState.getPlayer2();
        System.out.format("=== %s State ===\n", player1.getName().toUpperCase());
        System.out.println(player1);
        System.out.format("=== %s State ===\n", player2.getName().toUpperCase());
        System.out.println(player2);
    }
}
