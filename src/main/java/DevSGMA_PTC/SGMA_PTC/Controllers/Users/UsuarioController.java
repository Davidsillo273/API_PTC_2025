package DevSGMA_PTC.SGMA_PTC.Controllers.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.Usuario;
import DevSGMA_PTC.SGMA_PTC.Services.Users.UsuarioService;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.Role;
import DevSGMA_PTC.SGMA_PTC.Services.Roles.RoleService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RoleService roleService;

    public UsuarioController(UsuarioService usuarioService, RoleService roleService) {
        this.usuarioService = usuarioService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario updated = usuarioService.updateUsuario(id, usuario);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        boolean deleted = usuarioService.deleteUsuario(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> patchUsuario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Usuario> optionalUsuario = usuarioService.getUsuarioById(id);
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = optionalUsuario.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "username":
                    usuario.setUsername((String) value);
                    break;
                case "lastName":
                    usuario.setLastName((String) value);
                    break;
                case "instiEmail":
                    usuario.setInstiEmail((String) value);
                    break;
                case "password":
                    usuario.setPassword((String) value);
                    break;
                case "teacherGrade":
                    usuario.setTeacherGrade((String) value);
                    break;
                case "role":
                    Long roleId = null;
                    if (value instanceof Integer) {
                        roleId = ((Integer) value).longValue();
                    } else if (value instanceof Long) {
                        roleId = (Long) value;
                    } else if (value instanceof String) {
                        roleId = Long.parseLong((String) value);
                    }
                    if (roleId != null) {
                        Optional<Role> roleOpt = roleService.getRoleById(roleId);
                        roleOpt.ifPresent(usuario::setRole);
                    }
                    break;
            }
        });

        Usuario updatedUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
}
