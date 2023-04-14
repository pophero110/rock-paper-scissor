
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class MenuOptions extends Options {
    final private Map<String, Runnable> optionHandlers = new HashMap<>();
    final private static List<String> menuOptions = Arrays.asList(
            "Type '2 players' or 'vs. computer' to play.",
            "Type 'history' to view your game history.",
            "Type 'state' to view your game state.",
            "Type 'quit' to stop playing."
    );
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

    public MenuOptions(GameState gameState, UIController uiController) {
        optionHandlers.put(Option.Players.getOption(), () -> {
            gameState.setPlayer2(new HumanPlayer("Player2"));
            gameState.setGameMode(Option.Players.getOption());
        });

        optionHandlers.put(Option.Computer.getOption(), () -> {
            gameState.setPlayer2(new ComputerPlayer("Computer"));
            gameState.setGameMode(Option.Computer.getOption());
        });

        optionHandlers.put(Option.HISTORY.getOption(), () -> {
            uiController.displayHistory();
            setShouldGoBackMenu(true);
        });

        optionHandlers.put(Option.STATE.getOption(), () -> {
            uiController.displayPlayerState();
            setShouldGoBackMenu(true);
        });

        optionHandlers.put(Option.QUIT.getOption(), () -> {
            System.out.println("Game Terminated");
            System.exit(0);
        });

        optionHandlers.put("default", uiController::displayInvalidInput);
    }

    public void handleOption(String option) {
        optionHandlers.getOrDefault(option, optionHandlers.get("default")).run();
    }

    public void setShouldGoBackMenu(boolean shouldGoBackMenu) {
        this.shouldGoBackMenu = shouldGoBackMenu;
    }

    public boolean getShouldGoBackMenu() {
        return shouldGoBackMenu;
    }

    public void displayMenu() {
        System.out.println("=== MAIN MENU ===");
        menuOptions.forEach(System.out::println);
    }

    public String getPlayersOption() {
        return Option.Players.getOption();
    }

    public String getComputerOption() {
        return Option.Computer.getOption();
    }
}
