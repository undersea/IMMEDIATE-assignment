package immediate.learning.support.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

import immediate.learning.support.entity.Subject;
import immediate.learning.support.dao.SubjectDao;

public class SubjectView extends JPanel {
    private static final long serialVersionUID = 1L;

    private Subject subject;

    private SubjectDao subjectDao;

    public SubjectView(String title, SubjectDao dao) {
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
