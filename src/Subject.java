package immediate.learning.support;


@Entity(name="SUBJECT")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;
    

    @Column(name="TITLE" nullable=false)
    private String title;

    @ManyToMany
    @JoinTable(name="SUBJECT_CATEGORY")
    private Set<Category> categories = new HashSet<Category>()
    
}