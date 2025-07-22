package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbModules")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moduleId")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "moduleName", length = 50, nullable = false)
    private String name;


}
