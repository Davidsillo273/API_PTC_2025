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
    @Column(name = "vehicleId")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "plateNumber", length = 10, nullable = false, unique = true)
    private String plateNumber;

    @Column(name = "brand", length = 50)
    private String brand;

    @Column(name = "model", length = 50)
    private String model;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private VehicleTypeEntity type;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "circulationCardNumber", length = 50)
    private String circulationCardNumber;

    @Lob
    @Column(name = "vehicleImage")
    private byte[] vehicleImage;
}


