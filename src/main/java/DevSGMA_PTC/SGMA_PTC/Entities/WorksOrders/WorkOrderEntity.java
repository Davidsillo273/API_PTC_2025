package DevSGMA_PTC.SGMA_PTC.Entities.WorksOrders;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBWORKORDER")
@Getter
@Setter
public class WorkOrderEntity {


    private Long workOrderId;

    private Long vehicleId;

    private String academicYear;

    private Long instructorId;

    private String studentName;

    private String studentId;

    private Long operationId;

    private Long moduleId;

    private String maintenanceType;

    private String estimatedTime;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private String ownerName;

    private String ownerDui;

    private String status;




    }





