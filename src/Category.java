package immediate.learning.support;

@Entity(name="CATEGORY")
class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

    @ManyToMany
    @JoinTable(name="SUBJECT_CATEGORY")
    private Set<Subject> subjects = new HashSet<Subject>();


    
}