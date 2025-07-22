package DevSGMA_PTC.SGMA_PTC.Models.DTO.Users;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String lastName;
    private String email;
    private String teacherGrade;
    private RoleDTO role;
}
