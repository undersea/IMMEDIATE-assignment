package immediate.learning.support;

@Entity(name="SUPPORT")
class Support implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false)
    private Long id;

}