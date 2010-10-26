package immediate.learning.support.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

import java.util.Set;
import java.util.HashSet;


@Entity
public class Concept implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

    @Column(name="NAME", nullable=false)
    private String name;

    @OneToMany(mappedBy="concept")
    private Set<SupportInstance> instances = new HashSet<SupportInstance>();

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

    public Set<SupportInstance> getInstances() {
        return instances;
    }


    public void setInstances(Set<SupportInstance> instance) {
        this.instances = instance;
    }
}