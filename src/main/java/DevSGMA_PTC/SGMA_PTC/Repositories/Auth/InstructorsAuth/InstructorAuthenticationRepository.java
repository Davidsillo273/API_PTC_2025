package DevSGMA_PTC.SGMA_PTC.Repositories.Auth.InstructorsAuth;

import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorAuthenticationRepository extends JpaRepository<InstructorEntity, Long> {
    InstructorEntity findByEmail(String email);
}
