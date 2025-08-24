package DevSGMA_PTC.SGMA_PTC.Services.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleOperationEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleOperationNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleOperationNotRegister;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleOperationDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Modules.ModuleOperationRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@Service
@CrossOrigin
public class ModuleOperationService {

    @Autowired
    private ModuleOperationRepository repo;

    // Obtener todas las operaciones de módulos con paginación
    public Page<ModuleOperationDTO> getAllOperations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ModuleOperationEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::convertToDTO);
    }

    // Insertar nueva operación de módulo
    public ModuleOperationDTO insert(@Valid ModuleOperationDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("La información de la operación no puede ser nula");
        }
        try {
            ModuleOperationEntity entity = convertToEntity(dto);
            ModuleOperationEntity savedOperation = repo.save(entity);
            return convertToDTO(savedOperation);
        } catch (Exception e) {
            log.error("Error al registrar operación: " + e.getMessage());
            throw new ExceptionModuleOperationNotRegister("La operación no pudo ser registrada");
        }
    }

    // Actualizar operación existente
    public ModuleOperationDTO update(Long id, @Valid ModuleOperationDTO dto) {
        ModuleOperationEntity existingOperation = repo.findById(id)
                .orElseThrow(() -> new ExceptionModuleOperationNotFound("Operación no encontrada"));

        existingOperation.setModuleId(dto.getModuleId());
        existingOperation.setOperationName(dto.getOperationName());

        ModuleOperationEntity updatedOperation = repo.save(existingOperation);
        return convertToDTO(updatedOperation);
    }

    // Eliminar operación de módulo por ID
    public boolean delete(Long id) {
        ModuleOperationEntity existingOperation = repo.findById(id).orElse(null);
        if (existingOperation != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    // Convertir Entity a DTO
    private ModuleOperationDTO convertToDTO(ModuleOperationEntity entity) {
        ModuleOperationDTO dto = new ModuleOperationDTO();
        dto.setId(entity.getId());
        dto.setModuleId(entity.getModuleId());
        dto.setOperationName(entity.getOperationName());
        return dto;
    }

    // Convertir DTO a Entity
    private ModuleOperationEntity convertToEntity(@Valid ModuleOperationDTO dto) {
        ModuleOperationEntity entity = new ModuleOperationEntity();
        entity.setModuleId(dto.getModuleId());
        entity.setOperationName(dto.getOperationName());
        return entity;
    }
}
