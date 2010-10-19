package immediate.learning.support;


@Entity(name="SUBJECT")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;
    

    @Column(name="TITLE" nullable=false)
    private String title;

    @OneToMany(mappedBy="SUBJECT")
    private Set<Category> categories = new HashSet<Category>()
    
}