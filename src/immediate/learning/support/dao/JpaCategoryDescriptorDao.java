package immediate.learning.support.dao;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import immediate.learning.support.entity.CategoryDescriptor;

@Transactional
@Repository
public class JpaCategoryDescriptorDao extends JpaDaoSupport implements CategoryDescriptorDao {
    protected Class<CategoryDescriptor> identifier;
    public JpaCategoryDescriptorDao() {
        
        this.identifier = CategoryDescriptor.class;
    }

    public JpaCategoryDescriptorDao(Class<CategoryDescriptor> identifier) {
        
        this.identifier = identifier;

    }

    public Class<CategoryDescriptor> getType() {
        return identifier;
    }

    public CategoryDescriptor findById(Long id) {
        return getJpaTemplate().find(identifier, id);
    }

    @Transactional
    public void save(CategoryDescriptor entity) {
        getJpaTemplate().persist(entity);
    }

    @Transactional
    public CategoryDescriptor update(CategoryDescriptor entity) {
        return getJpaTemplate().merge(entity);
    }

    @Transactional
    public void delete(CategoryDescriptor entity) {
        getJpaTemplate().remove(entity);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryDescriptor> getAll() {
        
        return getJpaTemplate().find(String.format("select t from %s t", identifier.getSimpleName()));
    }

    @Transactional
    public void close() {
        System.out.println("CategoryDescriptor close");
        getJpaTemplate().flush();
    }

    public List<CategoryDescriptor> find(String where) {
        return getJpaTemplate().find(String.format("select t from %s t where %s", identifier.getSimpleName(), where));
    }
}