package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModuleOperations.ModuleOperationDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode


public class WorkOrderDTO {
    private Long WorkOrderid;
    private Long vehicleId;
    private String academicYear;
    private Long instructor;
    private String studentName;
    private String studentId;
    private Long operationDescription;
    private Long moduleId;
    private String maintenanceType;
    private String estimatedTime;
    private Long entryTime;
    private Long exitTime;
    private String ownerName;
    private String ownerDui;
    private String ownerPhone;
    private String status;


}
