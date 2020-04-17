package repo.ArtistAccessProduct;

import entity.Artist;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCArtistAccessConcreteProduct extends ArtistAccessAbstractProduct {

    @Override
    public void create(Artist artist) {

        // get ID from database sequence and set artist's ID
        try(Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT artist_id.nextval FROM dual")) {
            resultSet.next();
            artist.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement preparedStatement = Database.getConnection().prepareStatement("INSERT INTO artists VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, artist.getId());
            preparedStatement.setString(2, artist.getName());
            preparedStatement.setString(3, artist.getCountry());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Artist artist) {

        try(PreparedStatement preparedStatement = Database.getConnection().prepareStatement("DELETE FROM artists WHERE name = ? and country = ?")) {
            preparedStatement.setString(1, "'" + artist.getName() + "'");
            preparedStatement.setString(2, "'" + artist.getCountry() + "'");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Artist findById(Integer id) {
        Artist artist = null;

        try(Statement statement = Database.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM artists WHERE id = " + id);
            resultSet.next();
            artist = new Artist(id, resultSet.getString(2), resultSet.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artist;
    }

    @Override
    public List<Artist> findByName(String name) {
        List<Artist> artists = new ArrayList<>();

        try(Statement statement = Database.getConnection().createStatement()) {
            Artist artist;
            ResultSet rs = statement.executeQuery("SELECT * FROM artists WHERE name = '" + name + "'");
            while(rs.next()) {
                artist = new Artist(rs.getInt(1), rs.getString(2), rs.getString(3));
                artists.add(artist);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return artists;
    }
}
