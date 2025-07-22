package DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.Module;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbModuleOperations")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ModuleOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operationId")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "route", nullable = false, length = 100)
    private String route;

    @ManyToOne
    @JoinColumn(name = "moduleId", nullable = false)
    private Module module;
}
