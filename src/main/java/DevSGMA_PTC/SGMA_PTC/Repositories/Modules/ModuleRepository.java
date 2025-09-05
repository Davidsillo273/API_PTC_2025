package DevSGMA_PTC.SGMA_PTC.Repositories.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {

    // Método para obtener módulos paginados
    Page<ModuleEntity> findAll(Pageable pageable);

    boolean existsByModuleName(String moduleName);
}
