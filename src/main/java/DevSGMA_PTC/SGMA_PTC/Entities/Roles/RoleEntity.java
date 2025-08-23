package DevSGMA_PTC.SGMA_PTC.Entities.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity  // Entity que representa a un rol en la base de datos
@Table(name = "TBROLES")  // Nombre de la tabla en la base de datos

// Anotaciones de Lombok para generar getters, setters, toString, equals y hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleEntity {

    //*** ATRIBUTOS ***\\

    // ID del rol, clave primaria generada automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLEID")
    @EqualsAndHashCode.Include
    private Long roleId;

    // Nombre del rol, no puede ser nulo y tiene un tamaño máximo de 50 caracteres
    @Column(name = "ROLENAME", length = 50, nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "roleId", cascade = CascadeType.ALL) // Relación OneToMany con WorkOrderEntity
    private List<UserEntity> users = new ArrayList<>(); // Lista de órdenes de trabajo asociadas al usuario

}
