package DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleEntries;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModuleOperations.ModuleOperationDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleDTO;

import java.time.LocalDateTime;

public class VehicleEntryDTO {
    private Long id;
    private VehicleDTO vehicle;
    private LocalDateTime entryTime;
    private ModuleOperationDTO operation;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VehicleDTO getVehicle() { return vehicle; }
    public void setVehicle(VehicleDTO vehicle) { this.vehicle = vehicle; }

    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }

    public ModuleOperationDTO getOperation() { return operation; }
    public void setOperation(ModuleOperationDTO operation) { this.operation = operation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
