import java.util.*;

/**
 * The GameController class is responsible for controlling the game flow of Rock-Paper-Scissors game,
 * using the game state, game UI, and menu options. It has methods for handling player input, comparing the shapes,
 * and updating the game state.
 * It uses the GameState class to store the current game state, the UIController class for user interface,
 * and the MenuOptions class for menu options.
 */
public class GameController {
    final private Game gameState = new GameState(new HumanPlayer("Player1"), new ComputerPlayer("Computer"));
    final private GameUI uiController = new UIController(gameState);
    final private Menu menuOptions = new MenuOptions(gameState, uiController);

    public GameController() {
        uiController.displayWelcome();
    }


    /**
     * The main game loop for the Rock-Paper-Scissors game. Handles player input, updates game state,
     * and displays game over message. Recursively called until the game is over.
     *
     * @param isGaming a boolean indicating whether the players are still selecting shape
     */
    public void gameLoop(boolean isGaming) {
        if (!isGaming) menuOptionsHandler();
        if (menuOptions.getShouldGoBackMenu()) goBackMenuHandler();
        else {
            if (gameState.isPlayerMode()) {
                pickShapeHandler();
                if (!menuOptions.getShouldGoBackMenu()) pickShapeHandler();
            } else {
                pickShapeHandler();
            }
            if (menuOptions.getShouldGoBackMenu()) goBackMenuHandler();
            else {
                checkGameOverHandler();
                gameLoop(true);
            }
        }
    }

    private void goBackMenuHandler() {
        if (menuOptions.getShouldGoBackMenu()) {
            menuOptions.setShouldGoBackMenu(false);
            gameLoop(false);
        }
    }

    /**
     * Checks if the game is over by comparing the selected shapes of the two players.
     * Updates the game state with the winner, displays game over message, and adds the game history to the list.
     */
    private void checkGameOverHandler() {
        gameState.setPlayer1Win(compareShape(gameState.getPlayer1SelectedShape(), gameState.getPlayer2SelectedShape()));
        gameState.setPlayer2Win(compareShape(gameState.getPlayer2SelectedShape(), gameState.getPlayer1SelectedShape()));
        gameState.updatePlayerState();
        gameState.setGameState(gameState.isPlayer1Win() ? 0 : gameState.isPlayer2Win() ? 1 : 2);
        uiController.displayGameOver();
        gameState.addHistory();
        gameState.reset();
    }

    /**
     * Handles selecting a shape for the player. Displays the shape options, validates user input,
     * and updates the game state with the selected shape.
     */
    private void pickShapeHandler() {
        uiController.displayShapeOptions();
        Optional<String> validUserInput = validateShapeOptions(getUserInput());
        if (validUserInput.isPresent()) {
            if (validUserInput.get().equals("quit")) {
                menuOptions.setShouldGoBackMenu(true);
                return;
            }
            gameState.updateGameState(validUserInput.get());
        } else {
            uiController.displayInvalidInput();
            pickShapeHandler();
        }
    }

    /**
     * Handles the menu options. Displays the menu options, validates user input, and performs the selected action.
     */
    private void menuOptionsHandler() {
        menuOptions.showOption();
        try {
            menuOptions.handleOption(getUserInput());
        } catch (IllegalArgumentException e) {
            uiController.displayInvalidInput();
            menuOptionsHandler();
        }
    }

    private String getUserInput() {
        return new Scanner(System.in).nextLine().toLowerCase();
    }

    /**
     * Validates if the given user input is a valid shape option.
     *
     * @param shape a string representing the user input
     * @return an Optional containing the shape if it is valid, or an empty Optional otherwise
     */
    private Optional<String> validateShapeOptions(String shape) {
        List<String> validShapes = Arrays.asList("rock", "paper", "scissors", "quit");
        if (validShapes.contains(shape)) {
            return Optional.of(shape);
        }
        return Optional.empty();
    }

    /**
     * Compares two given shapes to determine if the first shape wins over the second shape based on the rules of Rock-Paper-Scissors game.
     *
     * @param shapeA a string representing the first shape
     * @param shapeB a string representing the second shape
     * @return true if the first shape wins over the second shape, else false
     */
    private boolean compareShape(String shapeA, String shapeB) {
        if (shapeA.equals("rock") && shapeB.equals("scissors")) return true;
        if (shapeA.equals("paper") && shapeB.equals("rock")) return true;
        return shapeA.equals("scissors") && shapeB.equals("paper");
    }
}
