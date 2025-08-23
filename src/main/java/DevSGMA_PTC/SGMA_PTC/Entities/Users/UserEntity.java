package DevSGMA_PTC.SGMA_PTC.Entities.Users;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import lombok.*;

@Entity
@Table(name = "tbUsers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long userId;

    @Column(name = "username", length = 50, nullable = false)
    private String userName;

    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "instiEmail", nullable = false, unique = true)
    private String instiEmail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "grade", length = 25, nullable = false)
    private String grade;

    //*** MANYTOONEs ***\\

    @ManyToOne // Muchos usuarios pueden tener un mismo rol
    @JoinColumn(name = "roleId", nullable = false) // Columna que conecta con la tabla de roles
    private RoleEntity roleId;

    //*** ONETOMANYS ***\\
//
//    @OneToMany(mappedBy = "instructorId", cascade = CascadeType.ALL) // Relación OneToMany con WorkOrderEntity
//    private List<WorkOrderEntity> instructor = new ArrayList<>(); // Lista de órdenes de trabajo asociadas al usuario
}
