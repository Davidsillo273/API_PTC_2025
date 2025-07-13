package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String fullName;
    private Long roleId;
    private String roleName;
}
