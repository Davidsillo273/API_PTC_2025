package DevSGMA_PTC.SGMA_PTC.Models.DTO.Users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long userId;
    private String email;
    private String username;
    private String passwordHash;
    private String fullName;
    private Long roleId;
}
