package DevSGMA_PTC.SGMA_PTC.Entities.VehicleTypes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbVehicleTypes")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeId")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "typeName", length = 50, nullable = false)
    private String name;
}
