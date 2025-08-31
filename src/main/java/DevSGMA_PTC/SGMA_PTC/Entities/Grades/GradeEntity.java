package DevSGMA_PTC.SGMA_PTC.Entities.Grades;

import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBGRADES")
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Grades")
    @SequenceGenerator(name = "seq_Grades", sequenceName = "seq_Grades", allocationSize = 1)
    @Column(name = "GRADEID")
    private Long id;

    @Column(name = "GROUPNAME", nullable = false)
    private String groupName;

    // Muchos grados pertenecen a un nivel
    @ManyToOne
    @JoinColumn(name = "LEVELID", nullable = false)
    private LevelEntity level;
}
