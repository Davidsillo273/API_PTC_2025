package DevSGMA_PTC.SGMA_PTC.Entities.Users;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbUsers")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "lastName", length = 50, nullable = false, unique = true)
    private String lastName;

    @Column(name = "instiEmail", length = 100, nullable = false, unique = true)
    private String instiEmail;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "teacherGrade", length = 25, nullable = false)
    private String teacherGrade;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}
