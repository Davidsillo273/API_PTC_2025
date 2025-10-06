package DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules;

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
public class ModuleDTO {

    //*** ATRIBUTOS ***\\

    @Positive // El ID del módulo debe ser positivo, generado por la base de datos
    private Long moduleId;

    // Validaciones para el nombre del módulo
    @NotBlank(message = "El nombre del módulo no puede estar vacío")
    // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(min = 5, message = "El nombre del módulo debe tener al menos 5 caracteres")
    // Validación para el tamaño máximo del nombre
    @Size(max = 50, message = "El nombre del módulo no puede exceder los 50 caracteres")
    // Validación para el tamaño mínimo del nombre
    private String moduleName;

    @Positive // El ID del nivel debe ser positivo
    private Long levelId; // Referencia al LevelEntity

    //Campo adicional
    private String levelName;  // Campo adicional para mostrar el nombre del año académico, campo como tal no existe en tbModules

    @NotBlank(message = "El código del módulo no puede estar vacío")
    @Size(max = 20, message = "El código del módulo no puede exceder los 20 caracteres")
    private String moduleCode;

    // Para POST: id del instructor
    private Long instructorId;

    // Para GET: detalles del instructor
    private InstructorInfoDTO instructor;

    // DTO auxiliar para mostrar datos clave del instructor
    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class InstructorInfoDTO {
        private Long instructorId;
        private String instructorName;
    }
}
