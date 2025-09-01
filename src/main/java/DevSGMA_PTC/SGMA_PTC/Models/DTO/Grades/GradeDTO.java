package DevSGMA_PTC.SGMA_PTC.Models.DTO.Grades;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GradeDTO {

    private Long id;

    @NotBlank(message = "El nombre del grupo no puede estar vacío")
    private String groupName;

    @NotNull(message = "El nivel asociado es obligatorio")
    private Long levelId; // Referencia al LevelEntity
}
