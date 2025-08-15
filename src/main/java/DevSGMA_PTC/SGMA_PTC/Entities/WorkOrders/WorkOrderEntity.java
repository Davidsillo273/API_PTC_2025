package DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.Vehicle;
import DevSGMA_PTC.SGMA_PTC.Entities.Users.Usuario;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.Module;
import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbWorkOrders")
@Getter @Setter
@ToString @EqualsAndHashCode
public class WorkOrderEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workOrderId")
    private Long WorkOrderid;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Long vehicleId;

    @Column(name = "academicYear", length = 20, nullable = false)
    private String academicYear; // Puedes usar enum si quieres

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Long instructor;

    @Column(name = "studentName", length = 100)
    private String studentName;

    @Column(name = "studentId", length = 8)
    private String studentId;

    @ManyToOne
    @JoinColumn(name = "operationId", nullable = false)
    private Long operationDescription;

    @ManyToOne
    @JoinColumn(name = "moduleId", nullable = false)
    private Long moduleId;

    @Column(name = "maintenanceType", length = 20, nullable = false)
    private String maintenanceType; // Preventive or Corrective

    @Column(name = "estimatedTime", length = 50)
    private String estimatedTime;

    @Column(name = "entryTime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long entryTime;

    @Column(name = "exitTime")
    private Long exitTime;

    @Column(name = "ownerName", length = 100)
    private String ownerName;


    @Column(name = "ownerDui", length = 10)
    private String ownerDui;

    @Column(name = "ownerPhone", length = 9)
    private String ownerPhone;

    @Column(name = "status", length = 20)
    private String status; // Pending, Accepted, Completed


}
