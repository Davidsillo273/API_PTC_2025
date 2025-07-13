package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
    private Long vehicleId;
    private String plateNumber;
    private String brand;
    private String model;
    private Long typeId;
    private String typeName;
    private String color;
    private String circulationCardNumber;
    private byte[] vehicleImage;
}
