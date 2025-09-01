package DevSGMA_PTC.SGMA_PTC.Repositories.Levels;

import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Long> {
    // Repository simple, no necesita paginación
}
