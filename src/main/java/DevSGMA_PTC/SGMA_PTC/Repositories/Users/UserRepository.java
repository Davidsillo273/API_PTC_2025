package DevSGMA_PTC.SGMA_PTC.Repositories.Users;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Repositorio para la entidad Role. Hereda los métodos CRUD de JpaRepository.
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//     Búsqueda personalizada por nombre de User
//    Optional<UserEntity> findByUsername(String username);
}
