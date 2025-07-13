package DevSGMA_PTC.SGMA_PTC.Models.DTO.Users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El nombre de usuario no puede exceder los 50 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255, message = "La contraseña no puede exceder los 255 caracteres")
    private String passwordHash;
    @Size(max = 100, message = "El nombre completo no puede exceder los 100 caracteres")
    private String fullName;

    @NotNull(message = "El ID del rol es obligatorio")
    private Long roleId;
}
