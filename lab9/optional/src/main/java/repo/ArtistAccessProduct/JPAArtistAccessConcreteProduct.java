package repo.ArtistAccessProduct;

import entity.Artist;
import repo.AbstractRepository;

import java.util.List;

public class JPAArtistAccessConcreteProduct extends ArtistAccessAbstractProduct implements AbstractRepository<Artist, Integer> {

    @Override
    public void create(Artist artist) {
        util.PersistenceUtil.getEntityManager().persist(artist);
    }

    @Override
    public void delete(Artist artist) {
        util.PersistenceUtil.getEntityManager().remove(artist);
    }

    @Override
    public Artist findById(Integer id) {
        return util.PersistenceUtil.getEntityManager().find(Artist.class, id);
    }

    @Override
    public List<Artist> findByName(String name) {
        return util.PersistenceUtil.getEntityManager().createNamedQuery("Artist.findByName").setParameter("name", name).getResultList();
    }
}
