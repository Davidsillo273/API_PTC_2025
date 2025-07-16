package DevSGMA_PTC.SGMA_PTC.Entities.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// Esta clase representa la tabla tbRole de la base de datos
@Entity
@Table(name = "TBROLES")
@ToString
@EqualsAndHashCode
@Getter
@Setter

//    @NoArgsConstructor // Lombok: constructor vacío
//    @AllArgsConstructor // Lombok: constructor con todos los campos
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLEID")
    private Long roleId;


    @Column(name = "ROLENAME")
    private String role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<UserEntity> usuarios = new ArrayList<>();


}
