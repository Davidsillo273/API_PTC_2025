package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.VehicleEntry.VehicleEntryStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntryResponseDTO {
    private Long entryId;
    private Long vehicleId;
    private String vehiclePlateNumber;
    private LocalDateTime entryTime;
    private Long operationId;
    private String operationName;
    private VehicleEntryStatus status;
}
