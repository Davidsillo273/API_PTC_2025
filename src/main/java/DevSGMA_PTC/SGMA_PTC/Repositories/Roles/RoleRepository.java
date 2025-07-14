package DevSGMA_PTC.SGMA_PTC.Repositories.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;

@Repository
// Repositorio para la entidad Role. Hereda los métodos CRUD de JpaRepository.
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Búsqueda personalizada por nombre de rol
    Optional<Role> findByRoleName(String roleName);
}
