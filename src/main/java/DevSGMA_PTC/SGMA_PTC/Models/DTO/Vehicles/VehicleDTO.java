package DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleTypes.VehicleTypeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class VehicleDTO {
    private Long id;
    private String plateNumber;
    private String brand;
    private String model;
    private VehicleTypeDTO type;
    private String color;
    private String circulationCardNumber;
    private byte[] vehicleImage;
}
