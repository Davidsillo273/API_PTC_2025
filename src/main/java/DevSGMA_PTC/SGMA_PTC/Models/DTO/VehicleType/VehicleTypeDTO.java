package DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor


public class VehicleTypeDTO {
    private Long id;

    @NotBlank(message = "El nombre del tipo de vehículo es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String name;

}
