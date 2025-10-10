package DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Getter // Lombok: genera automáticamente los métodos getter
@Setter // Lombok: genera automáticamente los métodos setter
@EqualsAndHashCode // Lombok: genera automáticamente equals y hashCode
@Table(name = "TBVECHILESTYPES") // Especifica el nombre de la tabla en la base de datos
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

    @OneToMany(mappedBy = "typeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación OneToMany con tbVehicles
    @JsonIgnore
    private List<VehicleEntity> vehicleType = new ArrayList<>();

    @Override
    public String toString() {
        return "VehicleTypeEntity{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
