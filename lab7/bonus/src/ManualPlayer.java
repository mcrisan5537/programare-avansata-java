import java.util.Scanner;

public class ManualPlayer extends Player {

    public ManualPlayer(String name) {
        super(name);
    }

    @Override
    public Token pickToken(Scanner scannerz) {
        Token token;
        int value;
        do {
            System.out.print(getName()  + "'s turn, pick token by value: ");
            value = scanner.nextInt();
            token = Game.getBoard().getAndRemoveToken(value);
            if (token == null)
                System.out.println("Board doesn't contain a token with value " + value + "! Pick again.");
        } while (token == null);
            if (token.getValue() == 0) {
            System.out.print("Input value that the token with value 0 should have: ");
            value = scanner.nextInt();
            token.setValue(value);
        }
        return token;
    }
}
