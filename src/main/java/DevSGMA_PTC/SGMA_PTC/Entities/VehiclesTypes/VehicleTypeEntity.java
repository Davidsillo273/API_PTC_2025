package DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

// Entity que representa un tipo de vehículo en la base de datos
@Entity
@Table(name = "TBVEHICLETYPES")
// Anotaciones de Lombok para generar getters, setters y equals/hashCode automáticamente
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VehicleTypeEntity {
    //*** ATRIBUTOS ***\\

    // ID del tipo de vehículo, clave primaria generada automáticamente
    @Id
    @Column(name = "TYPEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long typeId;

    // Nombre del tipo de vehículo, obligatorio y máximo 50 caracteres
    @Column(name = "TYPENAME", length = 50, nullable = false)
    private String typeName;
}
