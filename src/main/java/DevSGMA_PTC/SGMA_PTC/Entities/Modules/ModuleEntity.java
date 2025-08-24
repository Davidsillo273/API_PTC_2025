package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

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
    @SequenceGenerator(sequenceName = "seq_Modules", name = "seq_Modules", allocationSize = 1)
    @Column(name = "MODULEID")
    private Long id;

    @Column(name = "MODULENAME", nullable = false)
    private String moduleName;
}
