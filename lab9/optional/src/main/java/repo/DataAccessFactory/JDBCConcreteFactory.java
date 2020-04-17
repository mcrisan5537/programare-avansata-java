package repo.DataAccessFactory;

import repo.AlbumAccessProduct.AlbumAccessAbstractProduct;
import repo.ArtistAccessProduct.ArtistAccessAbstractProduct;
import repo.ChartAccessProduct.ChartAccessAbstractProduct;
import repo.ArtistAccessProduct.JDBCArtistAccessConcreteProduct;

public class JDBCConcreteFactory extends DataAccessAbstractFactory {

    @Override
    public ArtistAccessAbstractProduct createArtistAccessConcreteProduct() {
        return new JDBCArtistAccessConcreteProduct();
    }

    @Override
    public AlbumAccessAbstractProduct createAlbumAccessConcreteProduct() {
        // am implementat doar pentru entitatea Artist
        return null;
    }

    @Override
    public ChartAccessAbstractProduct createChartAccessConcreteProduct() {
        // am implementat doar pentru entitatea Artist
        return null;
    }
}
