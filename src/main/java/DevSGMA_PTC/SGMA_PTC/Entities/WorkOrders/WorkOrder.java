package DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.Vehicle;
import DevSGMA_PTC.SGMA_PTC.Entities.Users.Usuario;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.Module;
import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperation;

@Entity
@Table(name = "tbWorkOrders")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workOrderId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    @Column(name = "academicYear", length = 20, nullable = false)
    private String academicYear; // Puedes usar enum si quieres

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Usuario instructor;

    @Column(name = "studentName", length = 100)
    private String studentName;

    @Column(name = "studentId", length = 8)
    private String studentId;

    @ManyToOne
    @JoinColumn(name = "operationId", nullable = false)
    private ModuleOperation operation;

    @ManyToOne
    @JoinColumn(name = "moduleId", nullable = false)
    private Module module;

    @Column(name = "maintenanceType", length = 20, nullable = false)
    private String maintenanceType; // Preventive or Corrective

    @Column(name = "estimatedTime", length = 50)
    private String estimatedTime;

    @Column(name = "entryTime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp entryTime;

    @Column(name = "exitTime")
    private java.sql.Timestamp exitTime;

    @Column(name = "ownerName", length = 100)
    private String ownerName;

    @Column(name = "ownerDui", length = 10)
    private String ownerDui;

    @Column(name = "status", length = 20)
    private String status; // Pending, Accepted, Completed

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public Usuario getInstructor() { return instructor; }
    public void setInstructor(Usuario instructor) { this.instructor = instructor; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public ModuleOperation getOperation() { return operation; }
    public void setOperation(ModuleOperation operation) { this.operation = operation; }

    public Module getModule() { return module; }
    public void setModule(Module module) { this.module = module; }

    public String getMaintenanceType() { return maintenanceType; }
    public void setMaintenanceType(String maintenanceType) { this.maintenanceType = maintenanceType; }

    public String getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(String estimatedTime) { this.estimatedTime = estimatedTime; }

    public java.sql.Timestamp getEntryTime() { return entryTime; }
    public void setEntryTime(java.sql.Timestamp entryTime) { this.entryTime = entryTime; }

    public java.sql.Timestamp getExitTime() { return exitTime; }
    public void setExitTime(java.sql.Timestamp exitTime) { this.exitTime = exitTime; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerDui() { return ownerDui; }
    public void setOwnerDui(String ownerDui) { this.ownerDui = ownerDui; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
