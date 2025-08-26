package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WorkOrderDTO {

    @Positive
    private Number workOrderId;

    @Positive
    private VehicleEntity vehicleId;

    @Positive
    private ModuleEntity moduleId;
    
    @NotBlank(message = "El tipo de mantenimiento debe ser ingresado")
    private String maintenanceExpo;

    @NotBlank(message = "El tiempo estimado es obligatorio")
    private String estimatedTime;

    @NotBlank(message = "Es necesario justificar el estado del vehículo")
    private Long Status;
}
