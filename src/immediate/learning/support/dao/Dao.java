package immediate.learning.support.dao;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Dao<T> {
    public Class<T> getType();

    public T findById(Long id);

    public void save(T entity);
	 
    public T update(T entity);
	 
    public void delete(T entity);

    public List<T> getAll();

    public void close();
}