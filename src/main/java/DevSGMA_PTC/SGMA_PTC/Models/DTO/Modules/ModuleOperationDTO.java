package DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ModuleOperationDTO {

    private Long id;

    @NotNull(message = "Module ID cannot be null")
    private Long moduleId;

    @NotBlank(message = "Operation name cannot be blank")
    private String operationName;
}
