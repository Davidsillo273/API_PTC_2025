package DevSGMA_PTC.SGMA_PTC.Repositories;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.VehicleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleEntryRepository extends JpaRepository<VehicleEntry, Long> {
    List<VehicleEntry> findByVehicleVehicleId(Long vehicleId);
}
