import immediate.learning.support.gui.SubjectView;
import immediate.learning.support.gui.CategoryView;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import immediate.learning.support.dao.SubjectDao;
import immediate.learning.support.dao.Dao;
import immediate.learning.support.entity.Category;

public class Main {
    @Autowired
    @Qualifier(value = "categoryDao")
    static Dao<Category> categoryDao;

    @SuppressWarnings("unchecked")
    public static void main(String[] argv) {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("im");
        SubjectDao subjectDao;
        

        ClassPathXmlApplicationContext appContext = 
            new ClassPathXmlApplicationContext(new String[] {
                    "applicationContext.xml"
                });

        subjectDao = (SubjectDao)appContext.getBean("subjectDao");
        categoryDao = (Dao<Category>)appContext.getBean("categoryDao");
        
        SubjectView view = new SubjectView("Calculus I", subjectDao);
        System.out.println("CategoryDao: " + categoryDao.getType());
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.getContentPane().add(view);
        frame.pack();
        
        frame.setVisible(true);
    }

}