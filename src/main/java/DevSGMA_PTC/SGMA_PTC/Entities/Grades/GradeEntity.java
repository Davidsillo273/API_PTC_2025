package DevSGMA_PTC.SGMA_PTC.Entities.Grades;

import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Getter // Lombok: genera automáticamente los métodos getter
@Setter // Lombok: genera automáticamente los métodos setter
@ToString // Lombok: genera automáticamente el método toString
@EqualsAndHashCode // Lombok: genera automáticamente equals y hashCode
@Table(name = "TBGRADES") // Especifica el nombre de la tabla en la base de datos
public class GradeEntity {

    //*** ATRIBUTOS ***\\

    @Id // Indica que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Incluye este campo en los métodos equals y hashCode generados por Lombok
    @Column(name = "GRADEID") // Mapea el campo id con la columna GRADEID de la tabla
    private Long gradeId; // Identificador único del grado

    @ManyToOne // Relación muchos a uno: varios grados pueden estar asociados a un mismo nivel académico
    @JoinColumn(name = "LEVELID", referencedColumnName = "LEVELID") // Columna que conecta con la tabla de tbLevels
    private LevelEntity levelId; // Referencia al nivel académico asociado al grado

    @Column(name = "GRADEGROUP", nullable = false) // Mapea el campo gradeGroup con la columna GRADEGROUP de la tabla, no puede ser nulo
    private Long gradeGroup; // Número de grupo dentro del grado

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "gradeId", cascade = CascadeType.ALL) // Relación OneToMany con tbStudents
    @JsonIgnore
    private List<StudentEntity> student = new ArrayList<>(); // Grado asociado al estudiante

}
