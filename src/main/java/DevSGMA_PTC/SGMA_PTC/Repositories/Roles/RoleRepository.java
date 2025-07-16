package DevSGMA_PTC.SGMA_PTC.Repositories.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Repositorio para la entidad Role. Hereda los métodos CRUD de JpaRepository.
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
//    Búsqueda personalizada por nombre de rol
//    Optional<RoleEntity> findByRoleName(String roleName);
}
