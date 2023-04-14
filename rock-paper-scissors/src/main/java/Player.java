public abstract class Player {
    private int winningPoints;
    private int losingPoints;
    private int tiePoints;
    private String name;
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

    public void setName(String name) {
        this.name = name;
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
                "  Winning Points: %d\n" +
                        "  Losing Points: %d\n" +
                        "  Tie Points: %d\n" +
                        "  Previous Move: %s\n" +
                        "  Won Last Game: %b",
                winningPoints, losingPoints, tiePoints, selectedShape, isWinning);

    }
}
