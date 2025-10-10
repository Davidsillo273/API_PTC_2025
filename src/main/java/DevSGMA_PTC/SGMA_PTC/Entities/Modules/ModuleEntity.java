package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Getter // Lombok: genera automáticamente los métodos getter
@Setter // Lombok: genera automáticamente los métodos setter
@EqualsAndHashCode // Lombok: genera automáticamente equals y hashCode
@Table(name = "TBMODULES") // Especifica el nombre de la tabla en la base de datos
public class ModuleEntity {

    //*** ATRIBUTOS ***\\

    // ID del módulo, clave primaria generada automáticamente
    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_modules")
    @SequenceGenerator(name = "seq_modules", sequenceName = "seq_modules", allocationSize = 1)
    private Long moduleId;

    // Nombre del módulo académico, no puede ser nulo
    @Column(name = "MODULENAME", nullable = false, length = 500) // Cambiado a 500 caracteres
    private String moduleName;

    // Nuevo campo: Código corto del módulo
    @Column(name = "MODULECODE", nullable = false, length = 20) // NOT NULL
    private String moduleCode;

    //*** MANYTOONEs ***\\

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEVELID", referencedColumnName = "LEVELID") // Columna que conecta con la tabla de tbLevels
    private LevelEntity levelId; // Referencia al nivel académico asociado al módulo

    // Relación con Instructor (clave foránea)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTRUCTORID", referencedColumnName = "INSTRUCTORID")
    private InstructorEntity instructor;

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "moduleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación OneToMany con tbWorkOrders
    @JsonIgnore
    private List<WorkOrderEntity> workOrder = new ArrayList<>(); // Lista de órdenes de trabajo asociadas al módulo

    @Override
    public String toString() {
        return "ModuleEntity{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", moduleCode='" + moduleCode + '\'' +
                ", levelId=" + levelId +
                ", instructor=" + instructor +
                ", workOrder=" + workOrder +
                '}';
    }
}
