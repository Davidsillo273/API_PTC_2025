package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkOrderStatusDTO {

    @NotNull(message = "El idStatus es obligatorio")
    @Positive(message = "El idStatus debe ser un número positivo")
    private Long idStatus;

}

