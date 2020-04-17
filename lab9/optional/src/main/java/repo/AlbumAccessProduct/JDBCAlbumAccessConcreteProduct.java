package repo.AlbumAccessProduct;

import entity.Album;
import entity.Artist;

import java.util.List;

public class JDBCAlbumAccessConcreteProduct extends AlbumAccessAbstractProduct {

    @Override
    public void create(Album album) {
        // am implementat doar pentru entitatea Artist
    }

    @Override
    public void delete(Album album) {
        // am implementat doar pentru entitatea Artist
    }

    @Override
    public Album findById(Integer id) {
        // am implementat doar pentru entitatea Artist
        return null;
    }

    @Override
    public List<Album> findByName(String name) {
        // am implementat doar pentru entitatea Artist
        return null;
    }

    @Override
    public List<Album> findByArtist(Artist artist) {
        // am implementat doar pentru entitatea Artist
        return null;
    }

}
