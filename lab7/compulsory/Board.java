import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

    private List<Token> tokens = Collections.synchronizedList(new ArrayList<>());

    public Board(int n, int m) {
        // generate random board of size n
        // with tokens from 0 to m, where 0 is a blank token
        Random random = new Random();
        for(int i = 0; i < n; i++)
            tokens.add(new Token(random.nextInt(m)));
    }

    public Board(List<Token> tokens) {
        this.tokens = tokens;
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
    public String toString() {
        return tokens.toString();
    }
}
