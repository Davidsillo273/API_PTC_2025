package DevSGMA_PTC.SGMA_PTC.Models.DTO.RolesDTO;


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
public class RolDTO {

    //*** ATRIBUTOS ***\\

    @Positive //El ID es positivo ya qué es generado por la base de datos.
    private Long rolId;

    //Validaciones para el nombre del rol
    @NotBlank(message = "El nombre del rol es obligatorio")
    // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(min = 5, message = "El nombre del rol debe tener al menos 5 caracteres")
    // Validación para el tamaño máximo del nombre del rol
    @Size(max = 50, message = "El nombre del rol no puede exceder los 50 caracteres")
    // Validación para el tamaño mínimo del nombre del rol
    private String rolName;
}
