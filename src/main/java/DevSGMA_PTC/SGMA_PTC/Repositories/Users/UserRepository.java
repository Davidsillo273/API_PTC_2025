package DevSGMA_PTC.SGMA_PTC.Repositories.Users;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// Repositorio para la entidad Role. Hereda los métodos CRUD de JpaRepository.
public interface UserRepository extends JpaRepository<User, Long> {
    // Búsqueda personalizada por nombre de User
    Optional<User> findByUsername(String username);
}
