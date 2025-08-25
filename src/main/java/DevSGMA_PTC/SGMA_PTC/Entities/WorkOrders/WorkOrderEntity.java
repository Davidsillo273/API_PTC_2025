package DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries.VehicleEntriesEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBWORKORDERS")
public class WorkOrderEntity {

    @Id
    @Column(name = "WORKORDERID")
    private Number workOrderId;


    @ManyToOne // Relación ManyToOne con VehicleEntity
    @JoinColumn(name = "VEHICLEID", referencedColumnName = "VEHICLEID") // Columna que conecta con la tabla de Vehículos
    private VehicleEntity vehicleId; // Relación ManyToOne con VehicleEntity

    @Column(name = "ACADEMICYEAR")
    private String academicYear;

    @ManyToOne // Relación ManyToOne con VehicleEntity
    @JoinColumn(name = "INSTRUCTORID", referencedColumnName = "USERID") // Columna que conecta con la tabla de Vehículos
    private UserEntity instructorId; // Relación ManyToOne con VehicleEntity

    @Column(name = "STUDENTNAME")
    private String studentName;

    @Column(name = "STUDENTID")
    private String studentId;

    @Column(name = "OPERATIONDESCRIPTION")
    private String operationDescription;

    @ManyToOne
    @JoinColumn(name = "MODULEID", referencedColumnName = "MODULEID") // Columna que conecta con la tabla de Modulos
    private ModuleEntity moduleId;

    @Column(name = "MAINTENANCETYPE")
    private String maintenanceType;

    @Column(name = "ESTIMATEDTIME")
    private String estimatedTime;

    @Column(name = "ENTRYTIME")
    @OneToMany
    private String entryTime;

    @Column(name = "EXITTIME")
    private String exitTime;

    @Column(name = "OWNERNAME")
    private String ownerName;

    @Column(name = "OWNERDUI")
    private String OwnerDui;

    @Column(name = "OWNERPHONE")
    private String ownerPhone;

    @Column(name = "STATUS")
    private long Status;
}