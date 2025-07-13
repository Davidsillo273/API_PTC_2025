package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleOperationResponseDTO {
    private Long operationId;
    private Long moduleId;
    private String moduleName;
    private String operationName;
}
