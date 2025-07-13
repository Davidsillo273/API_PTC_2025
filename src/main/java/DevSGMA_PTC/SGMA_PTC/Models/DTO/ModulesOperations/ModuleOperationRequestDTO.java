package DevSGMA_PTC.SGMA_PTC.Models.DTO.ModulesOperations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleOperationRequestDTO {
    @NotNull(message = "El ID del módulo es obligatorio")
    private Long moduleId;

    @NotBlank(message = "El nombre de la operación es obligatorio")
    @Size(max = 100, message = "El nombre de la operación no puede exceder los 100 caracteres")
    private String operationName;
}
