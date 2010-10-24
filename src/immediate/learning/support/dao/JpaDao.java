package immediate.learning.support.dao;

import org.springframework.orm.jpa.support.JpaDaoSupport;

public class JpaDao<T> extends JpaDaoSupport implements Dao<T> {
    protected Class<T> identifier;

    public JpaDao(Class<T> identifier) {
        this.identifier = identifier;
    }

    public Class<T> getType() {
        return identifier;
    }

    public T findById(Long id) {
        return getJpaTemplate().find(identifier, id);
    }


    public void save(T entity) {
        getJpaTemplate().persist(entity);
    }


    public T update(T entity) {
        return getJpaTemplate().merge(entity);
    }


    public void delete(T entity) {
        getJpaTemplate().remove(entity);
    }
}