package DevSGMA_PTC.SGMA_PTC.Entities.WorksOrders;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbWorkOrder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workOrderId;

    @Column(nullable = false)
    private Long vehicleId;

    @Column(length = 20)
    private String academicYear;

    private Long instructorId;

    @Column(length = 100)
    private String studentName;

    @Column(length = 8)
    private String studentId;

    @Column(nullable = false)
    private Long operationId;

    @Column(nullable = false)
    private Long moduleId;

    @Column
    private String maintenanceType;

    @Column
    private String estimatedTime;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @Column
    private String ownerName;

    @Column
    private String ownerDui;

    @Column
    private String status;




    }





