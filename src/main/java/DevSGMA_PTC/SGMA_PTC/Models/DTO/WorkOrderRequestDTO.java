package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.WorkOrder.AcademicYear;
import DevSGMA_PTC.SGMA_PTC.Models.Entities.WorkOrder.MaintenanceType;
import DevSGMA_PTC.SGMA_PTC.Models.Entities.WorkOrder.WorkOrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderRequestDTO {

    @NotNull(message = "El ID del vehículo es obligatorio")
    private Long vehicleId;

    private AcademicYear academicYear;

    private Long instructorId;

    @Size(max = 100, message = "El nombre del estudiante no puede exceder los 100 caracteres")
    private String studentName;

    @Size(max = 8, message = "El ID del estudiante no puede exceder los 8 caracteres")
    private String studentId;

    @NotNull(message = "El ID de la operación es obligatorio")
    private Long operationId;

    @NotNull(message = "El ID del módulo es obligatorio")
    private Long moduleId;

    private MaintenanceType maintenanceType;

    @Size(max = 50, message = "El tiempo estimado no puede exceder los 50 caracteres")
    private String estimatedTime;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @Size(max = 100, message = "El nombre del propietario no puede exceder los 100 caracteres")
    private String ownerName;

    @Size(max = 10, message = "El DUI del propietario no puede exceder los 10 caracteres")
    private String ownerDui;

    private WorkOrderStatus status;
}
