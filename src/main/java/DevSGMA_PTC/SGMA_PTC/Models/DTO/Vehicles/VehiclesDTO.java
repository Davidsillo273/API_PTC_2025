package DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// Anotaciones de Lombok para generar getters, setters y constructor sin argumentos automáticamente
@Getter
@Setter
@NoArgsConstructor
public class VehiclesDTO {
    //*** ATRIBUTOS ***\\

    @Positive
    private Long vehicleId; // Identificador único del vehículo, generado por la base de datos

    @NotBlank(message = "El número de placa es obligatorio") // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(max = 10, message = "La placa no puede exceder 10 caracteres") // Validación para el tamaño máximo de la placa
    @Pattern(regexp = "^[A-Z]{1,3}[0-9]{3,4}$", message = "Formato de placa inválido (ej: ABC1234)") // Validación para el formato de la placa
    private String plateNumber; // Número de placa del vehículo

    @NotBlank(message = "La marca es obligatoria") // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(max = 50, message = "La marca no puede exceder 50 caracteres") // Validación para el tamaño máximo de la marca
    private String brand; // Marca del vehículo

    @NotBlank(message = "El modelo es obligatorio") // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(max = 50, message = "El modelo no puede exceder 50 caracteres") // Validación para el tamaño máximo del modelo
    private String model; // Modelo del vehículo

    @NotNull(message = "El tipo de vehículo es obligatorio") // Validación para que el campo no sea nulo
    private String type; // Tipo de vehículo (ej: automóvil, motocicleta, etc.)

    @Size(max = 30, message = "El color no puede exceder 30 caracteres") // Validación para el tamaño máximo del color
    private String color; // Color del vehículo

    @NotBlank(message = "El número de tarjeta de circulación es obligatorio") // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(max = 20, message = "El número de tarjeta no puede exceder 20 caracteres") // Validación para el tamaño máximo de la tarjeta de circulación
    private String circulationCardNumber; // Número de tarjeta de circulación del vehículo
}
