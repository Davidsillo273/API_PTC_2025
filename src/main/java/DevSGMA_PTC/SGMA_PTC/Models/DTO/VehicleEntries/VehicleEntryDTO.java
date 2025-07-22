package DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleEntries;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModuleOperations.ModuleOperationDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VehicleEntryDTO {
    private Long id;
    private VehicleDTO vehicle;
    private LocalDateTime entryTime;
    private ModuleOperationDTO operation;
    private String status;
}
