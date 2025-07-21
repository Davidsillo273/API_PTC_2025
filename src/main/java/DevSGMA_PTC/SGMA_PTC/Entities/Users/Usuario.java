package DevSGMA_PTC.SGMA_PTC.Entities.Users;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.Role;

@Entity
@Table(name = "tbUsers")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
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

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getInstiEmail() { return instiEmail; }
    public void setInstiEmail(String instiEmail) { this.instiEmail = instiEmail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTeacherGrade() { return teacherGrade; }
    public void setTeacherGrade(String teacherGrade) { this.teacherGrade = teacherGrade; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
