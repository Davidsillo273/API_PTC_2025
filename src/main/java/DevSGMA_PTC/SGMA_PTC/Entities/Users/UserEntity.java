package DevSGMA_PTC.SGMA_PTC.Entities.Users;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import lombok.*;

@Entity // Entity que representa a un usuario en la base de datos
@Table(name = "TBUSERS") // Nombre de la tabla en la base de datos

// Anotaciones de Lombok para generar getters, setters, toString, equals y hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {

    //*** ATRIBUTOS ***\\

    // ID del usuario, clave primaria generada automáticamente
    @Id
    @Column(name = "USERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long userId;

    // Nombre del usuario, no puede ser nulo y tiene un tamaño máximo de 50 caracteres
    @Column(name = "USERNAME", length = 50, nullable = false)
    private String userName;

    // Apellido del usuario, no puede ser nulo y tiene un tamaño máximo de 50 caracteres
    @Column(name = "LASTNAME", length = 50, nullable = false)
    private String lastName;

    // Correo electrónico institucional del usuario, no puede ser nulo, debe ser único
    @Column(name = "INSTIEMAIL", nullable = false, unique = true)
    private String instiEmail;

    // Contraseña del usuario, no puede ser nulo
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    // Grado del usuario, no puede ser nulo y tiene un tamaño máximo de 25 caracteres
    @Column(name = "GRADE", length = 25, nullable = false)
    private String grade;

    // URL de la imagen del usuario, no puede ser nulo
    @Column(name = "IMAGENURL", nullable = false)
    private String imagenUrl;

    //*** MANYTOONEs ***\\

    @ManyToOne // Muchos usuarios pueden tener un mismo rol
    @JoinColumn(name = "roleId", nullable = false) // Columna que conecta con la tabla de roles
    private RoleEntity roleId;

    //*** ONETOMANYS ***\\
//
//    @OneToMany(mappedBy = "instructorId", cascade = CascadeType.ALL) // Relación OneToMany con WorkOrderEntity
//    private List<WorkOrderEntity> instructor = new ArrayList<>(); // Lista de órdenes de trabajo asociadas al usuario
}
