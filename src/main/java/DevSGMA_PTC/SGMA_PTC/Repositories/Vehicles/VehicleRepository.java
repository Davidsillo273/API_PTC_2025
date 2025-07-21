 package DevSGMA_PTC.SGMA_PTC.Repositories.Vehicles;

import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByPlateNumber(String plateNumber);
    boolean existsByPlateNumber(String plateNumber);
}
