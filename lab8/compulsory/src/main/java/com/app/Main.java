package com.app;

import com.dao.AlbumController;
import com.dao.ArtistController;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArtistController artistController = new ArtistController();
        AlbumController albumController = new AlbumController();

        useArtistController(artistController, albumController);
        useAlbumController(artistController, albumController);

    }

    public static void useArtistController(ArtistController artistController, AlbumController albumController) {
        artistController.create("Tyler, The Creator", "United States");
        artistController.create("Kendrick Lamar", "United States");
        artistController.create("Drake", "United States");

        List<Artist> artists = artistController.findByName("Kanye West");
        for(Artist artist : artists)
            System.out.println(artist);
    }

    private static void useAlbumController(ArtistController artistController, AlbumController albumController) {
        albumController.create("IGOR", artistController.getIDByName("Tyler, The Creator"), 2019);
        albumController.create("Take Care", artistController.getIDByName("Drake"), 2011);
        albumController.create("Views", artistController.getIDByName("Drake"), 2016);

        List<Album> albums = albumController.findByArtist(artistController.getIDByName("Drake"));
        for(Album album : albums)
            System.out.println(album);
    }

}
