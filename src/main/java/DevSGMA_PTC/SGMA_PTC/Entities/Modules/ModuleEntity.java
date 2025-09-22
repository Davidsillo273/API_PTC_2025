package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// Entity que representa un módulo académico en la base de datos
@Entity
@Table(name = "TBMODULES")
// Anotaciones de Lombok para generar getters, setters, toString y equals/hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ModuleEntity {

    //*** ATRIBUTOS ***\\

    // ID del módulo, clave primaria generada automáticamente
    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_modules")
    @SequenceGenerator(name = "seq_modules", sequenceName = "seq_modules", allocationSize = 1)
    private Long moduleId;

    // Nombre del módulo académico, no puede ser nulo
    @Column(name = "MODULENAME", nullable = false)
    private String moduleName;

    //*** MANYTOONEs ***\\

    @ManyToOne // Muchos módulos pueden estar asociados a un mismo nivel académico
    @JoinColumn(name = "LEVELID", referencedColumnName = "LEVELID") // Columna que conecta con la tabla de tbLevels
    private LevelEntity levelId; // Referencia al nivel académico asociado al módulo

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "moduleId", cascade = CascadeType.ALL) // Relación OneToMany con tbWorkOrders
    @JsonIgnore
    private List<WorkOrderEntity> workOrder = new ArrayList<>(); // Lista de órdenes de trabajo asociadas al módulo
}
