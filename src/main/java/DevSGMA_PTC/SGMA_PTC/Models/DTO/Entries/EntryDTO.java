package DevSGMA_PTC.SGMA_PTC.Models.DTO.Entries;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Anotaciones de Lombok para generar getters, setters, toString, equals y hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EntryDTO {

    //*** ATRIBUTOS ***\\

    @Positive // El ID de la entrada debe ser positivo, generado por la base de datos
    private Long entryId;

    // Validaciones para el tiempo de entrada
    @NotBlank(message = "El tiempo de entrada es obligatorio")
    // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    private Long entryDate; // Fecha/hora de la entrada

    // Validaciones para el ID de la orden de trabajo asociada
    @Positive // El ID de la orden de trabajo debe ser positivo
    private Long WorkOrderId;

    //*** CAMPOS ADICIONALES ***\\
    private String plateNumber;  // Campo adicional para mostrar el número de placa, campo como tal no existe en tbEntries
}
