package DevSGMA_PTC.SGMA_PTC.Services.VehicleType;

import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.VehicleTypeEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleType.VehicleTypeDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehicleType.VehicleTypeRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepo repo;


    public List<VehicleTypeDTO> getAllVehicleTypes() {
        List<VehicleTypeEntity> entities = repo.findAll();
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
//
//    public Page<VehicleTypeDTO> getAllVehicleTypes(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<VehicleTypeEntity> entities = repo.findAll(pageable);
//        return entities.map(this::convertToDTO);
//    }
//
//
//    public VehicleTypeDTO insert(@Valid VehicleTypeDTO dto) {
//        if (dto == null) {
//            throw new IllegalArgumentException("El tipo de vehículo no puede ser nulo");
//        }
//        VehicleTypeEntity entity = convertToEntity(dto);
//        VehicleTypeEntity saved = repo.save(entity);
//        return convertToDTO(saved);
//    }
//
//
//    public VehicleTypeDTO update(Long id, @Valid VehicleTypeDTO dto) {
//        if (dto == null) {
//            throw new IllegalArgumentException("El tipo de vehículo no puede ser nulo");
//        }
//
//        VehicleTypeEntity entity = repo.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Tipo de vehículo no encontrado"));
//
//        entity.setName(dto.getName());
//
//        VehicleTypeEntity updated = repo.save(entity);
//        return convertToDTO(updated);
//    }
//
//
//    public boolean delete(Long id) {
//        if (repo.existsById(id)) {
//            repo.deleteById(id);
//            return true;
//        }
//        return false;
//    }

    private VehicleTypeDTO convertToDTO(VehicleTypeEntity entity) {
        VehicleTypeDTO dto = new VehicleTypeDTO();
        dto.setTypeId(entity.getTypeId());
        dto.setTypeName(entity.getTypeName());
        return dto;
    }
//
//    private VehicleTypeEntity convertToEntity(@Valid VehicleTypeDTO dto) {
//        VehicleTypeEntity entity = new VehicleTypeEntity();
//        entity.setName(dto.getName());
//        return entity;
//    }
}
