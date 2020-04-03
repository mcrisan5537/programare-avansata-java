public class Main {

    public static void main(String[] args) throws Exception {

        Game game = new Game(30, 50, 5);
        Player p1 = new Player("Player1", game.getBoard(), game.getArithmeticProgressionSize());
        Player p2 = new Player("Player2", game.getBoard(), game.getArithmeticProgressionSize());
        game.startGame(p1, p2);

    }

}
