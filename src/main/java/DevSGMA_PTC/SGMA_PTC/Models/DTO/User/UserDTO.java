package DevSGMA_PTC.SGMA_PTC.Models.DTO.User;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {

    @Positive
    private Long userId;

    @NotBlank(message = "Los nombres son obligatorio")
    @Size(max = 50, message = "Los nombres del usuario no pueden exceder los 50 caracteres")
    private String userName;

    @NotBlank(message = "Los apellidos son obligatorio")
    @Size(max = 50, message = "los apellidos del usuario no pueden exceder los 50 caracteres")
    private String lastName;

    //Se omite @Size ya qué Pattern valida el tamaño, en este caso exacto los 27 caracteres del correo institucional.
    @Pattern(
            regexp = "^[0-9]{8}@ricaldone\\.edu\\.sv$",
            message = "Debe ser un correo institucional válido"
    )
    @NotBlank
    private String instiEmail;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255, message = "La contraseña no puede exceder los 255 caracteres")
    private String password;

    @NotBlank(message = "El grado es obligatorio")
    @Size(max = 25, message = "El grado no puede exceder los 25 caracteres")
    private String grade;

//    @NotNull(message = "El ID del rol es obligatorio")
    @Positive(message = "El ID de cargo debe ser positivo")
    private Long roleId;

    //Este es un campo adiccional para mostrar el nombre del rol, campo como tal no existe en Users
    private String roleName;
}
