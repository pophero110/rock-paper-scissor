import java.util.*;
import java.util.stream.Collectors;

public class GameController {
    private UIController uiController;
    private String gameMode;
    private boolean isPlayer1Win;
    private boolean isPlayer2Win;
    private String playerSelectedShape;
    private String computerSelectedShape;
    private boolean shouldGoBackMenu;

    private enum Option {
        Players("2 players"),
        Computer("computer"),
        HISTORY("history"),
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

    public void gameLoop() {
        uiController.displayWelcome();
        menuOptionHandler();
        pickShapeHandler();
        if (shouldGoBackMenu) goBackMenuHandler();
        else checkGameOverHandler();
    }

    private void goBackMenuHandler() {
        if (shouldGoBackMenu) {
            shouldGoBackMenu = false;
            gameLoop();
        }
    }

    private void checkGameOverHandler() {
        if (isPlayer1Win) {
            uiController.displayGameOver(0, playerSelectedShape, computerSelectedShape);
        } else if (isPlayer2Win) {
            uiController.displayGameOver(1, playerSelectedShape, computerSelectedShape);
        } else {
            uiController.displayGameOver(2, playerSelectedShape, computerSelectedShape);
        }
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
                computerSelectedShape = selectRandomShape();
                playerSelectedShape = userInput;
                isPlayer1Win = compareShape(playerSelectedShape, computerSelectedShape);
                isPlayer2Win = compareShape(computerSelectedShape, playerSelectedShape);
            } else {
                pickShapeHandler();
            }
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
            System.out.println("check history");
        } else if (option.equals((Option.QUIT.getOption()))) {
            System.out.println("quit game");
        } else {
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
