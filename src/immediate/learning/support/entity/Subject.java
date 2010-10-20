package immediate.learning.support.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;


import java.util.HashSet;
import java.util.Set;

@Entity(name="SUBJECT")
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;
    

    @Column(name="TITLE", nullable=false)
    private String title;

    @OneToMany(mappedBy="SUBJECT")
    private Set<Category> categories = new HashSet<Category>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }


    public Set<Category> getCategories() {
        return categories;
    }   
    
}