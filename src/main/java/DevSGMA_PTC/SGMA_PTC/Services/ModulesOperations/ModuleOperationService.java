package DevSGMA_PTC.SGMA_PTC.Services.ModulesOperations;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.ModulesOperations.ModuleOperationEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModulesOperations.ModuleOperationRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModulesOperations.ModuleOperationResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Modules.ModuleRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.ModulesOperations.ModuleOperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModuleOperationService {

    private final ModuleOperationRepository operationRepository;
    private final ModuleRepository moduleRepository;

    public ModuleOperationService(ModuleOperationRepository operationRepository, ModuleRepository moduleRepository) {
        this.operationRepository = operationRepository;
        this.moduleRepository = moduleRepository;
    }

    public List<ModuleOperationResponseDTO> findAll() {
        return operationRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ModuleOperationResponseDTO> findById(Long id) {
        return operationRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public ModuleOperationResponseDTO save(ModuleOperationRequestDTO dto) {
        Optional<ModuleEntity> moduleOpt = moduleRepository.findById(dto.getModuleId());
        if (moduleOpt.isEmpty()) {
            throw new IllegalArgumentException("Módulo no encontrado para el ID: " + dto.getModuleId());
        }
        ModuleOperationEntity entity = fromRequestDTO(dto, moduleOpt.get());
        ModuleOperationEntity saved = operationRepository.save(entity);
        return toResponseDTO(saved);
    }

    public ModuleOperationResponseDTO update(Long id, ModuleOperationRequestDTO dto) {
        Optional<ModuleOperationEntity> optionalOp = operationRepository.findById(id);
        Optional<ModuleEntity> optionalModule = moduleRepository.findById(dto.getModuleId());
        if(optionalOp.isPresent() && optionalModule.isPresent()) {
            ModuleOperationEntity entity = optionalOp.get();
            entity.setModule(optionalModule.get());
            entity.setOperationName(dto.getOperationName());
            ModuleOperationEntity updated = operationRepository.save(entity);
            return toResponseDTO(updated);
        }
        return null;
    }

    public void delete(Long id) {
        operationRepository.deleteById(id);
    }

    private ModuleOperationEntity fromRequestDTO(ModuleOperationRequestDTO dto, ModuleEntity module) {
        ModuleOperationEntity entity = new ModuleOperationEntity();
        entity.setModule(module);
        entity.setOperationName(dto.getOperationName());
        return entity;
    }

    private ModuleOperationResponseDTO toResponseDTO(ModuleOperationEntity entity) {
        return new ModuleOperationResponseDTO(
                entity.getOperationId(),
                entity.getModule().getModuleId(),
                entity.getModule().getModuleName(),
                entity.getOperationName()
        );
    }
}
