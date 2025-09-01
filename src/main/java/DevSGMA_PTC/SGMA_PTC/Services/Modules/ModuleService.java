package DevSGMA_PTC.SGMA_PTC.Services.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Levels.ExceptionLevelNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleDontRegister;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Levels.LevelRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.Modules.ModuleRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
public class ModuleService {

    @Autowired
    private ModuleRepository repo;

    @Autowired
    private LevelRepository levelRepo;

    // Obtener módulos paginados
    public Page<ModuleDTO> getAllModules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ModuleEntity> pageResult = repo.findAll(pageable);
        return pageResult.map(this::convertToDTO);
    }


    // Insertar un módulo
    public ModuleDTO insert(@Valid ModuleDTO dto) {
        LevelEntity level = levelRepo.findById(dto.getLevelId())
                .orElseThrow(() -> new ExceptionLevelNotFound("Nivel no encontrado con ID: " + dto.getLevelId()));

        try {
            ModuleEntity entity = convertToEntity(dto, level);
            ModuleEntity saved = repo.save(entity);
            return convertToDTO(saved);
        } catch (Exception e) {
            log.error("Error al registrar el módulo: " + e.getMessage());
            throw new ExceptionModuleDontRegister("No se pudo registrar el módulo");
        }
    }

    // Actualizar un módulo
    public ModuleDTO update(Long id, @Valid ModuleDTO dto) {
        ModuleEntity module = repo.findById(id)
                .orElseThrow(() -> new ExceptionModuleNotFound("Módulo no encontrado con ID: " + id));

        LevelEntity level = levelRepo.findById(dto.getLevelId())
                .orElseThrow(() -> new ExceptionLevelNotFound("Nivel no encontrado con ID: " + dto.getLevelId()));

        module.setModuleName(dto.getModuleName());
        module.setLevel(level);

        try {
            ModuleEntity updated = repo.save(module);
            return convertToDTO(updated);
        } catch (Exception e) {
            log.error("Error al actualizar el módulo: " + e.getMessage());
            throw new ExceptionModuleDontRegister("No se pudo actualizar el módulo");
        }
    }

    // Eliminar un módulo
    public boolean delete(Long id) {
        ModuleEntity module = repo.findById(id).orElse(null);
        if (module != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    // Métodos privados de conversión
    private ModuleDTO convertToDTO(ModuleEntity entity) {
        ModuleDTO dto = new ModuleDTO();
        dto.setId(entity.getId());
        dto.setModuleName(entity.getModuleName());
        dto.setLevelId(entity.getLevel().getId());
        return dto;
    }

    private ModuleEntity convertToEntity(ModuleDTO dto, LevelEntity level) {
        ModuleEntity entity = new ModuleEntity();
        entity.setModuleName(dto.getModuleName());
        entity.setLevel(level);
        return entity;
    }
}
