package gomoku;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int id;
    private List<Integer> playerIds = new ArrayList<>();
    private String game;
    private String game_date;
    private String result;

    public Game(int id, String game, String game_date, String result) {
        this.id = id;
        this.game = game;
        this.game_date = game_date;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<Integer> playerIds) {
        this.playerIds = playerIds;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getGame_date() {
        return game_date;
    }

    public void setGame_date(String game_date) {
        this.game_date = game_date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
