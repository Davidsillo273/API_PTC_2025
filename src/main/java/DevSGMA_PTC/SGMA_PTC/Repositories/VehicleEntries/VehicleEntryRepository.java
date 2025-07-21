package DevSGMA_PTC.SGMA_PTC.Repositories.VehicleEntries;

import DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries.VehicleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleEntryRepository extends JpaRepository<VehicleEntry, Long> {
    List<VehicleEntry> findByVehicleId(Long vehicleId);
}
