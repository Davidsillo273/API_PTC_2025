package DevSGMA_PTC.SGMA_PTC.Models.DTO.Entries;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    private Number entryId;

    // Validaciones para el tiempo de entrada
    @NotBlank(message = "El tiempo de entrada es obligatorio") // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    private Long entryTime;

    // Validaciones para el ID de la operación asociada
    @Positive // El ID de la operación debe ser positivo
    private Number operationId;

    // Validaciones para el estado del vehículo
    @NotBlank(message = "Es necesario justificar el estado del vehículo") // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(min = 50, max = 200, message = "La justificación debe tener entre 50 y 200 caracteres") // Validación para el tamaño mínimo y máximo de la justificación del estado
    private String status; // Justificación del estado del vehículo

    //*** CAMPOS ADICIONALES ***\\

    private String studentName;  // Campo adicional para mostrar el nombre del estudiante, campo como tal no existe en tbEntries
    private String plateNumber;  // Campo adicional para mostrar el número de placa, campo como tal no existe en tbEntries
    private String typeName;     // Campo adicional para mostrar el tipo de automóvil, campo como tal no existe en tbEntries
}
