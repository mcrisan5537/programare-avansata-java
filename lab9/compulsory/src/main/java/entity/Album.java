package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findByName", query = "SELECT a FROM Album a WHERE a.name LIKE :name"),
        @NamedQuery(name = "Album.findByArtist", query = "SELECT a FROM Album a WHERE a.artist = :artist")
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

    public Album() {
    }

    public Album(String name, int releaseYear, Artist artist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.artist = artist;
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

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", name=" + name + ", artist=" + artist.getName() + ", releaseYear=" + releaseYear + '}';
    }
}
