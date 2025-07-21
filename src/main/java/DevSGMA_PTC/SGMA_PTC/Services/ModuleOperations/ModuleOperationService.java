package DevSGMA_PTC.SGMA_PTC.Services.ModuleOperations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperation;
import DevSGMA_PTC.SGMA_PTC.Repositories.ModuleOperations.ModuleOperationRepository;

@Service
public class ModuleOperationService {

    private final ModuleOperationRepository moduleOperationRepository;

    public ModuleOperationService(ModuleOperationRepository moduleOperationRepository) {
        this.moduleOperationRepository = moduleOperationRepository;
    }

    public List<ModuleOperation> getAllOperations() {
        return moduleOperationRepository.findAll();
    }

    public Optional<ModuleOperation> getOperationById(Long id) {
        return moduleOperationRepository.findById(id);
    }

    public ModuleOperation createOperation(ModuleOperation operation) {
        return moduleOperationRepository.save(operation);
    }

    public ModuleOperation updateOperation(Long id, ModuleOperation updatedOperation) {
        return moduleOperationRepository.findById(id)
                .map(op -> {
                    op.setName(updatedOperation.getName());
                    op.setRoute(updatedOperation.getRoute());
                    op.setModule(updatedOperation.getModule());
                    return moduleOperationRepository.save(op);
                }).orElse(null);
    }

    public boolean deleteOperation(Long id) {
        if(moduleOperationRepository.existsById(id)) {
            moduleOperationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
