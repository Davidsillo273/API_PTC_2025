package DevSGMA_PTC.SGMA_PTC.Entities.Vehicles;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrder.WorkOrderEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBVEHICLES")
public class VehicleEntity {

    @Column(name = "VEHICLEID")
    private long vehicleId;

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

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "vehicleId", cascade = CascadeType.ALL) // Relación OneToMany con WorkOrderEntity
    private List<WorkOrderEntity> vehicle = new ArrayList<>(); // Relación OneToMany con WorkOrderEntity

}
