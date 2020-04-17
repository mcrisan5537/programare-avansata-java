package app;

import entity.Album;
import entity.Artist;
import entity.Chart;
import jdk.jshell.spi.ExecutionControl;
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
import java.util.Scanner;

public class AlbumManager {

    private static EntityManagerFactory factory = util.PersistenceUtil.getFactory();
    private static EntityManager em = util.PersistenceUtil.getEntityManager();

    private static AlbumRepository albumRepository = new AlbumRepository(em);
    private static ArtistRepository artistRepository = new ArtistRepository(em);
    private static ChartRepository chartRepository = new ChartRepository(em);

    public static void main(String[] args) throws Exception {

        em.getTransaction().begin();
        useCharts();
        em.getTransaction().commit();

        Thread.sleep(12500);

        em.getTransaction().begin();
        useAbstractFactory();
        em.getTransaction().commit();

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
        Album iyrtitl = new Album("If You're Reading This It's Too Late", 2015, drake);
        albumRepository.create(iyrtitl);
        Album wattba = new Album("What a Time to be Alive", 2015, drake);
        albumRepository.create(wattba);

        chartRepository.create(new Chart(drake, iyrtitl, 1, "Top 100 hip-hop"));
        chartRepository.create(new Chart(drake, wattba, 2, "Top 100 hip-hop"));

        System.out.println(chartRepository.findByName("Top 100 hip-hop"));

    }

}
