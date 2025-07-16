package DevSGMA_PTC.SGMA_PTC.Entities.Users;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "TBUSERS")
@Getter @Setter
public class UserEntity {

    //Atributos
    @Id
    @Column(name = "USERID")
    private Long userId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORDHASH")
    private String passwordHash;

    @Column(name = "FULLNAME")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")
    private RoleEntity role;

}

//    // Esta clase representa la tabla tbUsers de la base de datos
//    @Entity
//    @Table(name = "tbUsers")
//    @Data // Lombok: genera automáticamente getters, setters, equals, hashCode y toString
//    @NoArgsConstructor // Lombok: constructor vacío
//    @AllArgsConstructor // Lombok: constructor con todos los campos
//
//    public class User {
//
//        @Id // Llave primaria
//        @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automáticamente (autoincremental)
//        private Long userId;
//
//        @Column(nullable = false, unique = true)
//        private String username;
//
//        @Column(nullable = false)
//        private String passwordHash;
//
//        @Column(nullable = false)
//        private String fullName;
//
//        @ManyToOne // Muchos usuarios pueden tener un mismo rol
//        @JoinColumn(name = "roleId", nullable = false) // Columna que conecta con la tabla de roles
//        private Role role;
//    }
