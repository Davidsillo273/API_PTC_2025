package DevSGMA_PTC.SGMA_PTC.Entities.Roles;

import jakarta.persistence.*;

@Entity
@Table(name = "tbRoles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Long id;

    @Column(name = "roleName", length = 50, nullable = false)
    private String name;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
