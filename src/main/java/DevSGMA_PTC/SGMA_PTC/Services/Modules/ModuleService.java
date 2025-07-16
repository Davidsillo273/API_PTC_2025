package DevSGMA_PTC.SGMA_PTC.Services.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Modules.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModuleService {

    private final ModuleRepository repository;

    public ModuleService(ModuleRepository repository) {
        this.repository = repository;
    }

    public List<ModuleResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ModuleResponseDTO> findById(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    public ModuleResponseDTO save(ModuleRequestDTO dto) {
        ModuleEntity entity = fromRequestDTO(dto);
        ModuleEntity saved = repository.save(entity);
        return toResponseDTO(saved);
    }

    public ModuleResponseDTO update(Long id, ModuleRequestDTO dto) {
        Optional<ModuleEntity> optional = repository.findById(id);
        if(optional.isPresent()) {
            ModuleEntity entity = optional.get();
            entity.setModuleName(dto.getModuleName());
            ModuleEntity updated = repository.save(entity);
            return toResponseDTO(updated);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ModuleEntity fromRequestDTO(ModuleRequestDTO dto) {
        ModuleEntity entity = new ModuleEntity();
        entity.setModuleName(dto.getModuleName());
        return entity;
    }

    private ModuleResponseDTO toResponseDTO(ModuleEntity entity) {
        return new ModuleResponseDTO(entity.getModuleId(), entity.getModuleName());
    }
}
