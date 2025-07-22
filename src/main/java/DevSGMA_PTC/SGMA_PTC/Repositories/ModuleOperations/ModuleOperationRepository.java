package DevSGMA_PTC.SGMA_PTC.Repositories.ModuleOperations;

import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleOperationRepository extends JpaRepository<ModuleOperation, Long> {
    boolean existsByNameAndModuleId(String name, Long moduleId);
}
