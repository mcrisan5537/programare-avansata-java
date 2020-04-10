package com.dao;

import com.app.Album;
import com.util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumController {

    public void create(String name, int artistID, int releaseYear) {
        try(PreparedStatement statement = Database.getConnection().prepareStatement("INSERT INTO albums VALUES(0, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, artistID);
            statement.setInt(3, releaseYear);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Album> findByArtist(int artistID) {
        List<Album> albums = new ArrayList<>();

        try(Statement statement = Database.getConnection().createStatement()) {
            Album album;
            ResultSet rs = statement.executeQuery("SELECT * FROM albums WHERE artist_id = " + artistID);
            while(rs.next()) {
                album = new Album(rs.getString(2), rs.getInt(3), rs.getInt(4));
                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return albums;
    }

}
