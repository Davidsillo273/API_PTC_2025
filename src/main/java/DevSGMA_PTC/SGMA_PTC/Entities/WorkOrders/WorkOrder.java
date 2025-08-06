package DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.Vehicle;
import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.Module;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@jakarta.persistence.Entity
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
    private String academicYear;

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private UserEntity instructor;

    @Column(name = "studentName", length = 100)
    private String studentName;

    @Column(name = "studentId", length = 8)
    private String studentId;

    @Column(name = "operationDescription", length = 255)
    private String operationDescription;

    @ManyToOne
    @JoinColumn(name = "moduleId", nullable = false)
    private Module module;

    @Column(name = "maintenanceType", length = 20, nullable = false)
    private String maintenanceType;

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

    @Column(name = "ownerPhone", length = 10)
    private String ownerPhone;

    @Column(name = "status", length = 20)
    private String status;
}