package DevSGMA_PTC.SGMA_PTC.Repositories.Instructors;

import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository <InstructorEntity, Long> {
    Page<InstructorEntity> findAll(Pageable pageable);

    //Opcionales
    Optional<InstructorEntity> findbyFirstName(String firstName);
    Optional<InstructorEntity> findByEmail(String email);

    boolean existsByFirstName(String firstName);
    boolean existsByEmail(String email);
}
