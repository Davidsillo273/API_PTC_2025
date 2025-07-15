package DevSGMA_PTC.SGMA_PTC.Controller.Users;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Users.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Controlador que define las rutas HTTP para usuarios
@RestController
@RequestMapping("/apiUsers")// Ruta base: http://localhost:puerto/api/users
@CrossOrigin // Permite llamadas desde otro origen
public class UserController {

    @Autowired
    private UserService userService;

    //localhost:8080/apiUsuario/datosUsuarios
    @GetMapping("/DatosUsers")
    public List<UserRequestDTO> getAllData(){
        return userService.getAllUsers();

    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        // GET /api/users/1 para usuario con ID 1
//        return userService.getUserById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build()); // 404 si no existe
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userService.saveUser(user); // POST para guarda un nuevo usuario
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id); // DELETE /api/users/1 para eliminar usuario
//    }
}
