package DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

// Entity que representa un tipo de vehículo en la base de datos
@Entity
@Table(name = "TBVEHICLETYPES")
// Anotaciones de Lombok para generar getters, setters y equals/hashCode automáticamente
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VehicleTypeEntity {

    //*** ATRIBUTOS ***\\

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehicle_types")
    @SequenceGenerator(name = "seq_vehicle_types", sequenceName = "seq_vehicle_types", allocationSize = 1)
    @Column(name = "TYPEID")
    private Long typeId;

    // Nombre del tipo de vehículo, obligatorio y máximo 50 caracteres
    @Column(name = "TYPENAME", length = 50, nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "typeId", cascade = CascadeType.ALL) // Relación OneToMany con tbVehicles
    @JsonIgnore
    private List<VehicleEntity> vehicleType = new ArrayList<>();

}
