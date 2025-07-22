package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModuleOperations.ModuleOperationDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleDTO;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@EqualsAndHashCode
public class WorkOrderDTO {
    private Long id;
    private VehicleDTO vehicle;
    private String academicYear;
    private UserDTO instructor;
    private String studentName;
    private String studentId;
    private ModuleOperationDTO operation;
    private ModuleDTO module;
    private String maintenanceType;
    private String estimatedTime;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String ownerName;
    private String ownerDui;
    private String ownerPhone;
    private String status;
}
