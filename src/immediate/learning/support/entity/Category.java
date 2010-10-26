package immediate.learning.support.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import java.util.Set;
import java.util.HashSet;

@Entity
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy="category")
    private Set<SupportInstance> instances = new HashSet<SupportInstance>();
    
    
    @Column(name="NAME", nullable=false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<SupportInstance> getInstances() {
        return instances;
    }

    public void setInstances(Set<SupportInstance> instance) {
        this.instances = instance;
    }

    public Subject getSubject() {
        return subject;
    }


    public void setSubject(Subject subject) {
        this.subject = subject;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    
}