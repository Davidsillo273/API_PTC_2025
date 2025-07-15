package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorksOrders;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderRequestDTO {

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
