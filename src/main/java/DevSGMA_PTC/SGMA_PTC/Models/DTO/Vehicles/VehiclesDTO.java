package DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleType.VehicleTypeDTO;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor

public class VehiclesDTO {
    private Long id;
    @NotBlank(message = "El número de placa es obligatorio")
    @Size(max = 10, message = "La placa no puede exceder 10 caracteres")
    @Pattern(regexp = "^[A-Z]{1,3}[0-9]{3,4}$",
            message = "Formato de placa inválido (ej: ABC1234)")
    private String plateNumber;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50, message = "La marca no puede exceder 50 caracteres")
    private String brand;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 50, message = "El modelo no puede exceder 50 caracteres")
    private String model;

    @NotNull(message = "El tipo de vehículo es obligatorio")
    private VehicleTypeDTO type;

    @Size(max = 30, message = "El color no puede exceder 30 caracteres")
    private String color;

    @NotBlank(message = "El número de tarjeta de circulación es obligatorio")
    @Size(max = 20, message = "El número de tarjeta no puede exceder 20 caracteres")
    private String circulationCardNumber;

}
