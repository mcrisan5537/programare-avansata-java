import java.util.*;

public class SmartPlayer extends Player {

    public SmartPlayer(String name) {
        super(name);
    }

    @Override
    public Token pickToken(Scanner scanner) {
        if(getPicks().size() == 0) {
            // first round of turns, can't smart pick yet
            // pick random
            return randomPick();
        } else {
            //
            return smartPick();
        }
    }

    public Token randomPick() {
        Random random = new Random();
        // pick random token available on the board;
        Token token = Game.getBoard().getTokens().get(random.nextInt(Game.getBoard().getTokens().size()));
        token = Game.getBoard().getAndRemoveToken(token.getValue());
        // if picked token's value is 0, pick random value;
        if (token.getValue() == 0)
            token.setValue(random.nextInt(Game.getTokenMaxValue() + 1));

        return token;
    }

    public Token smartPick() {
        // key = arithmetic progression difference of other players
        // value = count of how many players are going for this arithmetic progression
        HashMap<Integer, Integer> differences = new HashMap<>();

        // gather information about other player's picks
        for(Player player : Game.getPlayers()) {
            // if player doesn't have 2 picks yet, then skip
            // as you can't determine difference yet
            if(player.getPicks().size() >= 2) {
                int difference = player.getPicks().get(1).getValue() - player.getPicks().get(0).getValue();

                Integer currentCount = differences.get(difference);
                if (currentCount == null)
                    differences.put(difference, 0);
                else
                    differences.put(difference, currentCount + 1);
            }
        }

        Token token;
        if(getPicks().size() < 2) {
            // if I haven't picked the second token
            // then pick token corresponding to the key with the highest value
            Integer mostFrequent = Collections.max(differences.values());
            // keyToMostFrequent == token value
            Integer keyToMostFrequent = null;
            for(Integer key : differences.keySet())
                if(differences.get(key).equals(mostFrequent)) {
                    keyToMostFrequent = key;
                    break;
                }
            int finalKeyToMostFrequent = keyToMostFrequent;
            int currentPickValue = getPicks().get(0).getValue();
            token = Game.getBoard().getTokens().stream().filter(num -> num.getValue() == finalKeyToMostFrequent + currentPickValue).findAny().orElse(null);
            // if I there are no tokens to hijack just pick random
            if(token == null) {
                return randomPick();
            } else {
                token = Game.getBoard().getAndRemoveToken(token.getValue());
            }
        } else {
            int myDifference = getPicks().get(1).getValue() - getPicks().get(0).getValue();
            int myLastPickValue = getPicks().get(getPicks().size() - 1).getValue();
            token = Game.getBoard().getTokens().stream().filter(num -> num.getValue() == myLastPickValue + myDifference).findAny().orElse(null);
            if(token == null) {
                // if there is a blank token use it
                if(Game.getBoard().areBlankTokensAvailable()) {
                    Game.getBoard().getAndRemoveToken(0);
                    return new Token(myLastPickValue + myDifference);
                }
            } else {
                return randomPick();
            }
        }

        // will never reach this point
        return token;
    }

}
