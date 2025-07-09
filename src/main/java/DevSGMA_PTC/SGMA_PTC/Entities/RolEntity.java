package DevSGMA_PTC.SGMA_PTC.Entities;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "tbRoles")
@Getter
@Setter
@ToString
@EqualsAndHashCode
class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;


    @Column(name = "roleName", nullable = false, length = 50)
    private String roleName;
    public RolEntity() {
    }


    public Long getRoleId() {
        return roleId;
    }


    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }


    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

}