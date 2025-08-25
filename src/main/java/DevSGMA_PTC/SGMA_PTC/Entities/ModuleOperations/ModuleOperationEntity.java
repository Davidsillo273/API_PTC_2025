package DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations;

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
@Table(name = "tbModuleOperations")
public class ModuleOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ModuleOperations")
    @SequenceGenerator(sequenceName = "seq_ModuleOperations", name = "seq_ModuleOperations", allocationSize = 1)
    @Column(name = "operationId")
    private Long id;

    @Column(name = "moduleId")
    private Long moduleId;

    @Column(name = "operationName")
    private String operationName;
}
