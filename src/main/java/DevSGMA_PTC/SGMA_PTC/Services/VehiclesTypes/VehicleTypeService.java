package DevSGMA_PTC.SGMA_PTC.Services.VehiclesTypes;

import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.vehicleTypeEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesTypes.VehicleTypeResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehiclesTypes.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository repo;

    public List<VehicleTypeResponseDTO> obtenerTodo() {

        List<vehicleTypeEntity>VehicleTypes = repo.findAll();
        return VehicleTypes.stream()
                .map(this::convertirVehicleTypesDTO)
                .collect(Collectors.toList());
    }

    public VehicleTypeResponseDTO convertirVehicleTypesDTO(vehicleTypeEntity entity){

        VehicleTypeResponseDTO dto = new VehicleTypeResponseDTO();
        dto.setTypeId(entity.getTypeId());
        dto.setTypeName(entity.getTypeName());
        return dto;
    }

    
}





