package DevSGMA_PTC.SGMA_PTC.Models.DTO.Views;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class VehicleWorkDetailDTO {
    private Long workOrderId;
    private String vehicleFullName;  // marca + modelo
    private String plateNumber;
    private String assignedStudent;
    private String moduleName;
    private String instructorFullName;
    private String taskName;
}
