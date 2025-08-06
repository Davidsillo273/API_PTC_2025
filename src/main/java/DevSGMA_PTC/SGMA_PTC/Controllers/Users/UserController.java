package DevSGMA_PTC.SGMA_PTC.Controllers.Users;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Users.ExceptionUserDontInsert;
import DevSGMA_PTC.SGMA_PTC.Models.ApiResponse.ApiResponse;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import DevSGMA_PTC.SGMA_PTC.Services.Users.UserService;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/users") // Ruta base para todos los endpoints de esta clase
public class UserController {

    @Autowired // Inyección automática del servicio de usuarios
    private UserService userService;

    //*** MÉTODO PARA OBTENER TODOS LOS USUARIOS CON PAGINACIÓN ***\\

    /**
     * Obtiene todos los usuarios del sistema con paginación.
     *
     * @param page Número de página solicitada.
     * @param size Cantidad de registros por página.
     * @return ResponseEntity con un ApiResponse que contiene una página de usuarios (UserDTO).
     *         Retorna error si los parámetros son inválidos o si ocurre un problema en el servicio.
     */

    @GetMapping("/getDataUsers")
    public ResponseEntity<ApiResponse<Page<UserDTO>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page, // Página por defecto 0
            @RequestParam(defaultValue = "10") int size // Tamaño por defecto 10
    ) {
        // Validación del tamaño de página: debe estar entre 1 y 50
        if (size <= 0 || size > 50) {
            ResponseEntity.badRequest().body(Map.of(
                    "status", "La paginación de datos debe estar entre 1 y 50"
            ));
            return ResponseEntity.ok(null); // Devuelve nulo si la validación falla
        }

        // Obtiene los usuarios paginados desde el servicio
        Page<UserDTO> users = userService.getAllUsers(page, size);

        // Si ocurre un error al obtener los datos
        if (users == null) {
            ResponseEntity.badRequest().body(Map.of(
                    "status", "Error al obtener los datos"
            ));
        }

        // Retorna respuesta exitosa con los datos
        return ResponseEntity.ok(ApiResponse.success("Datos consultados correctamente", users));
    }

    //*** MÉTODO PARA AGREGAR UN NUEVO USUARIO ***\\
    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param json Objeto UserDTO con los datos del usuario a registrar.
     * @return ResponseEntity con un ApiResponse que contiene el usuario creado.
     *         Lanza excepción si el JSON es nulo o si falla el guardado.
     */

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO json) {
        // Verifica si el JSON recibido es nulo
        if (json == null) {
            throw new ExceptionUserDontInsert("Error al recibir y procesar la información del usuario");
        }

        // Intenta guardar el usuario usando el servicio
        UserDTO userSaved = userService.createUser(json);

        // Si el usuario no se guarda correctamente
        if (userSaved == null) {
            throw new ExceptionUserDontInsert("El usuario no pudo ser registrado debido a un problema en los datos");
        }

        // Retorna respuesta exitosa con el usuario guardado
        return ResponseEntity.ok(ApiResponse.success("Usuario registrado exitosamente", userSaved));
    }

    //*** MÉTODO PARA ACTUALIZAR UN USUARIO EXISTENTE ***\\
    /**
     * Actualiza los datos de un usuario existente según su ID.
     *
     * @param id ID del usuario a actualizar.
     * @param json Objeto UserDTO con los nuevos datos del usuario.
     * @param bindingResult Objeto que contiene errores de validación si los hay.
     * @return ResponseEntity con el usuario actualizado si la operación fue exitosa.
     *         Si hay errores de validación, devuelve un mapa con los errores.
     *         Si ocurre una excepción, devuelve un mensaje de error.
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable Long id, @RequestBody UserDTO json, BindingResult bindingResult) {
        // Si hay errores de validación en el DTO recibido
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            // Recorre los errores y los guarda en un mapa con nombre del campo y mensaje
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));

            // Retorna los errores al cliente
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Intenta actualizar el usuario con el ID proporcionado
            UserDTO usuarioActualizado = userService.updateUser(id, json);
            return ResponseEntity.ok(usuarioActualizado); // Respuesta exitosa
        } catch (Exception e) {
            // Si ocurre algún error durante la actualización
            return ResponseEntity.badRequest().body("Error al modificar el usuario");
        }
    }

    //*** MÉTODO PARA ELIMINAR UN USUARIO POR ID ***\\
    /**
     * Elimina un usuario del sistema según su ID.
     *
     * @param id ID del usuario que se desea eliminar.
     * @return ResponseEntity con mensaje de éxito si se elimina correctamente.
     *         Si no se encuentra el usuario, retorna un mensaje de error con estado 404.
     *         Si ocurre un error inesperado, retorna un mensaje de error con estado 500.
     */
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(
            @PathVariable Long id
    ){
        try{
            // Intenta eliminar el usuario usando el servicio
            if (!userService.deleteUser(id)){
                // Si el usuario no fue encontrado o no se pudo eliminar
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Error", "El usuario no encontrado")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Mensaje", "El usuario no fue encontrado",
                                "Fecha y hora", Instant.now().toString()
                        ));
            }

            // Si se elimina correctamente, devuelve mensaje de éxito
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso completado",
                    "mensaje", "Usuario eliminado exitosamente"
            ));
        }catch (Exception e){
            // Captura cualquier error inesperado durante la eliminación
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",  // Estado general
                    "message", "Error al eliminar el usuario",  // Mensaje para el usuario
                    "detail", e.getMessage()  // Detalles técnicos (para debugging)
            ));
        }
    }
}



    //*** MÉTODOS ANTIGUOS ***\\

