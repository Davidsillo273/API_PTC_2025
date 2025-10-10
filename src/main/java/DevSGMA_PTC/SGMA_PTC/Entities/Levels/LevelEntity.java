package DevSGMA_PTC.SGMA_PTC.Entities.Levels;

import DevSGMA_PTC.SGMA_PTC.Entities.Grades.GradeEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
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
@Table(name = "TBLEVELS") // Especifica el nombre de la tabla en la base de datos
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

    @OneToMany(mappedBy = "levelId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación OneToMany con tbGrades
    private List<GradeEntity> grade = new ArrayList<>(); // Lista de grados asociados al nivel

    @OneToMany(mappedBy = "levelId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación OneToMany con tbInstructors
    private List<InstructorEntity> instructor = new ArrayList<>(); // Lista de instructores asociados al nivel

    @OneToMany(mappedBy = "levelId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación OneToMany con tbModules
    private List<ModuleEntity> module = new ArrayList<>(); // Lista de módulos asociados al nivel

    @Override
    public String toString() {
        return "LevelEntity{" +
                "levelId=" + levelId +
                ", levelName='" + levelName + '\'' +
                ", grade=" + grade +
                ", instructor=" + instructor +
                ", module=" + module +
                '}';
    }
}
