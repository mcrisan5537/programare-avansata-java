package repo.AlbumAccessProduct;

import entity.Album;
import entity.Artist;
import repo.AbstractRepository;

import java.util.List;

public abstract class AlbumAccessAbstractProduct {

    abstract void create(Album album);
    abstract void delete(Album album);
    abstract Album findById(Integer id);
    abstract List<Album> findByName(String name);
    abstract List<Album> findByArtist(Artist artist);

}

