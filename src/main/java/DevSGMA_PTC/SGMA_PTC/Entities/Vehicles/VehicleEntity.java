package DevSGMA_PTC.SGMA_PTC.Entities.Vehicles;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrder.WorkOrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBVEHICLES")
public class VehicleEntity {

    @Column(name = "VEHICLEID")
    @OneToMany
    private WorkOrderEntity vehicleId;

    @Column(name = "PLATENUMBER")
    private Long plateNumber;

    @Column(name = "BRAND")
    private Long brand;

    @Column(name = "MODEL")
    private Long model;

    @Column(name = "TYPEID")
    private Number typeId;

    @Column(name = "COLOR")
    private String   color;

    @Column(name = "CIRCULATIONCARDNUMBER")
    private String circulationCardNumber;

    @Column(name = "VEHICLEIMAGE")
    private String vehicleImage;

}
