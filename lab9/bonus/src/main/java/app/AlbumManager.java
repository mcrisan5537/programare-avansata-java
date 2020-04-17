package app;

import com.github.javafaker.Faker;
import entity.Album;
import entity.Artist;
import entity.Chart;
import repo.AlbumRepository;
import repo.ArtistAccessProduct.ArtistAccessAbstractProduct;
import repo.ArtistRepository;
import repo.ChartRepository;
import repo.DataAccessFactory.DataAccessAbstractFactory;
import repo.DataAccessFactory.JDBCConcreteFactory;
import repo.DataAccessFactory.JPAConcreteFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.*;
import java.util.*;

public class AlbumManager {

    private static EntityManagerFactory factory = util.PersistenceUtil.getFactory();
    private static EntityManager em = util.PersistenceUtil.getEntityManager();

    private static AlbumRepository albumRepository = new AlbumRepository(em);
    private static ArtistRepository artistRepository = new ArtistRepository(em);
    private static ChartRepository chartRepository = new ChartRepository(em);

    public static void main(String[] args) throws Exception {

        em.getTransaction().begin();

//        em.getTransaction().begin();
//        useCharts();
//        em.getTransaction().commit();
//
//        Thread.sleep(12500);
//
//        em.getTransaction().begin();
//        useAbstractFactory();
//        em.getTransaction().commit();

        populateDatabase();
        em.getTransaction().commit();

        List<Album> albums = em.createNamedQuery("Album.getAll").getResultList();
        List<String> genres = em.createNamedQuery("Album.getGenres").getResultList();
        HashSet<Album> chosenAlbums = useAlgorithm(albums, genres);
        for(Album album : chosenAlbums)
            System.out.println(album);

        em.close();
        factory.close();
    }

    public static boolean readInitFile() {
        boolean value = false;
        try (Scanner scanner = new Scanner(new FileInputStream("config.txt"))) {
            scanner.next();
            scanner.next();
            value = scanner.nextBoolean();
        } catch (Exception e) {
            System.err.println("ERROR READING INITIALISATION FILE");
            e.printStackTrace();
        }
        return value;
    }

    public static void useAbstractFactory() {

        boolean useJDBC = readInitFile();
        DataAccessAbstractFactory dataAccessAbstractFactory;
        if(useJDBC) {
            dataAccessAbstractFactory = new JDBCConcreteFactory();
        } else {
            dataAccessAbstractFactory = new JPAConcreteFactory();
        }

        dataAccessAbstractFactory = new JPAConcreteFactory();
        ArtistAccessAbstractProduct a = dataAccessAbstractFactory.createArtistAccessConcreteProduct();
        a.create(new Artist(0, "Lil Uzi Vert", "United States"));
        a.create(new Artist(0, "Playboi Carti", "United States"));
        System.out.println(a.findByName("Kanye").get(0));

    }

    public static void useCharts() {

        Artist drake = new Artist("Drake", "Canada");
        artistRepository.create(drake);
        Album iyrtitl = new Album("If You're Reading This It's Too Late", 2015, drake, "Hip-Hop");
        albumRepository.create(iyrtitl);
        Album wattba = new Album("What a Time to be Alive", 2015, drake, "Hip-Hop");
        albumRepository.create(wattba);

        chartRepository.create(new Chart(drake, iyrtitl, 1, "Top 100 hip-hop"));
        chartRepository.create(new Chart(drake, wattba, 2, "Top 100 hip-hop"));

        System.out.println(chartRepository.findByName("Top 100 hip-hop"));

    }

    public static void populateDatabase() {

        Faker faker = new Faker();

        int noOfArtists = faker.number().numberBetween(500, 750);
        List<Artist> availableArtists = new ArrayList<>();
        Artist artist;

        // add artists
        for(int i = 0; i < noOfArtists; i++) {
            artist = new Artist(faker.funnyName().name(), faker.country().name());
            availableArtists.add(artist);
            artistRepository.create(artist);
        }

        // add albums
        int noOfAlbums = noOfArtists * 5;
        for(int i = 0; i < noOfAlbums; i++) {
            artist = availableArtists.get(faker.number().numberBetween(0, availableArtists.size()));
            albumRepository.create(new Album(faker.music().chord() + " " + faker.music().instrument(),
                                             faker.number().numberBetween(1960, 2020), artist, faker.music().genre()));
        }

    }

    public static HashSet<Album> useAlgorithm(List<Album> albums, List<String> genres) {

        HashSet<Album> chosenAlbums = new HashSet<>();
        HashSet<String> chosenGenres = new HashSet<>();
        HashSet<Artist> chosenArtist = new HashSet<>();

        for(Album album : albums) {
            if (!chosenAlbums.contains(album) && !chosenGenres.contains(album.getGenre()) &&
                    !chosenArtist.contains(album.getArtist())) {
                chosenAlbums.add(album);
                chosenArtist.add(album.getArtist());
                chosenGenres.add(album.getGenre());
            }
            // if the number of chosen albums is greater than the number of genres stop
            // the number of genres is small relative to the number of albums
            if(chosenAlbums.size() >= genres.size())
                break;
        }

        return chosenAlbums;
    }



}
