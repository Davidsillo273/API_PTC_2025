package DevSGMA_PTC.SGMA_PTC.Models.DTO.Users;

import jakarta.validation.constraints.*;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter @Setter
public class UserRequestDTO {

    private Long userId;

    @Email(message = "Debe ser una correo valido")
    private String email;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El nombre de usuario no puede exceder los 50 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255, message = "La contraseña no puede exceder los 255 caracteres")
    private String passwordHash;

    @Size(max = 100, message = "El nombre completo no puede exceder los 100 caracteres")
    private String fullName;

    @NotNull(message = "El ID del rol es obligatorio")
    @Positive(message = "El ID de cargo debe ser positivo")
    private Long roleId;
}
