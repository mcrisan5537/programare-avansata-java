package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

public class AlbumRepository implements AbstractRepository<Album, Integer> {

    private EntityManager em;

    public AlbumRepository(EntityManager em) {
        this.em = em;
    }

    public void create(Album album) {
        em.persist(album);
    }

    public void delete(Album album) {
        em.remove(album);
    }

    public Album findById(Integer id) {
        return em.find(Album.class, id);
    }

    public List<Album> findByName(String name) {
        return em.createNamedQuery("Album.findByName").setParameter("name", name).getResultList();
    }

    public List<Album> findByArtist(Artist artist) {
        return em.createNamedQuery("Album.findByArtist").setParameter("artist", artist).getResultList();
    }

}
