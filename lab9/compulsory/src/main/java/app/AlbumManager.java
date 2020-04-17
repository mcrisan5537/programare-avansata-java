package app;

import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import java.rmi.ServerError;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class AlbumManager {

    public static void main(String[] args) throws Exception {

        // ignore warning messages
        System.err.close();

        EntityManagerFactory factory = util.PersistenceUtil.getFactory();
        EntityManager em = factory.createEntityManager();
        AlbumRepository albumRepository = new AlbumRepository(em);
        ArtistRepository artistRepository = new ArtistRepository(em);
        em.getTransaction().begin();

        Artist kanye = artistRepository.findByName("Kanye").get(0);
        System.out.println(kanye);
        List<Album> albums = albumRepository.findByArtist(kanye);
        System.out.println(albums);

        Artist drake = new Artist("Drake", "Canada");
        artistRepository.create(drake);
        Album iyrtitl = new Album("If You're Reading This It's Too Late", 2015, drake);
        albumRepository.create(iyrtitl);
        Album wattba = new Album("What a Time to be Alive", 2015, drake);
        albumRepository.create(wattba);

        em.getTransaction().commit();
        em.close();
        factory.close();

    }

}
