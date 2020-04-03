import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Board board;
    private int n, m, k;

    public Game(int n, int m, int k) {
        this.n = n;
        this.m = m;
        board = new Board(n, m);
        this.k = k;
    }

    public void startGame(Player p1, Player p2) throws InterruptedException {

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nFinal board " + board);
        System.out.println("Player 1 picks " + p1.getPicks());
        System.out.println("Player 2 picks " + p2.getPicks());

        if(isValidProgression(p1.getPicks())) {
            System.out.println("Player 1 wins.");
        } else if(isValidProgression(p2.getPicks())){
            System.out.println("Player 2 wins.");
        } else {
            System.out.println("Draw.");
        }
    }

    public int getArithmeticProgressionSize() {
        return k;
    }

    public Board getBoard() {
        return board;
    }

    private boolean isValidProgression(List<Token> picks) {
        int diff = picks.get(1).getValue() - picks.get(0).getValue();
        for (int i = 2; i < picks.size(); i++)
            if(picks.get(i).getValue() - picks.get(i-1).getValue() != diff)
                return false;

        return true;
    }

}
