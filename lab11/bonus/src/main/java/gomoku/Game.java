package gomoku;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io .*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {

    private int id;
    private List<Integer> playerIds = new ArrayList<>();
    private String game;
    private String game_date = null;
    private String result;

    public static class StrippedGame {
        private int id;
        private int playerId;
        private String game;
        private String game_date;
        private String result;

        public StrippedGame(int id, int playerId, String game, String game_date, String result) {
            this.id = id;
            this.playerId = playerId;
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

        public int getPlayerId() {
            return playerId;
        }

        public void setPlayerId(int playerId) {
            this.playerId = playerId;
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

    private static int gameID = 1;

    private String filename = "game" + new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()) + ".html";

    private Board board;
    private Player player1;
    private Player player2;

    private Player currentPlayer;
    private boolean isFinished = false;

    private PrintWriter representation;
    private int roundNumber = 0;

    public static List<StrippedGame> getGames() {
        List<StrippedGame> games = new ArrayList<>();

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM games");
            StrippedGame game;
            while(rs.next()) {
                game = new StrippedGame(rs.getInt(1), rs.getInt(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5));
                games.add(game);
            }
            statement.close();
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return games;
    }


    public static boolean addGame(Game game) {
        boolean returnVal = false;

        List<Integer> playerIds = game.getPlayerIds();
        System.out.println(playerIds);
        try {
            for(Integer playerId : playerIds) {
                PreparedStatement preparedStatement = Database.getConnection().prepareStatement("INSERT INTO games VALUES(?, ?, ?, CURRENT_DATE, ?)");
                preparedStatement.setInt(1, game.getId());
                preparedStatement.setInt(2, playerId);
                preparedStatement.setString(3, game.getGame());
                preparedStatement.setString(4, game.getResult());
                if(preparedStatement.executeUpdate() != 0) {
                    returnVal = true;
                } else {
                    returnVal = false;
                }
                preparedStatement.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return returnVal;
    }

    public Game() {
    }

    public Game(int id, String game, String game_date, String result) {
        this.id = id;
        this.game = game;
        this.game_date = game_date;
        this.result = result;
    }

    public Game(Board board, Player player1, Player player2) throws FileNotFoundException {
        Game.gameID++;
        this.board = board;
        this.currentPlayer = player1;
        this.player1 = player1;
        player1.setColor('B');
        this.player2 = player2;
        player2.setColor('W');

        representation = new PrintWriter(filename);
        HTMLRepresentation.insertHeader(representation);
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

    public boolean put(Player player, int row, char column) {
        // if game is not finished
        if(!isFinished) {
            column = Character.toUpperCase(column);

            if(player == currentPlayer) {
                row--;
                column = (char) (column - 'A');

                // if position is blank take position
                if(board.get()[row][column] == '.') {
                    board.get()[row][column] = currentPlayer.getColor();
                    roundNumber++;
                    HTMLRepresentation.insertGameRound(representation, roundNumber, currentPlayer, board);
                    // change player turns
                    currentPlayer = switchPlayer(currentPlayer);
                    return true;
                }

                // if position is occupied return false
                return false;
            }

            // if it's not this player's turn
            return false;
        }

        // if game is finished return false
        return false;
    }

    public boolean isWon(Player player, int rowPick, int columnPick) {
        // check for horizontal, vertical, rightDiagonal and leftDiagonal 5 in a row
        if(horizontal(player, rowPick, columnPick) || vertical(player, rowPick, columnPick) || rightDiagonal(player,
                                                                                                             rowPick,
                                                                                                             columnPick) || leftDiagonal(
                player,
                rowPick,
                columnPick)) {
            isFinished = true;
            endGame(player);
            return true;
        }
        return false;
    }

    public boolean rightDiagonal(Player player, int rowPick, int columnPick) {
        int i = rowPick, j = columnPick;
        while(isNotOutOfBounds(i - 1, j + 1)) {
            i--;
            j++;
        }

        int countInARow = 0;
        while(isNotOutOfBounds(i, j)) {
            if(board.get()[i][j] == player.getColor()) {
                countInARow++;
                if(countInARow == 5)
                    return true;
            } else {
                countInARow = 0;
            }
            i++;
            j--;
        }

        return false;
    }

    public boolean leftDiagonal(Player player, int rowPick, int columnPick) {
        int i = rowPick, j = columnPick;
        while(isNotOutOfBounds(i - 1, j - 1)) {
            i--;
            j--;
        }

        int countInARow = 0;
        while(isNotOutOfBounds(i, j)) {
            if(board.get()[i][j] == player.getColor()) {
                countInARow++;
                if(countInARow == 5)
                    return true;
            } else {
                countInARow = 0;
            }
            i++;
            j++;
        }

        return false;
    }

    public boolean vertical(Player player, int rowPick, int columnPick) {
        int countInARow = 0;
        for(int i = 0; i < board.get().length; i++) {
            if(board.get()[i][columnPick] == player.getColor()) {
                countInARow++;
                if(countInARow == 5)
                    return true;
            } else {
                countInARow = 0;
            }
        }
        return false;
    }

    public boolean horizontal(Player player, int rowPick, int columnPick) {
        int countInARow = 0;
        for(int j = 0; j < board.get()[0].length; j++) {
            if(board.get()[rowPick][j] == player.getColor()) {
                countInARow++;
                if(countInARow == 5)
                    return true;
            } else {
                countInARow = 0;
            }
        }
        return false;
    }

    private boolean isNotOutOfBounds(int row, int column) {
        return (row >= 0 && row < board.get().length) && (column >= 0 && column < board.get()[0].length);
    }

    private Player switchPlayer(Player player) {
        if(player == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    private void endGame(Player player) {
        if(representation != null) {
            HTMLRepresentation.insertFooter(representation, player);
            representation.close();
            representation = null;
        }
        try {
//            FTPClient ftpClient = new FTPClient();
//            ftpClient.connect("192.168.64.2", 21);
//            ftpClient.login("daemon", "xampp");
//            ftpClient.enterLocalPassiveMode();
//            ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
//            ftpClient.storeFile(filename, new FileInputStream(filename));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static int getID() {
        return Game.gameID;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static int getGameID() {
        return gameID;
    }

    public static void setGameID(int gameID) {
        Game.gameID = gameID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public PrintWriter getRepresentation() {
        return representation;
    }

    public void setRepresentation(PrintWriter representation) {
        this.representation = representation;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
