import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        Player p3 = new Player("Player3");
        Game.init(Arrays.asList(p1, p2, p3), 60, 30, 3);
        Game.start();

    }

}
