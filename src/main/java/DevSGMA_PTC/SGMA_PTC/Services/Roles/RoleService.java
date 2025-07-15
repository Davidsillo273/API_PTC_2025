package DevSGMA_PTC.SGMA_PTC.Services.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RolEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Roles.RoleRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Servicio que contiene la lógica de negocio para roles
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository; // Repositorio que accede a la base de datos

    // Devuelve todos los usuarios
    public List<RoleRequestDTO> getAllRoles() {
        List<RolEntity> Role = roleRepository.findAll();
        return Role.stream()
                .map(this::convertToRoleDTO)
                .collect(Collectors.toList());
    }

    public RoleRequestDTO convertToRoleDTO(RolEntity entity){

        //Este será el obj a retornar
        RoleRequestDTO dto = new RoleRequestDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        return dto;
    }
}
