package DevSGMA_PTC.SGMA_PTC.Models.DTO.Grades;

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
public class GradeDTO {

    @Positive
    private Long gradeId; // Identificador único del grado

    @Positive
    private Long levelId; // Referencia al nivel académico asociado al grado

    @Positive
    private Long gradeGroup; // Número de grupo dentro del grado
}
