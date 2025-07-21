package DevSGMA_PTC.SGMA_PTC.Entities.VehicleTypes;

import jakarta.persistence.*;

@Entity
@Table(name = "tbVehicleTypes")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeId")
    private Long id;

    @Column(name = "typeName", length = 50, nullable = false)
    private String name;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
