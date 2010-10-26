import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import java.awt.Dimension;

import java.util.Vector;
import java.util.Enumeration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import immediate.learning.support.dao.SubjectDao;
import immediate.learning.support.dao.Dao;
import immediate.learning.support.dao.CategoryDescriptorDao;
import immediate.learning.support.entity.Category;
import immediate.learning.support.entity.CategoryDescriptor;
import immediate.learning.support.component.Component;

public class MakeCategory extends Box implements ActionListener {
    JTextField categoryField = new JTextField(10);
    String componentPackage = "immediate.learning.support.component";
    String[] components = {"ExplainMore","Player","Question", "Recorder"};
    JButton addButton;
    JButton removeButton;
    JButton okButton = new JButton("Ok");
    JButton cancelButton = new JButton("Cancel");
    //JList model;
    JList selectedCategory;
    JList possibleCategory;
    @Autowired
    @Qualifier(value = "categoryDescriptorDao")
    CategoryDescriptorDao cDao;

    public MakeCategory(CategoryDescriptorDao cDao) {
        super(BoxLayout.Y_AXIS);
        this.cDao = cDao;
        add(new JLabel("Category name"));
        add(categoryField);
        Box cBox = new Box(BoxLayout.X_AXIS);
        add(cBox);
        DefaultListModel model = new DefaultListModel();
        
        selectedCategory = new JList(model);
        selectedCategory.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        selectedCategory.setLayoutOrientation(JList.VERTICAL_WRAP);
        selectedCategory.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(selectedCategory);
        listScroller.setPreferredSize(new Dimension(250, 80));
        cBox.add(listScroller);

        Box bbox = new Box(BoxLayout.Y_AXIS);
        cBox.add(bbox);
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        bbox.add(addButton);
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        bbox.add(removeButton);
        possibleCategory = new JList(components);
        

        possibleCategory.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        possibleCategory.setLayoutOrientation(JList.VERTICAL_WRAP);
        possibleCategory.setVisibleRowCount(10);
        JScrollPane listScroller2 = new JScrollPane(possibleCategory);
        listScroller2.setPreferredSize(new Dimension(250, 80));
        cBox.add(listScroller2);
        Box bBox2 = new Box(BoxLayout.X_AXIS);
        add(bBox2);
        bBox2.add(okButton);
        okButton.addActionListener(this);
        bBox2.add(cancelButton);
        cancelButton.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == addButton) {
            System.out.println("possible model = " + possibleCategory.getModel());
            for(Object item : possibleCategory.getSelectedValues()) {
                DefaultListModel model = ((DefaultListModel)(selectedCategory.getModel()));
                model.addElement(item.toString());
            }
        } else if(src == removeButton) {
            for(Object item : selectedCategory.getSelectedValues()) {
                ((DefaultListModel)(selectedCategory.getModel())).removeElement(item);
            }
        } else if(src == okButton) {
            String name = categoryField.getText();
            DefaultListModel model = 
                ((DefaultListModel)(selectedCategory.getModel()));
            Enumeration<Object> elems = (Enumeration<Object>)model.elements();
            while(elems.hasMoreElements()) {
                Object item = elems.nextElement();
                CategoryDescriptor desc = new CategoryDescriptor();
                desc.setCategory(name);
                try {
                    String classStr = String.format("%s.%s", componentPackage, item.toString());
                    Component component = (Component)Class.forName(classStr).newInstance();
                    desc.setComponent(classStr);
                    cDao.save(desc);
                    cDao.close();
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                
            }

            categoryField.setText("");
            ((DefaultListModel)selectedCategory.getModel()).clear();
        } else if(src == cancelButton) {
            categoryField.setText("");
            ((DefaultListModel)selectedCategory.getModel()).clear();
        }
    }
}