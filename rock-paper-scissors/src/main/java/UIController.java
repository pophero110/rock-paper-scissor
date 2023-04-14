
public class UIController extends GameUI {
    public UIController(Game gameState) {
        super(gameState);
    }

    public void displayShapeOptions() {
        System.out.println("Type 'rock', 'paper', or 'scissors' to play."
        );
        System.out.println("Type 'quit' to go back to the main menu.");
    }
    public void displayWelcome() {
        System.out.println("\nWelcome to Rock, Paper, Scissors!\n");
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
