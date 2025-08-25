package DevSGMA_PTC.SGMA_PTC.Repositories.Vehicles;

import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepo extends JpaRepository<VehicleEntity, Long> {
    Optional<VehicleEntity> findByPlateNumber(String plateNumber);
    boolean existsByPlateNumber(String plateNumber);
}
