package DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleEntries;


import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrder.WorkOrderEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehicleEntriesDTO {

    @Positive
    private Number entryId;

    @NotBlank(message = "el tiempo de entrada es obligatorio")
    private Long entryTime;

    @Positive
    private Number operationId;

    @NotBlank(message = "Es necesario justificar el estado del vehículo")
    @Size(min = 50, max = 200, message = "como mínimo")
    private WorkOrderEntity Status;
}
