public class Main {
    public static void main(String[] args) {
        UIController uiController = new UIController();
        GameController gameController = new GameController(uiController);
        uiController.displayWelcome();
        gameController.gameLoop(false);
    }
}
