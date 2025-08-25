package DevSGMA_PTC.SGMA_PTC.Repositories.Roles;

import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
