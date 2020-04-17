package repo;

import entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

public class ArtistRepository implements AbstractRepository<Artist, Integer> {

    private EntityManager em;

    public ArtistRepository(EntityManager em) {
        this.em = em;
    }

    public void create(Artist artist) {
        em.persist(artist);
    }

    public void delete(Artist artist) {
        em.remove(artist);
    }

    public Artist findById(Integer id) {
        return em.find(Artist.class, id);
    }

    public List<Artist> findByName(String name) {
        return em.createNamedQuery("Artist.findByName").setParameter("name", name).getResultList();
    }

}
