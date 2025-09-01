package DevSGMA_PTC.SGMA_PTC.Repositories.Students;

import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<StudentEntity, Long> {
    Page<StudentEntity> findAll(Pageable pageable);
    Optional<StudentEntity> findbyFirstName(String firstName);
    boolean existsByFirstName(String firstName);
    boolean existsByEmail(String Email);
}
