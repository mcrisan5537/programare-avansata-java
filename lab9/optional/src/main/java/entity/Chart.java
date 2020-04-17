package entity;

import javax.persistence.*;

// charts table;
// CREATE TABLE charts(
//        id INTEGER NOT NULL PRIMARY KEY,
//        artist_id INTEGER NOT NULL REFERENCES artists(id),
//        album_id INTEGER NOT NULL REFERENCES albums(id),
//        name VARCHAR2(100),
//        position INTEGER
// );

@Entity
@Table(name = "charts")
@NamedQueries({
        @NamedQuery(name = "Chart.findByPosition", query = "SELECT c FROM Chart c WHERE c.position = :position AND c.chartName = :name"),
        @NamedQuery(name = "Chart.findByName", query = "SELECT c FROM Chart c WHERE c.chartName = :name")
})
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chart_generator")
    @SequenceGenerator(name="chart_generator", sequenceName = "chart_id")
    @Column(name = "ID")
    private int id;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;

    @Column(name = "position")
    private int position;

    @Column(name = "name")
    private String chartName;

    public Chart() {
    }

    public Chart(Artist artist, Album album, int position, String chartName) {
        this.artist = artist;
        this.album = album;
        this.position = position;
        this.chartName = chartName;
    }

    public Chart(int id, Artist artist, Album album, int position, String chartName) {
        this.id = id;
        this.artist = artist;
        this.album = album;
        this.position = position;
        this.chartName = chartName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Chart{" + "id=" + id + ", artist=" + artist.getName() + ", album=" + album.getName() + ", position=" + position + ", name='" + chartName + '\'' + '}';
    }
}
