package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeResponseDTO {
    private Long typeId;
    private String typeName;
}
