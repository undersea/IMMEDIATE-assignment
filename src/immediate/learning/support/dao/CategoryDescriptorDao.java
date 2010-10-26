package immediate.learning.support.dao;

import immediate.learning.support.entity.CategoryDescriptor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryDescriptorDao {
    public Class<CategoryDescriptor> getType();

    public CategoryDescriptor findById(Long id);

    public void save(CategoryDescriptor entity);
	 
    public CategoryDescriptor update(CategoryDescriptor entity);
	 
    public void delete(CategoryDescriptor entity);

    public List<CategoryDescriptor> getAll();

    public void close();
}