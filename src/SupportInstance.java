package immediate.learning.support;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name="SUPPORTINSTANCE")
class SupportInstance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;
    

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID", nullable=false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="CONCEPT_ID", nullable=false)
    private Concept concept;

    @ManyToOne
    @JoinColumn(name="SUPPORT_ID", nullable=false)
    private Support support;    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Support getCategory() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}