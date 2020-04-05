import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game {

    // TIME_LIMIT in seconds
    private static long TIME_LIMIT = 300;

    private static List<Player> players;
    private volatile static Board board;
    private static int boardSize, tokenMaxValue, arithmeticProgressionSize, currentTurn = 0;

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

        Timekeeper timekeeper = new Timekeeper();
        timekeeper.setDaemon(true);
        timekeeper.start();

        executorService.shutdownNow();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        printResults();
    }

    private static class Timekeeper extends Thread {

        @Override
        public void run() {
            long secondsPassed = 0;
            // Game ends after TIME_LIMIT seconds.
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                secondsPassed++;
//                System.err.println("Time elapsed since start of the game: " + secondsPassed + "s");
            } while (secondsPassed < TIME_LIMIT);
            System.err.println("\n");
            System.err.println(TIME_LIMIT + " seconds have passed, stopping game...");
            System.exit(1);
        }

    }

    private static void printResults() {
        calculatePoints();

        System.out.println("\nFinal board : " + board);
        for(int i = 0; i < players.size(); i++)
            System.out.println("Player " + (i + 1) + "'s picks: " + players.get(i).getPicks() + ", points: " + players.get(i).getPoints());
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
            if(getLargestArithmeticProgression(player.getPicks()) == arithmeticProgressionSize) {
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

    public static int getPlayersSize() {
        return players.size();
    }

    public static int getCurrentTurn() {
        return currentTurn;
    }

    public static int getTokenMaxValue() {
        return tokenMaxValue;
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static void nextTurn() {
        Game.currentTurn = (currentTurn + 1) % getPlayersSize();
    }
}
