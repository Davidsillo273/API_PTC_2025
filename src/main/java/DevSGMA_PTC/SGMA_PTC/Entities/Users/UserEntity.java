package DevSGMA_PTC.SGMA_PTC.Entities.Users;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "tbUser")
@Getter
@Setter
@ToString
@EqualsAndHashCode
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column (name = "username", nullable = false, length = 50)
    private String username;

    @Column (name = "passwordHash", nullable = false, length = 225)
    private String passwordHash;

    @Column (name = "fullName", nullable = false, length = 100)
    private String fullName;


}


