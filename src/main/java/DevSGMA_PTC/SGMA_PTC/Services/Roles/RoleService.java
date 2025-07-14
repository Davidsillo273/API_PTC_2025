package DevSGMA_PTC.SGMA_PTC.Services.Roles;

import DevSGMA_PTC.SGMA_PTC.Repositories.Roles.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

// Servicio que contiene la lógica de negocio para roles
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository; // Acceso a la base de datos para roles

    // Devuelve todos los roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Devuelve un rol específico por ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Guarda o actualiza un rol
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Elimina un rol por ID
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
