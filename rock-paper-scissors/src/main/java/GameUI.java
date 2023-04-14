/**
 * This is an interface for a game user interface. It defines methods that need to be
 * implemented by any class that wishes to be used as a user interface for a game.
 */
public abstract class GameUI {
    protected Game gameState;
    public GameUI(Game gameState) {
        this.gameState = gameState;
    }
    public abstract void displayShapeOptions();
    public abstract void displayWelcome();
    public abstract void displayGameOver();
    public abstract void displayHistory();
    public abstract void displayInvalidInput();
    public abstract void displayPlayerState();
}
