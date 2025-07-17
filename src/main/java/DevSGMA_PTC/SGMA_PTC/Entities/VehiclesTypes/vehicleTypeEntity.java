package DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbVehicleTypes")
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class vehicleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(name = "typeName")
    private String typeName;

    
}
