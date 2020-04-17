package repo;

import java.util.List;

public interface AbstractRepository<T, ID> {

    void create(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> findByName(String name);

}
