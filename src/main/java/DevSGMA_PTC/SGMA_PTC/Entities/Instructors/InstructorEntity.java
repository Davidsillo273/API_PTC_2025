package DevSGMA_PTC.SGMA_PTC.Entities.Instructors;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// Entity que representa a un instructor en la base de datos
@Entity
@Table(name = "TBINSTRUCTORS")

// Anotaciones de Lombok para generar getters, setters, toString, equals y hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InstructorEntity {

    //*** ATRIBUTOS ***\\

    // ID del instructor, clave primaria generada automáticamente
    @Id
    @Column(name = "INSTRUCTORID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long instructorId;

    // Nombres del instructor, no pueden ser nulo y tiene un tamaño máximo de 50 caracteres
    @Column(name = "FIRSTNAME", length = 50, nullable = false)
    private String firstName;

    // Apellidos del instructor, no pueden ser nulo y tiene un tamaño máximo de 50 caracteres
    @Column(name = "LASTNAME", length = 50, nullable = false)
    private String lastName;

    // Correo electrónico institucional del instructor, no puede ser nulo, debe ser único
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    // Contraseña del instructor, no puede ser nulo
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "INSTRUCTORIMAGE", nullable = false)
    private String instructorImage; // Campo para almacenar la imagen del instructor (URL)

    //*** MANYTOONEs ***\\

    @ManyToOne // Muchos instructores pueden tener un mismo año académico
    @JoinColumn(name = "LEVELID", referencedColumnName = "LEVELID") // Columna que conecta con la tabla de tbLevels
    private LevelEntity levelId;

    @ManyToOne // Muchos instructores pueden tener un mismo rol
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID") // Columna que conecta con la tabla de tbRoles
    private RoleEntity roleId;

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL) // Relación OneToMany con tbVehicles
    private List<VehicleEntity> vehicle = new ArrayList<>(); // Lista de vehículos asociadas al instructor

}
