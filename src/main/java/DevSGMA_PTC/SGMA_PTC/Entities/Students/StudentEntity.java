package DevSGMA_PTC.SGMA_PTC.Entities.Students;

import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Entity que representa a un estudiante en la base de datos
@Table(name = "TBSTUDENTS") // Nombre de la tabla en la base de datos

// Anotaciones de Lombok para generar getters, setters, toString, equals y hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StudentEntity {

//    *** ATRIBUTOS ***\\

    // ID del estudiante, clave primaria generada automáticamente
    @Id
    @Column(name = "STUDENTID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long studentId;

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
    @JoinColumn(name = "LEVELID", referencedColumnName = "LEVELID") // Columna que conecta con la tabla de tbLevels
    private LevelEntity levelId;

//    *** ONETOMANYS ***\\

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL) // Relación OneToMany con tbVehicles
    private List<VehicleEntity> vehicle = new ArrayList<>(); // Lista de vehículo asociadas al estudiante

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL) // Relación OneToMany con tbEntries
    private List<EntriesEntity> entries = new ArrayList<>(); // Lista de órdenes de trabajo asociadas al estudiante
}
