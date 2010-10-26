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
public class CategoryNames implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

    @Column(name="NAME", nullable=false, unique=true)
    private String name;

    @OneToMany(mappedBy="name")
    private Set<CategoryDescriptor> descriptors = new HashSet<CategoryDescriptor>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public Set<CategoryDescriptor> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(Set<CategoryDescriptor> descriptors) {
        this.descriptors = descriptors;
    }
}