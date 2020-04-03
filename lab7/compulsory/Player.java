import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Runnable {

    private String name;
    private Board board;
    private List<Token> picks = new ArrayList<>();
    private int k;

    public Player(String name, Board board, int k) {
        this.name = name;
        this.board = board;
        this.k = k;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(picks.size() < k) {
            int index = random.nextInt(board.getSize());
            Token token = board.getAndRemoveToken(index);
            System.out.println("Player " + name + " picking index " + index + " corresponding to value " + token.getValue() + ".");
            picks.add(token);
        }
    }

    public List<Token> getPicks() {
        return picks;
    }
}
