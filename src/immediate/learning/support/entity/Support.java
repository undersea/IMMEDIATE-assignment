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


@Entity(name="SUPPORT")
class Support implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

    @OneToMany(mappedBy="CONCEPT")
    private Set<SupportInstance> instances = new HashSet<SupportInstance>();

    @Column(name="component", nullable=false)
    private String component;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<SupportInstance> getInstances() {
        return instances;
    }

    
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
    
}