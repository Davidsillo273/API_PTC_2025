package DevSGMA_PTC.SGMA_PTC.Services.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Roles.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Servicio que contiene la lógica de negocio para roles
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository; // Repositorio que accede a la base de datos

    // Devuelve todos los usuarios
    public List<RoleRequestDTO> getAllRoles() {
        List<RoleEntity> Role = roleRepository.findAll();
        return Role.stream()
                .map(this::convertToRoleDTO)
                .collect(Collectors.toList());
    }

    public RoleRequestDTO convertToRoleDTO(RoleEntity entity) {

        //Este será el obj a retornar
        RoleRequestDTO dto = new RoleRequestDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRole());
        return dto;
    }
}
