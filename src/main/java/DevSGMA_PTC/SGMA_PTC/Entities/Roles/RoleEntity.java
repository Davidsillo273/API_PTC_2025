package DevSGMA_PTC.SGMA_PTC.Entities.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbRoles")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    @EqualsAndHashCode.Include
    private Long roleId;

    @Column(name = "roleName", length = 50, nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "roleId", cascade = CascadeType.ALL)
    private List<UserEntity> users = new ArrayList<>();

}
