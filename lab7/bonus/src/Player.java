import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player implements Runnable {

    private String name;
    private volatile List<Token> picks = new ArrayList<>();
    public String picksAsString;
    private int points;

    // used to assign playerNumber to players
    private static int playerCounter;
    // used to number players in increasing order, used for ensuring turns
    private int playerNumber;
    // used for reading token input
    Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        Thread.currentThread().setName(name);
        this.name = name;
        this.playerNumber = playerCounter;
        Player.playerCounter++;
    }

    @Override
    public void run() {
        while(picks.size() < Game.getArithmeticProgressionSize()) {
            // use Game.getBoard()'s monitor in order to ensure mutual exclusion of players on board object
            synchronized (Game.getBoard()) {
                // while it's not this player's turn, wait
                while (playerNumber != Game.getCurrentTurn()) {
                    try {
                        Game.getBoard().wait();
                    } catch (InterruptedException ignored) {}
                }

                System.out.println("Current board: " + Game.getBoard());
                Token token = pickToken(scanner);
                System.out.println(getName()  + " picked value " + token.getValue() + ".");
                System.out.println();
                picks.add(token);
                picksAsString = picks.toString();

                // move onto next player
                Game.nextTurn();
                Game.getBoard().notifyAll();
            }
        }
    }

    public abstract Token pickToken(Scanner scanner);

    public synchronized List<Token> getPicks() {
        return picks;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public synchronized String getPicksString() {
        return picks.toString();
    }

}
