package immediate.learning.support.dao;

import java.util.List;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import immediate.learning.support.entity.Subject;

@Transactional
public class JpaSubjectDao extends JpaDaoSupport implements SubjectDao {
    public Subject findById(Long id) {
        return getJpaTemplate().find(Subject.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Subject> findByName(String name) {
        return getJpaTemplate().find("select s from Subject s where s.title = ?1", name);
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

    public void close() {
        try {
            getJpaTemplate().flush();
        }catch(Exception e) {
            System.err.println(getJpaTemplate().getEntityManager());
        }
    }
}