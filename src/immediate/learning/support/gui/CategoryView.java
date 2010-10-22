package immediate.learning.support.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

import immediate.learning.support.entity.Category;


public class CategoryView extends JPanel {
    private static final long serialVersionUID = 1L;

    private Category category;
    private static final String PERSISTENCE_UNIT_NAME = "im";
    private EntityManagerFactory factory;

    CategoryView() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    CategoryView(Long id) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        associateCategory(id);
    }

    public CategoryView(String name) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        associateCategory(name);
        JLabel idLabel = new JLabel("Id:");
        add(idLabel);
        JTextField idField = new JTextField(category.getId().toString());
        add(idField);
        JLabel nameLabel = new JLabel("Name");
        add(nameLabel);
        JTextField nameField = new JTextField(category.getName());
        add(nameField);
    }

    protected Category createCategory() {
        throw new org.apache.commons.lang.NotImplementedException();
        
    }


    protected void associateCategory(String name) {
        EntityManager em = factory.createEntityManager();
        // find out if category already exists or not and use that
        Query q = em.createQuery(String.format("select c from Category c where name='%s'", name));
        @SuppressWarnings("unchecked")
        List<Category> categoryList = q.getResultList();
        if(categoryList.size() != 0) {
            category = categoryList.get(0);
        } else {
            // Create new category
            em.getTransaction().begin();
            category = new Category();
            category.setName(name);
            em.persist(category);
            em.getTransaction().commit();
        
            em.close();
        }
    }

    protected void associateCategory(Long id) {
        EntityManager em = factory.createEntityManager();
        // find out if category already exists or not and use that
        Query q = em.createQuery(String.format("select s from Category s where id=%ld", id));
        @SuppressWarnings("unchecked")
        List<Category> categoryList = q.getResultList();
        if(categoryList.size() != 0) {
            category = categoryList.get(0);
        } else {
            // throw a ecveption here as no category by id provided exists
        }
    }

}