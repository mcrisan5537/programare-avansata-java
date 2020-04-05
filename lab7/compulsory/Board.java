import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

    private List<Token> tokens = Collections.synchronizedList(new ArrayList<>());

    public Board(int boardSize, int tokenMaxValue) {
        // generate random board of size boardSize
        // with tokens from 0 to tokenMaxValue, where 0 is a blank token
        Random random = new Random();
        for(int i = 0; i < boardSize; i++)
            tokens.add(new Token(random.nextInt(tokenMaxValue)));
    }

    public synchronized int getSize() {
        return tokens.size();
    }

    public synchronized Token getAndRemoveToken(int index) {
        if(index < 0 || index > tokens.size()) {
            return null; // index out of bounds;
        }
        Token token = tokens.get(index);
        tokens.remove(index);
        return token;
    }

    @Override
    public synchronized String toString() {
        return tokens.toString();
    }

}
