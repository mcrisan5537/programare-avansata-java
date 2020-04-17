package repo.ArtistAccessProduct;

import entity.Artist;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class ArtistAccessAbstractProduct {

    public abstract void create(Artist artist);
    public abstract void delete(Artist artist);
    public abstract Artist findById(Integer id);
    public abstract List<Artist> findByName(String name);

}

