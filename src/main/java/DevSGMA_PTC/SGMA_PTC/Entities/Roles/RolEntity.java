package DevSGMA_PTC.SGMA_PTC.Entities.Roles;
import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// Esta clase representa la tabla tbRole de la base de datos
@Entity
@Table(name = "tbRoles")
@ToString
@EqualsAndHashCode
@Getter @Setter

//    @NoArgsConstructor // Lombok: constructor vacío
//    @AllArgsConstructor // Lombok: constructor con todos los campos
public class RolEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
        @SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1)
        @Column(name = "roleId")
        private Long roleId;

        @Column(name = "roleName")
        private String roleName;

        @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
        private List<UserEntity> usuarios = new ArrayList<>();


}
