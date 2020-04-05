import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Runnable {

    private String name;
    private List<Token> picks = new ArrayList<>();
    private int points;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(picks.size() < Game.getArithmeticProgressionSize()) {
            int index = random.nextInt(Game.getBoard().getSize());
            Token token = Game.getBoard().getAndRemoveToken(index);
            // (index + 1) so it gives a visual effect that board indexing starts from 1
            System.out.println("Player " + name + " picking index " + (index + 1) + " corresponding to value " + token.getValue() + ".");
            picks.add(token);
        }
    }

    public List<Token> getPicks() {
        return picks;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
