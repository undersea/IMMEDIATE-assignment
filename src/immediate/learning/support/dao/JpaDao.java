package immediate.learning.support.dao;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JpaDao<T> extends JpaDaoSupport implements Dao<T> {
    protected Class<T> identifier;

    protected JpaDao() {}

    public JpaDao(Class<T> identifier) {
        this.identifier = identifier;

    }

    public Class<T> getType() {
        return identifier;
    }

    public T findById(Long id) {
        return getJpaTemplate().find(identifier, id);
    }

    @Transactional
    public void save(T entity) {
        getJpaTemplate().persist(entity);
    }

    @Transactional
    public T update(T entity) {
        return getJpaTemplate().merge(entity);
    }

    @Transactional
    public void delete(T entity) {
        getJpaTemplate().remove(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        
        return getJpaTemplate().find(String.format("select t from %s t", identifier.getSimpleName()));
    }

    @Transactional
    public void close() {
        getJpaTemplate().flush();
    }
}