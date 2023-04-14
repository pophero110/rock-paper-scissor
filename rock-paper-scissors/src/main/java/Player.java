/**
 This abstract class represents a player in a game. It has fields for the player's winning points, losing points,
 and tie points, as well as their name, selected shape, and whether they won the last game. The class provides
 getter and setter methods for these fields. It also overrides the toString method to print out the player's current
 status, including their winning points, losing points, tie points, previous move, and whether they won the
 last game.
 */
public abstract class Player {
    private int winningPoints;
    private int losingPoints;
    private int tiePoints;
    private final String name;
    private String selectedShape;
    private boolean isWinning;

    public Player(String name) {
        this.name = name;
    }

    public int getWinningPoints() {
        return winningPoints;
    }

    public void setWinningPoints(int winningPoints) {
        this.winningPoints = winningPoints;
    }

    public String getName() {
        return name;
    }

    public String getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(String selectedShape) {
        this.selectedShape = selectedShape;
    }

    public boolean isWinning() {
        return isWinning;
    }

    public void setWinning(boolean winning) {
        isWinning = winning;
    }

    public int getLosingPoints() {
        return losingPoints;
    }

    public void setLosingPoints(int losingPoints) {
        this.losingPoints = losingPoints;
    }

    public int getTiePoints() {
        return tiePoints;
    }

    public void setTiePoints(int tiePoints) {
        this.tiePoints = tiePoints;
    }

    @Override
    public String toString() {
        return String.format(
                """
                          Winning Points: %d
                          Losing Points: %d
                          Tie Points: %d
                          Previous Move: %s
                          Won Last Game: %b\
                        """,
                winningPoints, losingPoints, tiePoints, selectedShape, isWinning);

    }
}
