import java.util.ArrayList;

/**
 * The Game interface defines methods to fetch the game state, update the game state, reset the game state, add game state to history.
 */
public interface Game {
    void updatePlayerState();
    void reset();
    void addHistory();
    ArrayList<String> getHistory();
    String getGameMode();
    void setGameModeToPlayers();
    void setGameModeToComputer();
    boolean isPlayerMode();
    boolean isComputerMode();
    int getPlayerTurn();
    void setPlayerTurn(int playerTurn);
    int getGameState();
    void setGameState(int gameState);
    boolean isPlayer1Win();
    void setPlayer1Win(boolean player1Win);
    boolean isPlayer2Win();
    void setPlayer2Win(boolean player2Win);
    Player getPlayer1();
    Player getPlayer2();
    void setPlayer2(Player player2);
    String getPlayer1SelectedShape();
    void setPlayer1SelectedShape(String player1SelectedShape);
    String getPlayer2SelectedShape();
    void setPlayer2SelectedShape(String player2SelectedShape);
    void updateGameState(String userInput);
}