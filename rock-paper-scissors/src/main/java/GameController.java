import java.util.*;

public class GameController {
    final private GameState gameState = new GameState(new HumanPlayer("Player1"), new ComputerPlayer("Computer"));
    final private UIController uiController = new UIController(gameState);
    final private Menu menuOptions = new MenuOptions(gameState, uiController);

    public GameController() {
        uiController.displayWelcome();
    }

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

    private void checkGameOverHandler() {
        gameState.setPlayer1Win(compareShape(gameState.getPlayer1SelectedShape(), gameState.getPlayer2SelectedShape()));
        gameState.setPlayer2Win(compareShape(gameState.getPlayer2SelectedShape(), gameState.getPlayer1SelectedShape()));
        gameState.updatePlayerState();
        gameState.setGameState(gameState.isPlayer1Win() ? 0 : gameState.isPlayer2Win() ? 1 : 2);
        uiController.displayGameOver();
        gameState.addHistory();
        gameState.reset();
    }

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

    private Optional<String> validateShapeOptions(String shape) {
        List<String> validShapes = Arrays.asList("rock", "paper", "scissors", "quit");
        if (validShapes.contains(shape)) {
            return Optional.of(shape);
        }
        return Optional.empty();
    }


    private boolean compareShape(String shapeA, String shapeB) {
        if (shapeA.equals("rock") && shapeB.equals("scissors")) return true;
        if (shapeA.equals("paper") && shapeB.equals("rock")) return true;
        return shapeA.equals("scissors") && shapeB.equals("paper");
    }
}
