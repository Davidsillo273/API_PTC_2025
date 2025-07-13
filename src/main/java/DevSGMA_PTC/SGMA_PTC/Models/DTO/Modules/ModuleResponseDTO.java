package DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleResponseDTO {
    private Long moduleId;
    private String moduleName;
}
