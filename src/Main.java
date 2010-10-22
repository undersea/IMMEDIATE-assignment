import immediate.learning.support.gui.CategoryView;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import immediate.learning.support.dao.SubjectDao;

public class Main {

    public static void main(String[] argv) {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("im");
        SubjectDao subjectDao;

        ClassPathXmlApplicationContext appContext = 
            new ClassPathXmlApplicationContext(new String[] {
                    "applicationContext.xml"
                });

        subjectDao = (SubjectDao)appContext.getBean("subjectDao");

        //CategoryView view = new CategoryView("Pronunciation");
        javax.swing.JFrame frame = new javax.swing.JFrame();
        //frame.getContentPane().add(view);
        frame.pack();
        
        frame.setVisible(true);
    }

}