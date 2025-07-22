package DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.Vehicle;
import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbVehicleEntries")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VehicleEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryId")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    @Column(name = "entryTime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp entryTime;

    @ManyToOne
    @JoinColumn(name = "operationId", nullable = false)
    private ModuleOperation operation;

    @Column(name = "status", length = 20)
    private String status; // Accepted, Rejected, Pending
}
