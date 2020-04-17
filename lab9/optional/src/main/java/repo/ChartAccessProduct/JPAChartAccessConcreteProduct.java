package repo.ChartAccessProduct;

import entity.Chart;
import repo.AbstractRepository;

import java.util.List;

public class JPAChartAccessConcreteProduct extends ChartAccessAbstractProduct implements AbstractRepository<Chart, Integer> {

    @Override
    public void create(Chart chart) {
        // am implementat doar pentru entitatea Artist
    }

    @Override
    public void delete(Chart chart) {
        // am implementat doar pentru entitatea Artist
    }

    @Override
    public Chart findById(Integer id) {
        // am implementat doar pentru entitatea Artist
        return null;
    }

    @Override
    public List<Chart> findByName(String chartName) {
        // am implementat doar pentru entitatea Artist
        return null;
    }

    @Override
    public Chart findByPosition(int position, String chartName) {
        // am implementat doar pentru entitatea Artist
        return null;
    }
}
