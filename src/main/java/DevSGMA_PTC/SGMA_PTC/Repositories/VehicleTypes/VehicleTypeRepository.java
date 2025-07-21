package DevSGMA_PTC.SGMA_PTC.Repositories.VehicleTypes;

import DevSGMA_PTC.SGMA_PTC.Entities.VehicleTypes.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
