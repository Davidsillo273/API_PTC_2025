package DevSGMA_PTC.SGMA_PTC.Entities.VehiclesEntrys;

//Importacion de librerias
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbVehicleEntries")
@Data //No es necesario colocar @To String, Ya que el Data lo sustituye
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntryEntity {

    //creamos y ponemos los atributos haciendo relacion a la base de datos del Proyecto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    @Column
    private String notes;

    }

