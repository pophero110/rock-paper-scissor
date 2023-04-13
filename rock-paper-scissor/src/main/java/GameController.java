import java.util.*;
import java.util.stream.Collectors;

public class GameController {

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
        public GameController() {}


    public void getMenuOption() {
        Scanner scanner = new Scanner(System.in);
        String selectedOption = scanner.nextLine();
        menuOptionHandler(selectedOption);
    }

    private void menuOptionHandler(String option) {
        option = option.toLowerCase();
        if (option.equals(Option.Players.getOption())) {
            System.out.println("two players");
        } else if (option.equals(Option.Computer.getOption())) {
            System.out.println("computer");
        } else if (option.equals((Option.HISTORY.getOption()))) {
            System.out.println("check history");
        } else if (option.equals((Option.QUIT.getOption()))) {
            System.out.println("quit game");
        } else {
            System.out.println("invalid option");
        }
    }

    public void getShapeOption() {
        Scanner scanner = new Scanner(System.in);
        String selectedShape = scanner.nextLine();
        boolean isValidShape = validateShape(selectedShape.toLowerCase());
        if (isValidShape) {
            System.out.println("you selected " + selectedShape);
        } else {
            System.out.println("invalid shape");
        }
    }

    private boolean validateShape(String shape) {
        ArrayList<String> validShapes = new ArrayList<>(Arrays.asList("rock",
                "paper",
                "scissors"));
        return validShapes.contains(shape);
    }


    public void play() {}
    public void history() {}
    public void quit() {}
}
