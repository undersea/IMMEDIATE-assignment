import immediate.learning.support.gui.CategoryView;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {

    public static void main(String[] argv) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("im");
        //CategoryView view = new CategoryView("Pronunciation");
        javax.swing.JFrame frame = new javax.swing.JFrame();
        //frame.getContentPane().add(view);
        frame.pack();
        
        frame.setVisible(true);
    }

}