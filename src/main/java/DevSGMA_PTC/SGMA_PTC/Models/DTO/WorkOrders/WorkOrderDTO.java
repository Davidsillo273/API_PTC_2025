package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
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
public class WorkOrderDTO {

    @Positive
    private Number workOrderId;

    @Positive
    private VehicleEntity vehicleId;

    @NotBlank(message = "El año academico debe ser obligatorio  ")
    private String academicYear;

    @Positive
    private StudentEntity instructorId;

    @NotBlank(message = "El nombre del estudiante es obligatorio ")
    private String studentName;

    @Positive
    private String studentId;

    @NotBlank(message = "La descripción de la operación debe ser obligatoria ")
    @Size(min = 50, max = 500, message = "se debe constar almenos 50 carácteres en la descripcion pero con un límite de 500 caracteres")
    private String operationDescription;

    @Positive
    private ModuleEntity moduleId;

    @NotBlank(message = "El tipo de mantenimiento debe ser ingresado")
    private String maintenanceType;

    @NotBlank(message = "El tiempo estimado es obligatorio")
    private String estimatedTime;

    @NotBlank(message = "el tiempo de entrada es obligatorio")
    private String entryTime;

    @NotBlank(message = "el tiempo de salida es obligatorio")
    private String exitTime;

    @NotBlank(message = "debe de ingresarse el autor o dueño de determinao vehículo")
    @Size(min = 8, max = 50, message = "El nombre debe de tener un mínimo de 8 caracteres y no exceder el limite de 50 caracteres")
    private String ownerName;

    @NotBlank(message = "El DUI es requerido y obligatorio")
    private String OwnerDui;

    @NotBlank(message = "Debe proporiconar un numero telefonico")
    @Size(max = 9, message = "El numero telefonico debe contener 9 caracteres por defecto")
    private String ownerPhone;

    @NotBlank(message = "Es necesario justificar el estado del vehículo")
    private Long Status;
}
