package immediate.learning.support.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

import immediate.learning.support.entity.Subject;
import immediate.learning.support.dao.SubjectDao;

/*the only view class not to implement View due to it's specialised needs*/
public class SubjectView extends Box {
    private static final long serialVersionUID = 1L;

    private Subject subject;

    private SubjectDao subjectDao;

    public SubjectView(String title, SubjectDao dao) {
        super(BoxLayout.Y_AXIS);
        subjectDao = dao;
        associateSubject(title);
        JLabel idLabel = new JLabel("Subject ID");
        add(idLabel);
        JTextField idField = new JTextField(subject.getId().toString());
        add(idField);
        JLabel titleLabel = new JLabel("Subject");
        add(titleLabel);
        JTextField titleField = new JTextField(subject.getTitle());
        add(titleField);
        
    }

    public SubjectView(Long id, SubjectDao dao) {
        super(BoxLayout.Y_AXIS);
        subjectDao = dao;
        associateSubject(id);
    }

    protected void associateSubject(String title) {
        List<Subject> subjectList = subjectDao.findByName(title);
        if(subjectList.size() != 0) {
            subject = subjectList.get(0);
        } else {
            // Create new subject
            subject = new Subject();
            subject.setTitle(title);
            subjectDao.save(subject);
        }
    }


    protected void associateSubject(Long id) {
        subject = subjectDao.findById(id);
    }
}
