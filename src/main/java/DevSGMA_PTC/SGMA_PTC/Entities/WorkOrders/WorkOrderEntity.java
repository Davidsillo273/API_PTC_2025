package DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
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