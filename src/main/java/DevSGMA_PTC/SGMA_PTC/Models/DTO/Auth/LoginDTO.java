package DevSGMA_PTC.SGMA_PTC.Models.DTO.Auth;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
