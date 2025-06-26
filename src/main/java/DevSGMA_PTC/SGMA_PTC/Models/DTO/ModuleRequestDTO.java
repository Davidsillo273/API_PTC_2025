package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleRequestDTO {
    @NotBlank(message = "El nombre del módulo es obligatorio")
    @Size(max = 50, message = "El nombre del módulo no puede exceder los 50 caracteres")
    private String moduleName;
}
