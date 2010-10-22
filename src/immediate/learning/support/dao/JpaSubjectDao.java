package immediate.learning.support.dao;

import java.util.List;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import immediate.learning.support.entity.Subject;

public class JpaSubjectDao extends JpaDaoSupport implements SubjectDao {
    public Subject findById(Long id) {
        return getJpaTemplate().find(Subject.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Subject> findByName(String name) {
        return getJpaTemplate().find("select s from Subject s where s.name = ?1", name);
    }

    public void save(Subject subject) {
        getJpaTemplate().persist(subject);
    }

  public Subject update(Subject subject) {
    return getJpaTemplate().merge(subject);
  }

  public void delete(Subject subject) {
    getJpaTemplate().remove(subject);
  }


}