import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ComputerPlayer extends Player implements RandomShapeSelector {
    public ComputerPlayer(String name) {
        super(name);
    }

    /**
     * @returna random valid shape (rock, paper, or scissors)
     */
    @Override
    public String selectRandomShape() {
        ArrayList<String> validShapes = new ArrayList<>(Arrays.asList("rock",
                "paper",
                "scissors"));
        Random random = new Random();
        int randomIndex = random.nextInt(validShapes.size());
        return validShapes.get(randomIndex);
    }
}
