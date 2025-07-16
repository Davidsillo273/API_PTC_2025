package DevSGMA_PTC.SGMA_PTC.Controller.Roles;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Roles.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controlador REST que maneja las rutas relacionadas a roles
@RestController
@RequestMapping("/apiRoles")
@CrossOrigin // Permite que otro origen haga solicitudes a esta API

public class RoleController {

    @Autowired
    RoleService roleService;

    // GET localhost:8080/apiRoles/dataRoles para listar los roles
    @GetMapping("/dataRoles")
    public List<RoleRequestDTO> getAllData() {
        return roleService.getAllRoles();
    }

//    // GET /api/roles/{id} para traer el rol
//    @GetMapping("/{id}")
//    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
//        return roleService.getRoleById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build()); // 404 si no existe
//    }
//
//    // POST /api/roles para crear un nuevo usuario
//    @PostMapping
//    public Role createRole(@RequestBody Role role) {
//        return roleService.saveRole(role);
//    }
//
//    // DELETE /api/roles/{id} para eliminar rol
//    @DeleteMapping("/{id}")
//    public void deleteRole(@PathVariable Long id) {
//        roleService.deleteRole(id);
//    }
}
