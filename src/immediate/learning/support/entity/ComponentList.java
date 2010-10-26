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
public class ComponentList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;


    @Column(name="COMPONENT")
    private Component component;

    
    public Component getComponent() {
        return component;
    }


    public void setComponent(Component component) {
        this.component = component;
    }
}