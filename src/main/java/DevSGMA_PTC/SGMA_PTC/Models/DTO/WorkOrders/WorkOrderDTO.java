package DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

// Anotaciones de Lombok para generar getters, setters, toString y equals/hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WorkOrderDTO {

    //*** ATRIBUTOS ***\\

    @Positive(message = "El ID de la orden de trabajo debe ser positivo") // El ID es generado por la base de datos y debe ser positivo
    private Number workOrderId;

    @Positive(message = "El ID del vehículo debe ser positivo") // Referencia al vehículo asociado a la orden de trabajo
    private Long vehicleId;

    @Positive(message = "El ID del módulo debe ser positivo") // Referencia al módulo académico relacionado con la orden
    private Long moduleId;

    @NotNull(message = "El valor de mantenimiento expo es obligatorio") // El valor de mantenimiento expo no puede ser nulo
    @Positive(message = "El valor de mantenimiento expo debe ser positivo") // El valor debe ser positivo
    private Long maintenanceExpo;

    @FutureOrPresent(message = "El tiempo estimado debe ser en el presente o futuro") // El tiempo estimado para la orden debe ser actual o futuro
    private Long estimatedTime;

    @NotNull(message = "La imagen de la orden de trabajo es obligatoria") // La imagen de la orden es obligatoria (puede ser URL o base64)
    private String workOrdersImage;

    @NotNull(message = "El estado es obligatorio") // El estado de la orden de trabajo no puede ser nulo
    private Long status;
}
