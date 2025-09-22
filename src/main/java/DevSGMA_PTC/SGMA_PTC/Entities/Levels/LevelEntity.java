package DevSGMA_PTC.SGMA_PTC.Entities.Levels;

import DevSGMA_PTC.SGMA_PTC.Entities.Grades.GradeEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// Entity que representa un nivel académico en la base de datos
@Entity
@Table(name = "TBLEVELS")
// Anotaciones de Lombok para generar getters, setters, toString y equals/hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LevelEntity {

    //*** ATRIBUTOS ***\\

    // ID del nivel, clave primaria generada automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_levels")
    @SequenceGenerator(name = "seq_levels", sequenceName = "seq_levels", allocationSize = 1)
    @Column(name = "LEVELID")
    private Long levelId;

    // Nombre del nivel académico, no puede ser nulo
    @Column(name = "LEVELNAME", nullable = false)
    private String levelName;

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "levelId", cascade = CascadeType.ALL) // Relación OneToMany con tbGrades
    private List<GradeEntity> grade = new ArrayList<>(); // Lista de grados asociados al nivel

    @OneToMany(mappedBy = "levelId", cascade = CascadeType.ALL) // Relación OneToMany con tbInstructors
    private List<InstructorEntity> instructor = new ArrayList<>(); // Lista de instructores asociados al nivel

    @OneToMany(mappedBy = "levelId", cascade = CascadeType.ALL) // Relación OneToMany con tbModules
    private List<ModuleEntity> module = new ArrayList<>(); // Lista de módulos asociados al nivel

}
