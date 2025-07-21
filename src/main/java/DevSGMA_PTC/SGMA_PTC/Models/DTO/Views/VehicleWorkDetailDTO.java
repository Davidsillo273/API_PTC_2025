package DevSGMA_PTC.SGMA_PTC.Models.DTO.Views;

public class VehicleWorkDetailDTO {
    private Long workOrderId;
    private String vehicleFullName;  // marca + modelo
    private String plateNumber;
    private String assignedStudent;
    private String moduleName;
    private String instructorFullName;
    private String taskName;

    public Long getWorkOrderId() { return workOrderId; }
    public void setWorkOrderId(Long workOrderId) { this.workOrderId = workOrderId; }

    public String getVehicleFullName() { return vehicleFullName; }
    public void setVehicleFullName(String vehicleFullName) { this.vehicleFullName = vehicleFullName; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getAssignedStudent() { return assignedStudent; }
    public void setAssignedStudent(String assignedStudent) { this.assignedStudent = assignedStudent; }

    public String getModuleName() { return moduleName; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }

    public String getInstructorFullName() { return instructorFullName; }
    public void setInstructorFullName(String instructorFullName) { this.instructorFullName = instructorFullName; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
}
