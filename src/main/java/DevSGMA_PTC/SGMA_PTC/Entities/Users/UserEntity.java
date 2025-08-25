package DevSGMA_PTC.SGMA_PTC.Entities.Users;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrder.WorkOrderEntity;
import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbUsers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {

    @Id
    @Column(name = "USERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long userId;

    @Column(name = "USERNAME", length = 50, nullable = false)
    private String userName;

    @Column(name = "LASTNAME", length = 50, nullable = false)
    private String lastName;

    @Column(name = "INSTIEMAIL", nullable = false, unique = true)
    private String instiEmail;

    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;

    @Column(name = "GRADE", length = 25, nullable = false)
    private String grade;

    @ManyToOne // Muchos usuarios pueden tener un mismo rol
    @JoinColumn(name = "ROLEID", nullable = false) // Columna que conecta con la tabla de roles
    private RoleEntity roleId;




}
