package DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.Vehicle;
import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperation;

@Entity
@Table(name = "tbVehicleEntries")
public class VehicleEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    @Column(name = "entryTime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp entryTime;

    @ManyToOne
    @JoinColumn(name = "operationId", nullable = false)
    private ModuleOperation operation;

    @Column(name = "status", length = 20)
    private String status; // Accepted, Rejected, Pending

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public java.sql.Timestamp getEntryTime() { return entryTime; }
    public void setEntryTime(java.sql.Timestamp entryTime) { this.entryTime = entryTime; }

    public ModuleOperation getOperation() { return operation; }
    public void setOperation(ModuleOperation operation) { this.operation = operation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
