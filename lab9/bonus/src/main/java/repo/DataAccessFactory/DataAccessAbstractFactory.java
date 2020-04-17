package repo.DataAccessFactory;

import repo.AlbumAccessProduct.AlbumAccessAbstractProduct;
import repo.ArtistAccessProduct.ArtistAccessAbstractProduct;
import repo.ChartAccessProduct.ChartAccessAbstractProduct;

public abstract class DataAccessAbstractFactory {

    public abstract ArtistAccessAbstractProduct createArtistAccessConcreteProduct();
    public abstract AlbumAccessAbstractProduct createAlbumAccessConcreteProduct();
    public abstract ChartAccessAbstractProduct createChartAccessConcreteProduct();

}

