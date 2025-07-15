package DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@ToString
@EqualsAndHashCode
@Getter @Setter
public class RoleRequestDTO {

    private Long roleId;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 50, message = "El nombre del rol no puede exceder los 50 caracteres")
    private String roleName;
}
