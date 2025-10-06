package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
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
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ModuleEntity {

    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_modules")
    @SequenceGenerator(name = "seq_modules", sequenceName = "seq_modules", allocationSize = 1)
    private Long moduleId;

    @Column(name = "MODULENAME", nullable = false)
    private String moduleName;

    @Column(name = "MODULECODE", length = 20, nullable = false) // Agregar nullable = false si es requerido
    private String moduleCode;

    @ManyToOne
    @JoinColumn(name = "LEVELID", referencedColumnName = "LEVELID")
    private LevelEntity level; // ✅ Nombre corregido

    @ManyToOne
    @JoinColumn(name = "INSTRUCTORID", referencedColumnName = "INSTRUCTORID")
    private InstructorEntity instructor;

    // Método helper para obtener el levelId
    public Long getLevelId() {
        return level != null ? level.getLevelId() : null;
    }

    // Método helper para obtener el instructorId
    public Long getInstructorId() {
        return instructor != null ? instructor.getInstructorId() : null;
    }
}
