package DevSGMA_PTC.SGMA_PTC.Repositories.ModuleOperations;

import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleOperationRepository extends JpaRepository<ModuleOperationEntity, Long> {
    Page<ModuleOperationEntity> findAll(Pageable pageable);
}
