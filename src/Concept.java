package immediate.learning.support;


@Entity(name="CONCEPT")
class Concept implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

    @OneToMany(mappedBy="CONCEPT")
    private Set<
}