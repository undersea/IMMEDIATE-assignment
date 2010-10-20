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
        
        associateSubject(title);
    }

    private void associateSubject(String title) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // find out if subject already exists or not and use that
        Query q = em.createQuery(String.format("select title from Subject where title like %s", title));
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
}