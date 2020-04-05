import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Player p1 = new ManualPlayer("Player1");
        Player p2 = new SmartPlayer("Player2");
        Game.init(Arrays.asList(p1, p2), 60, 30, 3);
        Game.start();

    }

}
