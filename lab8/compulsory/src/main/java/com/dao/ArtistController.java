package com.dao;

import com.app.Artist;
import com.util.Database;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistController {

    public void create(String name, String country) {
        try(PreparedStatement statement = Database.getConnection().prepareStatement("INSERT INTO artists VALUES(0, ?, ?)")) {
            // ID value generated inside DBMS
            statement.setString(1, name);
            statement.setString(2, country);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Artist> findByName(String name) {
        List<Artist> artists = new ArrayList<>();

        try(Statement statement = Database.getConnection().createStatement()) {
            Artist artist;
            ResultSet rs = statement.executeQuery("SELECT * FROM artists WHERE name = '" + name + "'");
            while(rs.next()) {
                artist = new Artist(rs.getString(2), rs.getString(3));
                artists.add(artist);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return artists;
    }

    public int getIDByName(String name) {
        int artistID = -1;

        try(Statement statement = Database.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM artists WHERE name = '" + name + "' AND rownum <= 1");
            rs.next();
            artistID = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artistID;
    }

}
