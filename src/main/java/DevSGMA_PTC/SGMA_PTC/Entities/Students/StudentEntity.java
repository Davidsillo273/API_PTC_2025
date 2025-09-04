package DevSGMA_PTC.SGMA_PTC.Entities.Students;

import DevSGMA_PTC.SGMA_PTC.Entities.Grades.GradeEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Entity que representa a un estudiante en la base de datos
@Table(name = "TBSTUDENTS") // Nombre de la tabla en la base de datos

// Anotaciones de Lombok para generar getters, setters, toString, equals y hashCode automáticamente
@Getter
@Setter
public class StudentEntity {

//    *** ATRIBUTOS ***\\

    // ID del estudiante, clave primaria generada automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_students")
    @SequenceGenerator(name = "seq_students", sequenceName = "seq_students", allocationSize = 1)
    @Column(name = "STUDENTID")
    private Long studentId;

    // Carnet del estudiante, puede ser nulo y tiene un tamaño máximo de 8 caracteres
    @Column(name = "STUDENTCARD", length = 8, nullable = false, unique = true)
    private String studentCard;

    // Nombres del estudiante, no pueden ser nulo y tiene un tamaño máximo de 50 caracteres
    @Column(name = "FIRSTNAME", length = 50, nullable = false)
    private String firstName;

    // Apellidos del estudiante, no pueden ser nulo y tiene un tamaño máximo de 50 caracteres
    @Column(name = "LASTNAME", length = 50, nullable = false)
    private String lastName;

    // Correo electrónico institucional del estudiante, no puede ser nulo, debe ser único
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    // Contraseña del estudiante, no puede ser nulo
    @Column(name = "PASSWORD", nullable = false)
    private String password;

//*** MANYTOONEs ***\\

    @ManyToOne // Muchos estudiantes pueden tener un mismo año académico
    @JoinColumn(name = "GRADEID", referencedColumnName = "GRADEID") // Columna que conecta con la tabla de tbGrades
    private GradeEntity gradeId; // Año académico del estudiante

//    *** ONETOMANYS ***\\

    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL) // Relación OneToMany con tbVehicles
    @JsonIgnore // Ignorar en la serialización JSON para evitar referencias circulares
    private List<VehicleEntity> vehicle = new ArrayList<>(); // Lista de vehículo asociadas al estudiante

}
