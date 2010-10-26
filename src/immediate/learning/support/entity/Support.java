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


@Entity
public class Support implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

    @OneToMany(mappedBy="support")
    private Set<SupportInstance> instances = new HashSet<SupportInstance>();

    @Column(name="component", nullable=false)
    private String component;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<SupportInstance> getInstance() {
        return instances;
    }

    public void setInstance(Set<SupportInstance> instance) {
        this.instances = instance;
    }

    
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
    
}