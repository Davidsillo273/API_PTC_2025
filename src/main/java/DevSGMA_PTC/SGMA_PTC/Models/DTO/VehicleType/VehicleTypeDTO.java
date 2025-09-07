package DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeDTO {

    @Positive
    private long typeId;
    @NotBlank(message = "El nombre del del tipo de vehiculo es obligatorio")
    private String typeName;
}
