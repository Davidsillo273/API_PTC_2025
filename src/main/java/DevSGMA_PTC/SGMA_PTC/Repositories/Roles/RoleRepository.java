package DevSGMA_PTC.SGMA_PTC.Repositories.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}