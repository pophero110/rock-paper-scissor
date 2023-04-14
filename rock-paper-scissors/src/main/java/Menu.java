/**
 * defines the methods required for a menu in the game.
 * It includes options to handle menu selection, display menu options,
 * and check if the user wants to go back to the previous menu.
 */
public interface Menu {

    void handleOption(String key);

    void showOption();

    boolean getShouldGoBackMenu();

    void setShouldGoBackMenu(boolean shouldGoBackMenu);
}
