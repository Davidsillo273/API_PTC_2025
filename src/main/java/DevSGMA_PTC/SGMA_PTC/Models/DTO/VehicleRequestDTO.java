package DevSGMA_PTC.SGMA_PTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {
    @NotBlank(message = "El número de placa es obligatorio")
    @Size(max = 10, message = "El número de placa no puede exceder los 10 caracteres")
    private String plateNumber;

    @Size(max = 50, message = "La marca no puede exceder los 50 caracteres")
    private String brand;

    @Size(max = 50, message = "El modelo no puede exceder los 50 caracteres")
    private String model;

    @NotNull(message = "El ID del tipo de vehículo es obligatorio")
    private Long typeId;

    @Size(max = 30, message = "El color no puede exceder los 30 caracteres")
    private String color;

    @Size(max = 50, message = "El número de la tarjeta de circulación no puede exceder los 50 caracteres")
    private String circulationCardNumber;

    private String vehicleImageBase64;
}
