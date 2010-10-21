package immediate.learning.support.gui;

import javax.swing.JPanel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

import immediate.learning.support.entity.Subject;


class SubjectView extends JPanel {
    private static final long serialVersionUID = 1L;

    private Subject subject;
    private static final String PERSISTENCE_UNIT_NAME = "learning";
    private EntityManagerFactory factory;

    SubjectView(String title) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        associateSubject(title);
        
    }

    SubjectView(Long id) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        associateSubject(id);
    }

    protected void associateSubject(String title) {
        EntityManager em = factory.createEntityManager();
        // find out if subject already exists or not and use that
        Query q = em.createQuery(String.format("select s from Subject s where title='%s'", title));
        @SuppressWarnings("unchecked")
        List<Subject> subjectList = q.getResultList();
        if(subjectList.size() != 0) {
            subject = subjectList.get(0);
        } else {
            // Create new subject
            em.getTransaction().begin();
            subject = new Subject();
            subject.setTitle(title);
            em.persist(subject);
            em.getTransaction().commit();
        
            em.close();
        }
    }


    protected void associateSubject(Long id) {
        EntityManager em = factory.createEntityManager();
        // find out if subject already exists or not and use that
        Query q = em.createQuery(String.format("select title from Subject where id=%ld", id));
        @SuppressWarnings("unchecked")
        List<Subject> subjectList = q.getResultList();
        if(subjectList.size() != 0) {
            subject = subjectList.get(0);
        } else {
            // throw a ecveption here as no subject by id provided exists
        }
    }
}
