package DevSGMA_PTC.SGMA_PTC.Entities.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Getter // Lombok: genera automáticamente los métodos getter
@Setter // Lombok: genera automáticamente los métodos setter
@EqualsAndHashCode // Lombok: genera automáticamente equals y hashCode
@Table(name = "TBROLES") // Especifica el nombre de la tabla en la base de datos
public class RoleEntity {

    //*** ATRIBUTOS ***\\

    // ID del rol, clave primaria generada automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_roles")
    @SequenceGenerator(name = "seq_roles", sequenceName = "seq_roles", allocationSize = 1)
    @Column(name = "ROLEID")
    private Long roleId;

    // Nombre del rol, no puede ser nulo y tiene un tamaño máximo de 50 caracteres
    @Column(name = "ROLENAME", length = 50, nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "roleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación OneToMany con tbInstructor
    @JsonIgnore // Ignorar en la serialización JSON para evitar referencias circulares
    private List<InstructorEntity> instructor = new ArrayList<>(); // Lista de órdenes de trabajo asociadas al Instructor

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
