package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModuleOperations.ModuleOperationDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleDTO;

import java.time.LocalDateTime;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VehicleDTO getVehicle() { return vehicle; }
    public void setVehicle(VehicleDTO vehicle) { this.vehicle = vehicle; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public UserDTO getInstructor() { return instructor; }
    public void setInstructor(UserDTO instructor) { this.instructor = instructor; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public ModuleOperationDTO getOperation() { return operation; }
    public void setOperation(ModuleOperationDTO operation) { this.operation = operation; }

    public ModuleDTO getModule() { return module; }
    public void setModule(ModuleDTO module) { this.module = module; }

    public String getMaintenanceType() { return maintenanceType; }
    public void setMaintenanceType(String maintenanceType) { this.maintenanceType = maintenanceType; }

    public String getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(String estimatedTime) { this.estimatedTime = estimatedTime; }

    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }

    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerDui() { return ownerDui; }
    public void setOwnerDui(String ownerDui) { this.ownerDui = ownerDui; }

    public String getOwnerPhone() { return ownerPhone; }
    public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
