package immediate.learning.support.dao;

import java.util.List;
import immediate.learning.support.entity.Subject;

public interface SubjectDao {
    public Subject findById(Long id);

    public List<Subject> findByName(String name);

    public void save(Subject entity);
	 
    public Subject update(Subject entity);
	 
    public void delete(Subject entity);
}