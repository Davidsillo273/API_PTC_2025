package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.WorkOrder.AcademicYear;
import DevSGMA_PTC.SGMA_PTC.Models.Entities.WorkOrder.MaintenanceType;
import DevSGMA_PTC.SGMA_PTC.Models.Entities.WorkOrder.WorkOrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderResponseDTO {
    private Long workOrderId;
    private Long vehicleId;
    private String vehiclePlateNumber;
    private String vehicleBrandModel;
    private AcademicYear academicYear;
    private Long instructorId;
    private String instructorFullName;
    private String studentName;
    private String studentId;
    private Long operationId;
    private String operationName;
    private Long moduleId;
    private String moduleName;
    private MaintenanceType maintenanceType;
    private String estimatedTime;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String ownerName;
    private String ownerDui;
    private WorkOrderStatus status;
}
