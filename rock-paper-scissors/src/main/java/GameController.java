import java.util.*;
import java.util.stream.Collectors;

public class GameController {
    private UIController uiController;
    private String gameMode;
    private int gameState = -1;
    private boolean isPlayer1Win;
    private boolean isPlayer2Win;

    private Player player1;
    private Player player2;
    private String player1SelectedShape;
    private String player2SelectedShape;
    private boolean shouldGoBackMenu;
    private ArrayList<String> history = new ArrayList<>();

    private enum Option {
        Players("2 players"),
        Computer("computer"),
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

    public GameController(UIController uiController) {
        this.uiController = uiController;
    }

    public void gameLoop(boolean isGaming) {
        if(!isGaming) menuOptionHandler();
        if (shouldGoBackMenu) goBackMenuHandler();
        else {
            pickShapeHandler();
            if (shouldGoBackMenu) goBackMenuHandler();
            else {
                checkGameOverHandler();
                gameLoop(true);
            };
        }

    }

    public void createPlayers() {
        uiController.displayPlayerForm();
        String name = getUserInput();
        player1 = new HumanPlayer(name);
        player2 = new ComputerPlayer();
    }

    private void goBackMenuHandler() {
        if (shouldGoBackMenu) {
            shouldGoBackMenu = false;
            gameLoop(false);
        }
    }

    private void checkGameOverHandler() {
        isPlayer1Win = compareShape(player1SelectedShape, player2SelectedShape);
        isPlayer2Win = compareShape(player2SelectedShape, player1SelectedShape);
        if (isPlayer1Win) {
            gameState = 0;
            uiController.displayGameOver(gameState, player1SelectedShape, player2SelectedShape);
        } else if (isPlayer2Win) {
            gameState = 1;
            uiController.displayGameOver(gameState, player1SelectedShape, player2SelectedShape);
        } else {
            gameState = 2;
            uiController.displayGameOver(gameState, player1SelectedShape, player2SelectedShape);
        }

        updatePlayerState();
        updateHistory();
        resetGameState();
    }

    private void resetGameState() {
        isPlayer1Win = false;
        isPlayer2Win = false;
        gameState = -1;
    }
    private void updateHistory() {
        String selectedShapeByTwoPlayers = String.format("Player picked %s, computer picked %s", player1.getSelectedShape(), player2.getSelectedShape());
        history.add((player1.isWinning() ? "WIN: " : "LOSE: ") + selectedShapeByTwoPlayers);
    }

    private void pickShapeHandler() {
        uiController.displayShapeOptions();
        String userInput = getUserInput();
        if (userInput.equals("quit")) {
            shouldGoBackMenu = true;
            return;
        }
        if (gameMode.equals("computer")) {
            boolean isValidShape = validateShape(userInput);
            if (isValidShape) {
                player2SelectedShape = selectRandomShape();
                player1SelectedShape = userInput;
            } else {
                uiController.displayInvalidInput();
                pickShapeHandler();
            }
        }
    }
    private void updatePlayerState() {
        player1.setWinning(isPlayer1Win);
        player2.setWinning(isPlayer2Win);
        player1.setSelectedShape(player1SelectedShape);
        player2.setSelectedShape(player2SelectedShape);
        if (isPlayer1Win) {
            player1.setWinningPoints(player1.getWinningPoints()  + 1);
            player2.setLosingPoints(player2.getLosingPoints() + 1);
        } else if (isPlayer2Win) {
            player2.setWinningPoints(player2.getWinningPoints()  + 1);
            player1.setLosingPoints(player1.getLosingPoints() + 1);
        } else {
            player1.setTiePoints(player1.getTiePoints() + 1);
            player2.setTiePoints(player2.getTiePoints() + 1);
        }
    }

    private void menuOptionHandler() {
        uiController.displayMenu();
        String option = getUserInput();
        if (option.equals(Option.Players.getOption())) {
            gameMode = "players";
            System.out.println("two players");
        } else if (option.equals(Option.Computer.getOption())) {
            gameMode = "computer";
        } else if (option.equals((Option.HISTORY.getOption()))) {
            uiController.displayHistory(history);
            shouldGoBackMenu = true;
        }else if (option.equals(Option.STATE.getOption())) {
            uiController.displayPlayerState(player1);
            shouldGoBackMenu = true;
        } else if (option.equals((Option.QUIT.getOption()))) {
            uiController.displayHistory(history);
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


    private String selectRandomShape() {
        ArrayList<String> validShapes = new ArrayList<>(Arrays.asList("rock",
                "paper",
                "scissors"));
        Random random = new Random();
        int randomIndex = random.nextInt(validShapes.size());
        String randomShape = validShapes.get(randomIndex);
        return randomShape;
    }

    private boolean compareShape(String shapeA, String shapeB) {
        if (shapeA.equals("rock") && shapeB.equals("scissors")) return true;
        if (shapeA.equals("paper") && shapeB.equals("rock")) return true;
        if (shapeA.equals("scissors") && shapeB.equals("paper")) return true;
        return false;
    }

    public void history() {
    }

    public void quit() {
    }
}
