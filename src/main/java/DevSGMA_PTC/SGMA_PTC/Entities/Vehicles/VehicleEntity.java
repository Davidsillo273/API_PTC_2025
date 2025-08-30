package DevSGMA_PTC.SGMA_PTC.Entities.Vehicles;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.VehicleTypeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbVehicles")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VEHICLEID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "PLATENUMBER", length = 10, nullable = false, unique = true)
    private String plateNumber;

    @Column(name = "BRAND", length = 50)
    private String brand;

    @Column(name = "MODEL", length = 50)
    private String model;

    @ManyToOne
    @JoinColumn(name = "TYPEID")
    private VehicleTypeEntity type;

    @Column(name = "COLOR", length = 30)
    private String color;

    @Column(name = "CIRCULATIONCARDNUMBER", length = 50)
    private String circulationCardNumber;

    @Lob
    @Column(name = "VEHICLEIMAGE")
    private byte[] vehicleImage;
}


