package DevSGMA_PTC.SGMA_PTC.Repositories.ModulesOperations;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.ModuleOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleOperationRepository extends JpaRepository<ModuleOperation, Long> {
}
