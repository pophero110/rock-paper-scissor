import java.util.ArrayList;

public class UIController {
    private ArrayList<String> menuOptions = new ArrayList<>();

    public UIController() {
        menuOptions.add("Type 'play' to play.");
        menuOptions.add("Type 'history' to view your game history.");
        menuOptions.add("Type 'quit' to stop playing.");
    }
    public void displayMenu() {
        System.out.println("MAIN MENU");
        System.out.println("=====");
        menuOptions.forEach(option -> System.out.println(option));
    }

    public void displayWelcome() {
        System.out.println("Welcome to Rock, Paper, Scissors!\n");
    }
}
