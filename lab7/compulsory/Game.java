import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game {

    private static List<Player> players;
    private static Board board;
    private static int boardSize, tokenMaxValue, arithmeticProgressionSize;

    public static void init(List<Player> players, int boardSize, int tokenMaxValue, int arithmeticProgressionSize) {
        Game.players = players;
        Game.boardSize = boardSize;
        Game.tokenMaxValue = tokenMaxValue;
        Game.board = new Board(boardSize, tokenMaxValue);
        Game.arithmeticProgressionSize = arithmeticProgressionSize;
    }

    public static void start() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(players.size());
        for(Player player : players)
            executorService.execute(player);
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        printResults();
    }

    private static void printResults() {
        calculatePoints();

        System.out.println("\nFinal board : " + board);
        for(int i = 0; i < players.size(); i++)
            System.out.println("Player " + (i + 1) + " picks: " + players.get(i).getPicks() + ", points: " + players.get(i).getPoints());
    }

    private static void calculatePoints() {
        if (board.getSize() == 0) {
            // if all tokens have been removed from the board
            // each player receives a number of points equal to the their largest arithmetic progression
            for (Player player : players)
                player.setPoints(getLargestArithmeticProgression(player.getPicks()));
        } else {
            // find winner and give him boardSize points
            Player player = findWinner(players);
            if(player != null)
                player.setPoints(boardSize);
        }
    }

    private static int getLargestArithmeticProgression(List<Token> picks) {
        int difference = picks.get(1).getValue() - picks.get(0).getValue();
        for (int i = 2; i < picks.size(); i++)
            if (picks.get(i).getValue() - picks.get(i - 1).getValue() != difference)
                return i - 1;

        return picks.size();
    }

    private static Player findWinner(List<Player> players) {
        Player winner = null;
        for(Player player : players)
            if(getLargestArithmeticProgression(player.getPicks()) == boardSize) {
                winner = player;
                break;
            }
        return winner;
    }

    public static int getArithmeticProgressionSize() {
        return arithmeticProgressionSize;
    }

    public static Board getBoard() {
        return board;
    }

}
