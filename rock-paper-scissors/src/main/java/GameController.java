import java.util.*;

public class GameController {
    final private GameState gameState = new GameState(new HumanPlayer("Player1"), new ComputerPlayer("Computer"));
    final private UIController uiController = new UIController(gameState);
    final private MenuOptions menuOptions = new MenuOptions(gameState, uiController);

    public GameController() {
        uiController.displayWelcome();
    }

    public void gameLoop(boolean isGaming) {
        if (!isGaming) menuOptionHandler();
        if (menuOptions.getShouldGoBackMenu()) goBackMenuHandler();
        else {
            if (gameState.getGameMode().equals(menuOptions.getPlayersOption())) {
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
        if (gameState.isPlayer1Win()) {
            gameState.setGameState(0);
            uiController.displayGameOver();
        } else if (gameState.isPlayer2Win()) {
            gameState.setGameState(1);
            uiController.displayGameOver();
        } else {
            gameState.setGameState(2);
            uiController.displayGameOver();
        }
        gameState.addHistory();
        gameState.reset();
    }

    private void pickShapeHandler() {
        uiController.displayShapeOptions();
        String userInput = getUserInput();
        String gameMode = gameState.getGameMode();
        if (userInput.equals("quit")) {
            menuOptions.setShouldGoBackMenu(true);
            return;
        }
        if (gameMode.equals(menuOptions.getComputerOption())) {
            boolean isValidShape = validateShape(userInput);
            if (isValidShape) {
                // TODO how to avoid casting
                ComputerPlayer player2 = (ComputerPlayer) gameState.getPlayer2();
                gameState.setPlayer1SelectedShape(userInput);
                gameState.setPlayer2SelectedShape(player2.selectRandomShape());
            } else {
                uiController.displayInvalidInput();
                pickShapeHandler();
            }
        } else {
            if (gameState.getPlayerTurn() == 2) {
                gameState.setPlayerTurn(1);
                gameState.setPlayer2SelectedShape(userInput);
            } else {
                gameState.setPlayerTurn(2);
                gameState.setPlayer1SelectedShape(userInput);
            }
        }
    }

    private void menuOptionHandler() {
        menuOptions.displayMenu();
        menuOptions.handleOption(getUserInput());
    }

    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput.toLowerCase();
    }

    private boolean validateShape(String shape) {
        ArrayList<String> validShapes = new ArrayList<>(Arrays.asList("rock",
                "paper",
                "scissors"));
        return validShapes.contains(shape);
    }

    private boolean compareShape(String shapeA, String shapeB) {
        if (shapeA.equals("rock") && shapeB.equals("scissors")) return true;
        if (shapeA.equals("paper") && shapeB.equals("rock")) return true;
        return shapeA.equals("scissors") && shapeB.equals("paper");
    }
}
