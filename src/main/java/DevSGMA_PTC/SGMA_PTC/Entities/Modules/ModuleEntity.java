package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

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
@Table(name = "TBMODULES")
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Modules")
    @SequenceGenerator(name = "seq_Modules", sequenceName = "seq_Modules", allocationSize = 1)
    @Column(name = "MODULEID")
    private Long id;

    @Column(name = "MODULENAME", nullable = false)
    private String moduleName;

    // Muchos módulos pertenecen a un nivel
    @ManyToOne
    @JoinColumn(name = "LEVELID", nullable = false)
    private LevelEntity level;
}
