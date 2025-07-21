package DevSGMA_PTC.SGMA_PTC.Entities.Vehicles;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.VehicleTypes.VehicleType;

@Entity
@Table(name = "tbVehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicleId")
    private Long id;

    @Column(name = "plateNumber", length = 10, nullable = false, unique = true)
    private String plateNumber;

    @Column(name = "brand", length = 50)
    private String brand;

    @Column(name = "model", length = 50)
    private String model;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private VehicleType type;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "circulationCardNumber", length = 50)
    private String circulationCardNumber;

    @Lob
    @Column(name = "vehicleImage")
    private byte[] vehicleImage;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public VehicleType getType() { return type; }
    public void setType(VehicleType type) { this.type = type; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getCirculationCardNumber() { return circulationCardNumber; }
    public void setCirculationCardNumber(String circulationCardNumber) { this.circulationCardNumber = circulationCardNumber; }

    public byte[] getVehicleImage() { return vehicleImage; }
    public void setVehicleImage(byte[] vehicleImage) { this.vehicleImage = vehicleImage; }
}
