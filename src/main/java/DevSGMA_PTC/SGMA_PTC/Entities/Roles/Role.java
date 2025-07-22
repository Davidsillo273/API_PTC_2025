package DevSGMA_PTC.SGMA_PTC.Entities.Roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbRoles")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "roleName", length = 50, nullable = false)
    private String name;
}
