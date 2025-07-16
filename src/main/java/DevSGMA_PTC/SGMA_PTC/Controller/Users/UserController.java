package DevSGMA_PTC.SGMA_PTC.Controller.Users;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Controlador que define las rutas HTTP para usuarios
@RestController
@RequestMapping("/apiUsers")// Ruta base: http://localhost:puerto/api/users
@CrossOrigin // Permite llamadas desde otro origen
public class UserController {

    @Autowired
    UserService userService;

    //localhost:8080/apiUsers/dataUsers
    @GetMapping("/dataUsers")
    public List<UserRequestDTO> getAllData(){
        return userService.getAllFromUsers();

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
