package DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ModuleDTO {

    private Long id;

    @NotBlank(message = "Module name cannot be blank")
    private String moduleName;
}
