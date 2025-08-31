package DevSGMA_PTC.SGMA_PTC.Repositories.Grades;

import DevSGMA_PTC.SGMA_PTC.Entities.Grades.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
    // Repository simple, no necesita paginación
}
