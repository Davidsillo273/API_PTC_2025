
package DevSGMA_PTC.SGMA_PTC.Models.DTO.Users;

import jakarta.validation.constraints.*;
import lombok.*;

// Anotaciones de Lombok para generar getters, setters, toString, equals y hashCode automáticamente
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {

    //*** ATRIBUTOS ***\\

    @Positive  //El ID es positivo ya que es generado por la base de datos.
    private Long userId;
    //Validaciones para los nombres del usuario
    @NotBlank(message = "Los nombres son obligatorio")
    // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(min = 5, max = 50, message = "Los nombres del usuario no pueden exceder los 50 caracteres y deben tener al menos 5 caracteres")
    // Validación para el tamaño máximo del nombre
    // Validación para el tamaño mínimo del nombre
    private String userName;

    //Validaciones para los apellidos del usuario
    @NotBlank(message = "Los apellidos son obligatorio")
    // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(min = 5, max = 50, message = "los apellidos del usuario no pueden exceder los 50 caracteres y deben tener al menos 5 caracteres")
    // Validación para el tamaño máximo del apellido
    // Validación para el tamaño mínimo del apellido
    private String lastName;

    //Validaciones para el correo institucional del usuario
    @Pattern(
            regexp = "^[0-9]{8}@ricaldone\\.edu\\.sv$", // Expresión regular para validar el formato del correo institucional
            message = "Debe ser un correo institucional válido" // Mensaje de error si el formato no es correcto
    )
    @NotBlank // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    //Se omite @Size ya qué Pattern valida el tamaño, en este caso exacto los 27 caracteres del correo institucional.
    private String instiEmail;

    //Validaciones para la contraseña del usuario
    @NotBlank(message = "La contraseña es obligatoria")
    // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(min = 8, max = 255, message = "La contraseña no puede exceder los 255 caracteres y debe tener al menos 8 caracteres")
    // Validación para el tamaño mínimo de la contraseña
    // Validación para el tamaño máximo de la contraseña
    private String password;

    //Validaciones para el grado del usuario
    @NotBlank(message = "El grado es obligatorio")
    // Validación para que el campo no esté vacío o solo contenga espacios en blanco
    @Size(min = 5, max = 25, message = "El grado no puede exceder los 25 caracteres y debe tener al menos 5 caracteres")
    // Validación para el tamaño máximo del grado
    // Validación para el tamaño mínimo del grado
    private String grade;

    //Validaciones para el ID del rol del usuario
    @Positive(message = "El ID del rol debe ser positivo") // Validación para que el ID del rol sea positivo
    private Long roleId;

    private String imagenUrl; // URL de la imagen del usuario

    private String roleName;  // Campo adiccional para mostrar el nombre del rol, campo como tal no existe en Users
}
