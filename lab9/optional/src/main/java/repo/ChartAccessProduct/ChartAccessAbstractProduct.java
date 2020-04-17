package repo.ChartAccessProduct;

import entity.Chart;
import repo.AbstractRepository;

import java.util.List;

public abstract class ChartAccessAbstractProduct {

    public abstract void create(Chart chart);
    public abstract void delete(Chart chart);
    public abstract Chart findById(Integer id);
    public abstract List<Chart> findByName(String chartName);
    public abstract Chart findByPosition(int position, String chartName);

}

