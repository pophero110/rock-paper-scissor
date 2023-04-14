
import java.util.*;

public class MenuOptions implements Menu {
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
        QUIT("quit"),
        DEFAULT("default")
        ;
        private final String option;

        Option(String option) {
            this.option = option;
        }

        public String getOption() {
            return option;
        }

        public static boolean contains(String value) {
            for (Option e : Option.values()) {
                if (e.getOption().equals(value)) {
                    return true;
                }
            }
            return false;
        }
    }

    public MenuOptions(GameState gameState, UIController uiController) {
        optionHandlers.put(Option.Players.getOption(), gameState::setGameModeToPlayers);

        optionHandlers.put(Option.Computer.getOption(), gameState::setGameModeToComputer);

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

    }

    public void handleOption(String option) throws IllegalArgumentException {
        if(!Option.contains(option)) throw new IllegalArgumentException();
        optionHandlers.get(option).run();
    }

    @Override
    public void showOption() {
        System.out.println("=== MAIN MENU ===");
        menuOptions.forEach(System.out::println);
    }

    public void setShouldGoBackMenu(boolean shouldGoBackMenu) {
        this.shouldGoBackMenu = shouldGoBackMenu;
    }

    public boolean getShouldGoBackMenu() {
        return shouldGoBackMenu;
    }
}
