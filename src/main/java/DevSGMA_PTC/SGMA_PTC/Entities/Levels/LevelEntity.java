package DevSGMA_PTC.SGMA_PTC.Entities.Levels;

import DevSGMA_PTC.SGMA_PTC.Entities.Grades.GradeEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBLEVELS")
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Levels")
    @SequenceGenerator(name = "seq_Levels", sequenceName = "seq_Levels", allocationSize = 1)
    @Column(name = "LEVELID")
    private Long id;

    @Column(name = "LEVELNAME", nullable = false)
    private String levelName;

    // Un nivel puede tener muchos grados
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
    private List<GradeEntity> grades;

    // Un nivel puede tener muchos módulos
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
    private List<ModuleEntity> modules;
}
