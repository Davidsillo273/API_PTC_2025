package DevSGMA_PTC.SGMA_PTC.Repositories.ModuleOperations;

import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleOperationRepository extends JpaRepository<ModuleOperation, Long> {
    List<ModuleOperation> findByModuleId(Long moduleId);
}
