package DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;

import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBWORKORDERS")
public class WorkOrderEntity {

    @Id
    @Column(name = "WORKORDERID")
    private Number workOrderId;

    @ManyToOne // Relación ManyToOne con VehicleEntity
    @JoinColumn(name = "VEHICLEID", referencedColumnName = "VEHICLEID") // Columna que conecta con la tabla de Vehículos
    private Long vehicleId; // Relación ManyToOne con VehicleEntity
    private VehicleEntity vehicleId; // Relación ManyToOne con VehicleEntity

    @Column(name = "ACADEMICYEAR")
    private String academicYear;

    @ManyToOne // Relación ManyToOne con VehicleEntity
    @JoinColumn(name = "INSTRUCTORID", referencedColumnName = "USERID") // Columna que conecta con la tabla de Vehículos
    private StudentEntity instructorId; // Relación ManyToOne con VehicleEntity

    @Column(name = "STUDENTNAME")
    private String studentName;

    @Column(name = "STUDENTID")
    private String studentId;

    @Column(name = "OPERATIONDESCRIPTION")
    private String operationDescription;

    @ManyToOne
    @JoinColumn(name = "MODULEID", referencedColumnName = "MODULEID") // Columna que conecta con la tabla de Modulos
    private ModuleEntity moduleId;

    @Column(name = "MAINTENANCEEXPO")
    private String maintenanceExpo;

    @Column(name = "ESTIMATEDTIME")
    private String estimatedTime;

    @Column(name = "WORKORDERSIMAGE")
    private Long workOrdersImage;

    @Column(name = "STATUS")
    private Long status;
}