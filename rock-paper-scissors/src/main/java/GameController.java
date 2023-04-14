import java.util.*;

public class GameController {
    final private UIController uiController = new UIController();
    final private GameState gameState = new GameState(new HumanPlayer("Player1"), new ComputerPlayer("Computer"));
    private boolean shouldGoBackMenu;
    private enum Option {
        Players("2 players"),
        Computer("vs. computer"),
        HISTORY("history"),
        STATE("state"),
        QUIT("quit");
        private final String option;

        Option(String option) {
            this.option = option;
        }

        public String getOption() {
            return option;
        }
    }

    public GameController() {
        uiController.setGameState(gameState);
    }

    public void gameLoop(boolean isGaming) {
        if (!isGaming) menuOptionHandler();
        if (shouldGoBackMenu) goBackMenuHandler();
        else {
            if (gameState.getGameMode().equals(Option.Players.getOption())) {
                pickShapeHandler();
                if (!shouldGoBackMenu) pickShapeHandler();
            } else {
                pickShapeHandler();
            }
            if (shouldGoBackMenu) goBackMenuHandler();
            else {
                checkGameOverHandler();
                gameLoop(true);
            }
        }
    }

    private void goBackMenuHandler() {
        if (shouldGoBackMenu) {
            shouldGoBackMenu = false;
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
            shouldGoBackMenu = true;
            return;
        }
        if (gameMode.equals(Option.Computer.getOption())) {
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
        uiController.displayMenu();
        String option = getUserInput();
        if (option.equals(Option.Players.getOption())) {
            gameState.setPlayer2(new HumanPlayer("Player2"));
            gameState.setGameMode(Option.Players.getOption());
        } else if (option.equals(Option.Computer.getOption())) {
            gameState.setPlayer2(new ComputerPlayer("Computer"));
            gameState.setGameMode(Option.Computer.getOption());
        } else if (option.equals((Option.HISTORY.getOption()))) {
            uiController.displayHistory();
            shouldGoBackMenu = true;
        } else if (option.equals(Option.STATE.getOption())) {
            uiController.displayPlayerState();
            shouldGoBackMenu = true;
        } else if (option.equals((Option.QUIT.getOption()))) {
            System.out.println("Game Terminated");
            System.exit(0);
        } else {
            uiController.displayInvalidInput();
            menuOptionHandler();
        }
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
