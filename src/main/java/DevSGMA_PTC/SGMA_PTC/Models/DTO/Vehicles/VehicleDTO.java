package DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleTypes.VehicleTypeDTO;

public class VehicleDTO {
    private Long id;
    private String plateNumber;
    private String brand;
    private String model;
    private VehicleTypeDTO type;
    private String color;
    private String circulationCardNumber;
    private byte[] vehicleImage;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public VehicleTypeDTO getType() { return type; }
    public void setType(VehicleTypeDTO type) { this.type = type; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getCirculationCardNumber() { return circulationCardNumber; }
    public void setCirculationCardNumber(String circulationCardNumber) { this.circulationCardNumber = circulationCardNumber; }

    public byte[] getVehicleImage() { return vehicleImage; }
    public void setVehicleImage(byte[] vehicleImage) { this.vehicleImage = vehicleImage; }
}
