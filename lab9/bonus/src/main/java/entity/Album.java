package entity;

import javax.persistence.*;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findByName", query = "SELECT a FROM Album a WHERE a.name LIKE :name"),
        @NamedQuery(name = "Album.findByArtist", query = "SELECT a FROM Album a WHERE a.artist = :artist"),
        @NamedQuery(name = "Album.getAll", query = "SELECT a FROM Album a"),
        @NamedQuery(name = "Album.getGenres", query = "SELECT DISTINCT genre FROM Album")
})
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_generator")
    @SequenceGenerator(name="album_generator", sequenceName = "album_id")
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Artist artist;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "genre")
    private String genre;

    public Album() {
    }

    public Album(String name, int releaseYear, Artist artist, String genre) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.genre = genre;
    }

    public Album(int id, String name, Artist artist, int releaseYear, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", name='" + name + '\'' + ", artist=" + artist + ", releaseYear=" + releaseYear + ", genre='" + genre + '\'' + '}';
    }
}
