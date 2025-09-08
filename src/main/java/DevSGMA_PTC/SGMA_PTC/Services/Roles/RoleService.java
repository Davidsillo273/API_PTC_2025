package DevSGMA_PTC.SGMA_PTC.Services.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.VehicleTypeEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleType.VehicleTypeDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Roles.RoleRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehicleType.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repo;

    public List<RoleDTO> getAllVehicleTypes() {
        List<RoleEntity> entities = repo.findAll();
        System.out.println("ENTIDADES ENCONTRADAS: " + entities.size());
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RoleDTO convertToDTO(RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setRolId(entity.getRoleId());
        dto.setRolName(entity.getRoleName());
        return dto;
    }
}
