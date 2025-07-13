package DevSGMA_PTC.SGMA_PTC.Repositories.Modules;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
