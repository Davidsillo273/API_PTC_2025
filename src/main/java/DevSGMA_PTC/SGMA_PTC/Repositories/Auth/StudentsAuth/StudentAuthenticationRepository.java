package DevSGMA_PTC.SGMA_PTC.Repositories.Auth.StudentsAuth;

import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAuthenticationRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByEmail(String email);
}
