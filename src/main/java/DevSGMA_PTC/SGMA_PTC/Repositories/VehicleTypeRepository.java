package DevSGMA_PTC.SGMA_PTC.Repositories;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
