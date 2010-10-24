package immediate.learning.support.dao;


import java.util.List;

public interface Dao<T> {
    public Class<T> getType();

    public T findById(Long id);

    public void save(T entity);
	 
    public T update(T entity);
	 
    public void delete(T entity);
}