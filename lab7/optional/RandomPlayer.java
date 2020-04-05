import java.util.Random;
import java.util.Scanner;

public class RandomPlayer extends Player {

    private Random random = new Random();

    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public Token pickToken(Scanner scanner) {
        // pick random token available on the board;
        Token token = Game.getBoard().getTokens().get(random.nextInt(Game.getBoard().getTokens().size()));
        // if picked token's value is 0, pick random value;
        if (token.getValue() == 0)
            token.setValue(random.nextInt(Game.getTokenMaxValue() + 1));

        return token;
    }
}
