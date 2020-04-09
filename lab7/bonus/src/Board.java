import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

    private volatile List<Token> tokens = Collections.synchronizedList(new ArrayList<>());

    public Board(int boardSize, int tokenMaxValue) {
        // generate random board of size boardSize
        // with tokens from 0 to tokenMaxValue, where 0 is a blank token
        Random random = new Random();
        for(int i = 0; i < boardSize; i++)
            tokens.add(new Token(random.nextInt(tokenMaxValue)));
        Game.boardAsString = tokens.toString();
    }

    public synchronized int getSize() {
        return tokens.size();
    }

    public synchronized Token getAndRemoveToken(int value) {
        Token pickedToken = null;
        for(Token token : tokens)
            if(token.getValue() == value) {
                pickedToken = token;
                tokens.remove(token);
                Game.boardAsString = tokens.toString();
                break;
            }
        return pickedToken;
    }

    @Override
    public synchronized String toString() {
        return tokens.toString();
    }

    public synchronized List<Token> getTokens() {
        return tokens;
    }

    public synchronized boolean areBlankTokensAvailable() {
        return tokens.stream().anyMatch(token -> token.getValue() == 0);
    }

}
