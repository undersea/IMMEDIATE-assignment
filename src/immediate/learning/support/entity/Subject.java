package immediate.learning.support.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;


import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;
    

    @Column(name="TITLE", nullable=false, unique=true)
    private String title;

    @OneToMany(mappedBy="subject")
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
    

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
