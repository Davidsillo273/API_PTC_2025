package DevSGMA_PTC.SGMA_PTC.Controller.Roles;


import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Roles.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

// Controlador REST que maneja las rutas relacionadas a roles
@RestController
@RequestMapping("/api/roles")
@CrossOrigin // Permite que otro origen haga solicitudes a esta API

public class RoleController {

    @Autowired
    private RoleService roleService;

    // GET /api/roles para listar los roles
    @GetMapping("/DatosRoles")
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
