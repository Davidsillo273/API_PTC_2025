package DevSGMA_PTC.SGMA_PTC.Repositories.Instructors;

import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository <InstructorEntity, Long> {
    Page<InstructorEntity> findAll(Pageable pageable);
    Optional<StudentEntity> findbyFirstName(String firstName);
    boolean existsByFirstName(String firstName);
    boolean existsByEmail(String Email);
}
