package gomoku;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private int id;
    private String name;

    private char color;

    public static List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM players");
            Player player;
            while(rs.next()) {
                player = new Player(rs.getInt(1), rs.getString(2));
                players.add(player);
            }
            statement.close();
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return players;
    }

    public static boolean addPlayer(Player player) {
        boolean returnVal = false;

        try {
            PreparedStatement preparedStatement = Database.getConnection()
                                    .prepareStatement("INSERT INTO PLAYERS VALUES(?, ?)");
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, player.getName());
            if(preparedStatement.executeUpdate() != 0)
                returnVal = true;
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return returnVal;
    }

    public static boolean changePlayerName(Player player, int id) {
        boolean returnVal = false;

        try {
            PreparedStatement preparedStatement = Database.getConnection()
                                    .prepareStatement("UPDATE players SET name = ? WHERE id = ?");
            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(2, id);
            if(preparedStatement.executeUpdate() != 0)
                returnVal = true;
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return returnVal;
    }

    public static boolean deletePlayer(int id) {
        boolean returnVal = false;

        try {
            PreparedStatement preparedStatement = Database.getConnection()
                                    .prepareStatement("DELETE FROM players WHERE id = ?");
            preparedStatement.setInt(1, id);
            if(preparedStatement.executeUpdate() != 0)
                returnVal = true;
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return returnVal;
    }

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

}
