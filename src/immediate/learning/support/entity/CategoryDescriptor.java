package immediate.learning.support.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import java.util.Set;
import java.util.HashSet;


import immediate.learning.support.component.Component;



@Entity
public class CategoryDescriptor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

    @Column(name="CATEGORY", nullable=false)
    private String category;

    //Will be a string representation of a class
    @Column(name="COMPONENT")
    private String component;


    @ManyToOne
    private CategoryNames name;

    
    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getComponent() {
        return component;
    }


    public void setComponent(String component) {
        this.component = component;
    }


    public CategoryNames getName() {
        return name;
    }
}
