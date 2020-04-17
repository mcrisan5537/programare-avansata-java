package repo;

import entity.Chart;

import javax.persistence.EntityManager;
import java.util.List;

public class ChartRepository implements AbstractRepository<Chart, Integer> {

    private EntityManager em;

    public ChartRepository(EntityManager em) {
        this.em = em;
    }

    public void create(Chart chart) {
        em.persist(chart);
    }

    public void delete(Chart chart) {
        em.remove(chart);
    }

    public Chart findById(Integer id) {
        return em.find(Chart.class, id);
    }

    public List<Chart> findByName(String chartName) {
        return em.createNamedQuery("Chart.findByName")
                .setParameter("name", chartName)
                .getResultList();
    }

    public Chart findByPosition(int position, String chartName) {
        return (Chart) em.createNamedQuery("Chart.findByPosition")
                .setParameter("position", position)
                .setParameter("name", chartName)
                .getSingleResult();
    }

}
