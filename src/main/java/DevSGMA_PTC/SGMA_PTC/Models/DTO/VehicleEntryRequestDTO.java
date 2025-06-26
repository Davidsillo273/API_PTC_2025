package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.VehicleEntry.VehicleEntryStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntryRequestDTO {
    @NotNull(message = "El ID del vehículo es obligatorio")
    private Long vehicleId;

    private LocalDateTime entryTime;

    @NotNull(message = "El ID de la operación es obligatorio")
    private Long operationId;

    private VehicleEntryStatus status;
}
