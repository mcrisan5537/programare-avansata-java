package app;

import entity.Album;
import entity.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbumManagerTest {

    @Test
    void distinctGenre() {

        Artist artist1 = new Artist("name1", "country1");
        Artist artist2 = new Artist("name2", "country2");
        String genre = "genre";

        List<Album> albums = new ArrayList<>();
        albums.add(new Album("album1", 2000, artist1, genre));
        albums.add(new Album("album2", 2000, artist2, genre));

        HashSet<Album> chosenAlbums = AlbumManager.useAlgorithm(albums, Arrays.asList(genre, genre));

        assertEquals(1, chosenAlbums.size());

    }

    @Test
    void distinctArtist() {

        Artist artist = new Artist("name", "country");

        List<Album> albums = new ArrayList<>();
        albums.add(new Album("album1", 2000, artist, "genre1"));
        albums.add(new Album("album1", 2000, artist, "genre2"));

        HashSet<Album> chosenAlbums = AlbumManager.useAlgorithm(albums, Arrays.asList("genre1", "genre2"));

        assertEquals(1, chosenAlbums.size());

    }

}