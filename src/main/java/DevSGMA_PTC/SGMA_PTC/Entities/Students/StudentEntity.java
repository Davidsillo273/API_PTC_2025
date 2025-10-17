package DevSGMA_PTC.SGMA_PTC.Entities.Students;

import DevSGMA_PTC.SGMA_PTC.Entities.Grades.GradeEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Getter // Lombok: genera automáticamente los métodos getter
@Setter // Lombok: genera automáticamente los métodos setter
@EqualsAndHashCode // Lombok: genera automáticamente equals y hashCode
@Table(name = "TBSTUDENTS") // Especifica el nombre de la tabla en la base de datos
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

    @ManyToOne(fetch = FetchType.LAZY) // Muchos estudiantes pueden tener un mismo año académico
    @JoinColumn(name = "GRADEID", referencedColumnName = "GRADEID") // Columna que conecta con la tabla de tbGrades
    private GradeEntity gradeId; // Año académico del estudiante

    // Nueva relación ManyToOne con RoleEntity (tbRoles)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")
    private RoleEntity roleId;

//    *** ONETOMANYS ***\\

    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación OneToMany con tbVehicles
    @JsonIgnore // Ignorar en la serialización JSON para evitar referencias circulares
    private List<VehicleEntity> vehicle = new ArrayList<>(); // Lista de vehículo asociadas al estudiante

    @Override
    public String toString() {
        return "StudentEntity{" +
                "studentId=" + studentId +
                ", studentCard='" + studentCard + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gradeId=" + gradeId +
                ", roleId=" + roleId +
                ", vehicle=" + vehicle +
                '}';
    }
}
