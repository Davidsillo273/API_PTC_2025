package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders;

import jakarta.validation.constraints.FutureOrPresent;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WorkOrderDTO {


    @Positive
    private Number workOrderId;

    @Positive
    private VehicleEntity vehicleId;

    @NotBlank(message = "El año academico debe ser obligatorio  ")
    private String academicYear;

    @Positive
    private StudentEntity instructorId;


    @NotNull(message = "El ID de la orden de trabajo es obligatorio")
    @Positive(message = "El ID de la orden de trabajo debe ser positivo")
    private Number workOrderId;

    @NotNull(message = "El ID del vehículo es obligatorio")
    @Positive(message = "El ID del vehículo debe ser positivo")
    private Long vehicleId;

    @NotNull(message = "El ID del módulo es obligatorio")
    @Positive(message = "El ID del módulo debe ser positivo")
    private Long moduleId;

    @NotNull(message = "El valor de mantenimiento expo es obligatorio")
    @Positive(message = "El valor de mantenimiento expo debe ser positivo")
    private String maintenanceExpo;

    @FutureOrPresent(message = "El tiempo estimado debe ser en el presente o futuro")
    private Long estimatedTime;

    @NotNull(message = "La imagen de la orden de trabajo es obligatoria")
    private Long workOrdersImage;

    @NotNull(message = "El estado es obligatorio")
    private Long status;

}
