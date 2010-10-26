package immediate.learning.support.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;


import java.util.List;
import java.util.Vector;

import immediate.learning.support.entity.Subject;
import immediate.learning.support.entity.Category;
import immediate.learning.support.entity.Concept;
import immediate.learning.support.entity.Support;
import immediate.learning.support.entity.CategoryNames;
import immediate.learning.support.entity.SupportInstance;
import immediate.learning.support.entity.CategoryDescriptor;
import immediate.learning.support.dao.SubjectDao;
import immediate.learning.support.dao.Dao;
import immediate.learning.support.dao.CategoryDescriptorDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*the only view class not to implement View due to it's specialised needs*/
@SuppressWarnings("unchecked")
public class CategoryView extends Box {
    private static final long serialVersionUID = 1L;

    private Category category;

    @Autowired
    @Qualifier(value = "categoryDao")
    Dao<Category> categoryDao;

    @Autowired
    @Qualifier(value = "conceptDao")
    Dao<Concept> conceptDao;

    @Autowired
    @Qualifier(value = "supportInstanceDao")
    Dao<SupportInstance> cnDao;

    @Autowired
    @Qualifier(value = "categoryDescriptorDao")
    private CategoryDescriptorDao cDao;

    @Autowired
    @Qualifier(value = "subjectDao")
    private SubjectDao subjectDao;

    private ClassPathXmlApplicationContext appContext;

    public CategoryView(String title, 
                        ClassPathXmlApplicationContext appContext) {
        super(BoxLayout.Y_AXIS);
        //setPreferredSize(new java.awt.Dimension(300, 300));
        
        associateDaos(appContext);
        
        associateCategory(title);

        JTextField titleField = new JTextField(String.format("Category: %s", category.getName()));
        add(titleField);
        
        
    }

    public CategoryView(Long id, 
                        ClassPathXmlApplicationContext appContext) {
        super(BoxLayout.Y_AXIS);
        associateDaos(appContext);
        setPreferredSize(new java.awt.Dimension(300, 300));
        
        associateCategory(id);
        JTextField titleField = new JTextField(String.format("Category: %s", category.getName()));
        add(titleField);
    }


    private void associateDaos(ClassPathXmlApplicationContext appContext) {
        this.appContext = appContext;
        subjectDao = (SubjectDao)appContext.getBean("subjectDao");;
        categoryDao = (Dao<Category>)appContext.getBean("categoryDao");
        cDao = 
            (CategoryDescriptorDao)appContext.getBean("categoryDescriptorDao");
        cnDao = (Dao<SupportInstance>)appContext.getBean("supportInstanceDao");
        conceptDao = (Dao<Concept>)appContext.getBean("conceptDao");
    }

    public Box createConceptPanel() {
        Box panel = new Box(BoxLayout.X_AXIS);
        
        JList possibleCategory = createConceptList();
        
        possibleCategory.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        possibleCategory.setLayoutOrientation(JList.VERTICAL_WRAP);
        possibleCategory.setVisibleRowCount(10);
        JScrollPane listScroller2 = new JScrollPane(possibleCategory);
        listScroller2.setPreferredSize(new Dimension(250, 80));
        panel.add(listScroller2);

        JButton addButton = new JButton("Add");
        panel.add(addButton);
        JTextField addField = new JTextField(20);
        panel.add(addField);
        return panel;
    }


    protected void associateCategory(String title) {

        // Create new subject
        category = new Category();
        category.setName(title);
        categoryDao.save(category);
            
        
        
    }


    protected void associateCategory(Long id) {
        category = categoryDao.findById(id);
    }


    protected JList createConceptList() {
        List<Concept> concepts = 
            conceptDao.find(String.format("t.instances.category.id = %d", category.getId()));
        JList list = new JList(new Vector(concepts));
        return list;
    }
    
}
