package DevSGMA_PTC.SGMA_PTC.Services.Roles;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import DevSGMA_PTC.SGMA_PTC.Repositories.Roles.RoleRepository;

@Service
public class RoleService {

//    private final RoleRepository roleRepository;
//
//    public RoleService(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    public List<RoleEntity> getAllRoles() {
//        return roleRepository.findAll();
//    }
//
//    public Optional<RoleEntity> getRoleById(Long id) {
//        return roleRepository.findById(id);
//    }
//
//    public RoleEntity createRole(RoleEntity role) {
//        return roleRepository.save(role);
//    }
//
//    public RoleEntity updateRole(Long id, RoleEntity updatedRole) {
//        return roleRepository.findById(id)
//                .map(role -> {
//                    role.setName(updatedRole.getName());
//                    return roleRepository.save(role);
//                }).orElse(null);
//    }
//
//    public boolean deleteRole(Long id) {
//        if(roleRepository.existsById(id)) {
//            roleRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
}
