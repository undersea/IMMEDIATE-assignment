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

import immediate.learning.support.entity.Concept;
import immediate.learning.support.entity.Category;
import immediate.learning.support.entity.Support;
import immediate.learning.support.entity.SupportInstance;
import immediate.learning.support.entity.CategoryNames;
import immediate.learning.support.entity.CategoryDescriptor;
import immediate.learning.support.dao.SubjectDao;
import immediate.learning.support.dao.Dao;
import immediate.learning.support.dao.CategoryDescriptorDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*the only view class not to implement View due to it's specialised needs*/
@SuppressWarnings("unchecked")
public class ConceptView extends Box {
    private static final long serialVersionUID = 1L;

    private Concept concept;

    private Category category;

    @Autowired
    @Qualifier(value = "categoryDao")
    Dao<Category> categoryDao;

    @Autowired
    @Qualifier(value = "conceptDao")
    Dao<Concept> cnDao;

    @Autowired
    @Qualifier(value = "categoryDescriptorDao")
    private CategoryDescriptorDao cDao;

    @Autowired
    @Qualifier(value = "subjectDao")
    private SubjectDao subjectDao;

    @Autowired
    @Qualifier(value = "supportDao")
    Dao<Support> supportDao;


    @Autowired
    @Qualifier(value="supportInstanceDao")
    Dao<SupportInstance> supportInstanceDao;

   

     public ConceptView(Category category, 
                        ClassPathXmlApplicationContext appContext) {
         super(BoxLayout.Y_AXIS);
         setPreferredSize(new java.awt.Dimension(300, 300));
        

         System.out.println("ConceptView");
         associateDaos(appContext);
        
         
         //associateConcept("");
         concept = new Concept();
         cnDao.save(concept);
         this.category = category;
                
         JLabel titleLabel = new JLabel(String.format("Concept: %s", concept.getName()));
         add(titleLabel);
        
         add(createComponentPanel());
        
    }

    public ConceptView(String title, 
                       ClassPathXmlApplicationContext appContext) {
        super(BoxLayout.Y_AXIS);
        setPreferredSize(new java.awt.Dimension(300, 300));
        
        associateDaos(appContext);
        
        associateConcept(title);
                
        JLabel titleLabel = new JLabel(String.format("Concept: %s", concept.getName()));
        add(titleLabel);
        
        add(createComponentPanel());
        
    }

    public ConceptView(Long id, 
                       ClassPathXmlApplicationContext appContext) {
        super(BoxLayout.Y_AXIS);
        associateDaos(appContext);
        setPreferredSize(new java.awt.Dimension(300, 300));
        
        associateConcept(id);
        JLabel titleLabel = new JLabel(String.format("Concept: %s", concept.getName()));
        add(titleLabel);
        add(createComponentPanel());
        
    }


    private void associateDaos(ClassPathXmlApplicationContext appContext) {
        subjectDao = (SubjectDao)appContext.getBean("subjectDao");;
        categoryDao = (Dao<Category>)appContext.getBean("categoryDao");
        cDao = 
            (CategoryDescriptorDao)appContext.getBean("categoryDescriptorDao");
        cnDao = (Dao<Concept>)appContext.getBean("conceptDao");
        supportDao = (Dao<Support>)appContext.getBean("supportDao");
        supportInstanceDao = 
            (Dao<SupportInstance>)appContext.getBean("supportInstanceDao");
        
    }

    public Box createComponentPanel() {
        Box panel = new Box(BoxLayout.X_AXIS);
        JList selectedCategory = createActualComponentList();
        
        selectedCategory.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        selectedCategory.setLayoutOrientation(JList.VERTICAL_WRAP);
        selectedCategory.setVisibleRowCount(10);
        JScrollPane listScroller = new JScrollPane(selectedCategory);
        listScroller.setPreferredSize(new Dimension(250, 80));

        panel.add(listScroller);
        Box bbox = new Box(BoxLayout.Y_AXIS);
        panel.add(bbox);
        JButton addButton = new JButton("<");
        bbox.add(addButton);
        JButton removeButton = new JButton(">");
        bbox.add(removeButton);
        JList possibleCategory = createPosibleCategoryList();
        
        possibleCategory.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        possibleCategory.setLayoutOrientation(JList.VERTICAL_WRAP);
        possibleCategory.setVisibleRowCount(10);
        JScrollPane listScroller2 = new JScrollPane(possibleCategory);
        listScroller2.setPreferredSize(new Dimension(250, 80));
        panel.add(listScroller2);
        
        return panel;
    }

    public JList createActualComponentList() {
        JList list;
        List<SupportInstance> supportList = 
            supportInstanceDao.find(String.format("t.concept.id = %d", 
                                                  concept.getId()));
        if(supportList.size() > 0) {
            Vector<Support> vect = new Vector<Support>();
            for(SupportInstance inst : supportList) {
                vect.add(inst.getSupport());
            }
            list = new JList(vect);
        } else {
            list = new JList();
        }

        return list;
    }


    public JList createPosibleCategoryList() {
        Vector<String> tmp = new Vector<String>();
        try {
            List<CategoryDescriptor> descrlist = 
                cDao.find(String.format("t.category like '%s'", category.getName()));
            
            for(CategoryDescriptor d : descrlist) {
                tmp.add(d.getComponent());
            }
        }catch(Exception e) {}

        return new JList(tmp);
    }
    

    
    protected void associateConcept(String name) {
        concept = new Concept();
        concept.setName(name);
        cnDao.save(concept);
    }

    protected void associateConcept(Long id) {
        concept = cnDao.findById(id);
    }
}