import immediate.learning.support.gui.SubjectView;
import immediate.learning.support.gui.CategoryView;
import immediate.learning.support.component.ExplainMore;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;

import immediate.learning.support.dao.SubjectDao;
import immediate.learning.support.dao.Dao;
import immediate.learning.support.dao.CategoryDescriptorDao;
import immediate.learning.support.entity.Category;
import immediate.learning.support.entity.CategoryDescriptor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class Main {
    @Autowired
    @Qualifier(value = "categoryDao")
    static Dao<Category> categoryDao;
    @Autowired
    @Qualifier(value = "categoryDescriptorDao")
    static CategoryDescriptorDao categoryDescriptorDao;

    @SuppressWarnings("unchecked")
    public static void main(String[] argv) {
        
        

        ClassPathXmlApplicationContext appContext = 
            new ClassPathXmlApplicationContext(new String[] {
                    "applicationContext.xml"
                });

        final SubjectDao subjectDao = (SubjectDao)appContext.getBean("subjectDao");
        //final JpaTransactionMana tmanager = (JpaTransactionMana)appContext.getBean("transactionManager");
        categoryDao = (Dao<Category>)appContext.getBean("categoryDao");
        categoryDescriptorDao = 
            (CategoryDescriptorDao)appContext.getBean("categoryDescriptorDao");
        //final Object transaction = tmanager.doGetTransaction();
        //tmanager.doBegin(transaction, 
        SubjectView view = new SubjectView("Calculus I", appContext);
        System.out.println("CategoryDao: " + categoryDao.getType().getSimpleName());
        System.out.println("CategoryDescriptorDao: " + categoryDescriptorDao.getType());
        
        //final JpaCategoryDescriptorDao catDao = categoryDescriptorDao;
        //ExplainMore more = new ExplainMore();
        //javax.swing.JComponent panel = more.configure();
        //view.add(panel);
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent event) {
                    subjectDao.close();
                    //catDao.close();
                    System.exit(0);
                }
            });
        frame.getContentPane().add(view);
        //frame.getContentPane().add(new MakeCategory(categoryDescriptorDao));
        frame.pack();
        
        frame.setVisible(true);
        
    }

}