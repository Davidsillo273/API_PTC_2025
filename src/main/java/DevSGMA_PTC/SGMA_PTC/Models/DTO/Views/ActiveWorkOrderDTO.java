package DevSGMA_PTC.SGMA_PTC.Models.DTO.Views;

import java.time.LocalDateTime;

public class ActiveWorkOrderDTO {
    private String academicYear;
    private Long workOrderId;
    private String vehicleFullName;
    private byte[] vehicleImage;
    private String performedTask;
    private LocalDateTime exitTime;

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public Long getWorkOrderId() { return workOrderId; }
    public void setWorkOrderId(Long workOrderId) { this.workOrderId = workOrderId; }

    public String getVehicleFullName() { return vehicleFullName; }
    public void setVehicleFullName(String vehicleFullName) { this.vehicleFullName = vehicleFullName; }

    public byte[] getVehicleImage() { return vehicleImage; }
    public void setVehicleImage(byte[] vehicleImage) { this.vehicleImage = vehicleImage; }

    public String getPerformedTask() { return performedTask; }
    public void setPerformedTask(String performedTask) { this.performedTask = performedTask; }

    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
}