//@GetMapping("/{id}")
//public ResponseEntity<UserEntity> getUsuarioById(@PathVariable Long id) {
//    Optional<UserEntity> usuario = userService.getUser(id);
//    return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//}


//@PatchMapping("{id}")
//public ResponseEntity<?> updateUser(
//        @Valid
//        @PathVariable Long id,
//        @RequestBody UserDTO json,
//        BindingResult bindingResult) {
//    Optional<UserEntity> optionalUsuario = userService.getUser(id);
//    if (optionalUsuario.isEmpty()) {
//        return ResponseEntity.notFound().build();
//    }
//    UserEntity usuario = optionalUsuario.get();
//
//    updates.forEach((key, value) -> {
//        switch (key) {
//            case "userName":
//                usuario.setUserName((String) value);
//                break;
//            case "lastName":
//                usuario.setLastName((String) value);
//                break;
//            case "instiEmail":
//                usuario.setInstiEmail((String) value);
//                break;
//            case "password":
//                usuario.setPassword((String) value);
//                break;
//            case "grade":
//                usuario.setGrade((String) value);
//                break;
//            case "roleId":
//                Long roleId = null;
//                if (value instanceof Integer) {
//                    roleId = ((Integer) value).longValue();
//                } else if (value instanceof Long) {
//                    roleId = (Long) value;
//                } else if (value instanceof String) {
//                    roleId = Long.parseLong((String) value);
//                }
//                if (roleId != null) {
//                    Optional<RoleEntity> roleOpt = roleService.getRoleById(roleId);
//                    roleOpt.ifPresent(usuario::setRoleId);
//                }
//                break;
//        }
//    });
//
//    UserEntity updatedUsuario = userService.createUsuario(usuario);
//    return ResponseEntity.ok(updatedUsuario);
//}


//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
//        boolean deleted = userService.deleteUsuario(id);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }


//
//@PutMapping("/{id}")
//public ResponseEntity<UserEntity> updateUsuario(@PathVariable Long id, @RequestBody UserEntity usuario) {
//    UserEntity updated = userService.updateUsuario(id, usuario);
//    if (updated == null) {
//        return ResponseEntity.notFound().build();
//    }
//    return ResponseEntity.ok(updated);
//}