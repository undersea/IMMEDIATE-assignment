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
public class SubjectView extends Box {
    private static final long serialVersionUID = 1L;

    private Subject subject;

    @Autowired
    @Qualifier(value = "categoryDao")
    static Dao<Category> categoryDao;

    @Autowired
    @Qualifier(value = "categoryNamesDao")
    static Dao<CategoryNames> cnDao;

    @Autowired
    @Qualifier(value = "categoryDescriptorDao")
    private CategoryDescriptorDao cDao;

    @Autowired
    @Qualifier(value = "subjectDao")
    private SubjectDao subjectDao;

    private ClassPathXmlApplicationContext appContext;

    public SubjectView(String title, 
                       ClassPathXmlApplicationContext appContext) {
        super(BoxLayout.Y_AXIS);
        //setPreferredSize(new java.awt.Dimension(300, 300));
        
        associateDaos(appContext);
        
        associateSubject(title);
        JLabel titleLabel = new JLabel(subject.getTitle());
        add(titleLabel);
        
        add(createCategoryPanel());
        
    }

    public SubjectView(Long id, 
                       ClassPathXmlApplicationContext appContext) {
        super(BoxLayout.Y_AXIS);
        associateDaos(appContext);
        //        setPreferredSize(new java.awt.Dimension(300, 300));
        
        associateSubject(id);
        add(createCategoryPanel());
    }


    private void associateDaos(ClassPathXmlApplicationContext appContext) {
        this.appContext = appContext;
        subjectDao = (SubjectDao)appContext.getBean("subjectDao");;
        categoryDao = (Dao<Category>)appContext.getBean("categoryDao");
        cDao = 
            (CategoryDescriptorDao)appContext.getBean("categoryDescriptorDao");
        cnDao = (Dao<CategoryNames>)appContext.getBean("categoryNamesDao");
    }

    public Box createCategoryPanel() {
        Box panel = new Box(BoxLayout.X_AXIS);
        JList selectedCategory = createActualCategoryList();
        
        selectedCategory.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        selectedCategory.setLayoutOrientation(JList.VERTICAL_WRAP);
        selectedCategory.setVisibleRowCount(10);
        JScrollPane listScroller = new JScrollPane(selectedCategory);
        listScroller.setPreferredSize(new Dimension(250, 80));

        panel.add(listScroller);
        Box bbox = new Box(BoxLayout.Y_AXIS);
        panel.add(bbox);
        JButton addButton = new JButton("Add");
        bbox.add(addButton);
        JButton removeButton = new JButton("Remove");
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

    public JList createActualCategoryList() {
        return new JList(new Vector<Category>(subject.getCategories()));
    }

    public JList createPosibleCategoryList() {
        return new JList(new Vector<CategoryNames>(cnDao.getAll()));
    }

    

    protected void associateSubject(String title) {
        List<Subject> subjectList = subjectDao.findByName(title);
        if(subjectList.size() != 0) {
            subject = subjectList.get(0);
            System.out.println("Old subject row selected" + subjectList.size());
        } else {
            // Create new subject
            subject = new Subject();
            subject.setTitle(title);
            subjectDao.save(subject);
            
            System.out.println("New subject row selected");
        }
    }


    protected void associateSubject(Long id) {
        subject = subjectDao.findById(id);
    }
}
