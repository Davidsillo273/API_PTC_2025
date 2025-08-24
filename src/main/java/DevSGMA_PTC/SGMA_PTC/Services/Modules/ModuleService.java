package DevSGMA_PTC.SGMA_PTC.Services.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNotRegister;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Modules.ModuleRepository;
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
public class ModuleService {

    @Autowired
    private ModuleRepository repo;

    // Obtener todos los módulos con paginación
    public Page<ModuleDTO> getAllModules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ModuleEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::convertToDTO);
    }

    // Insertar un nuevo módulo
    public ModuleDTO insert(@Valid ModuleDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("La información del módulo no puede ser nula");
        }
        try {
            ModuleEntity entity = convertToEntity(dto);
            ModuleEntity savedModule = repo.save(entity);
            return convertToDTO(savedModule);
        } catch (Exception e) {
            log.error("Error al registrar módulo: " + e.getMessage());
            throw new ExceptionModuleNotRegister("El módulo no pudo ser registrado");
        }
    }

    // Actualizar un módulo existente
    public ModuleDTO update(Long id, @Valid ModuleDTO dto) {
        // Verificar existencia del módulo
        ModuleEntity existingModule = repo.findById(id)
                .orElseThrow(() -> new ExceptionModuleNotFound("Módulo no encontrado"));

        // Actualizar campos
        existingModule.setModuleName(dto.getModuleName());

        // Guardar cambios
        ModuleEntity updatedModule = repo.save(existingModule);
        return convertToDTO(updatedModule);
    }

    // Eliminar un módulo por ID
    public boolean delete(Long id) {
        ModuleEntity existingModule = repo.findById(id).orElse(null);
        if (existingModule != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    // Convertir Entity a DTO
    private ModuleDTO convertToDTO(ModuleEntity entity) {
        ModuleDTO dto = new ModuleDTO();
        dto.setId(entity.getId());
        dto.setModuleName(entity.getModuleName());
        return dto;
    }

    // Convertir DTO a Entity
    private ModuleEntity convertToEntity(@Valid ModuleDTO dto) {
        ModuleEntity entity = new ModuleEntity();
        entity.setModuleName(dto.getModuleName());
        return entity;
    }
}
