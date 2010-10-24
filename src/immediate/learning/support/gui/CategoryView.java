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

import immediate.learning.support.dao.Dao;

public class CategoryView extends JPanel implements View<Category> {
    private static final long serialVersionUID = 1L;

    private Category category;
    private Dao<Category> dao;    

    public CategoryView() {
        
    }

    public CategoryView(Long id) {
        
        associateCategory(id);
        
        JLabel idLabel = new JLabel("Id:");
        add(idLabel);
        JTextField idField = new JTextField(category.getId().toString());
        add(idField);
        JLabel nameLabel = new JLabel("Name");
        add(nameLabel);
        JTextField nameField = new JTextField(category.getName());
        add(nameField);
    }


    public void setDao(Dao<Category> dao) {
        this.dao = dao;
    }

    protected Category createCategory() {
        throw new org.apache.commons.lang.NotImplementedException();
        
    }


    protected void associateCategory(Long id) {
        try {
            category = dao.findById(id);
        }catch(Exception e) {}
    }

}