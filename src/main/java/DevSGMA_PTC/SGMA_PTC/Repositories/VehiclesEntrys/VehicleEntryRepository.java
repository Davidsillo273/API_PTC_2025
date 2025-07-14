package DevSGMA_PTC.SGMA_PTC.Repositories.VehiclesEntrys;

import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesEntrys.VehicleEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleEntryRepository extends JpaRepository<VehicleEntryEntity, Long> {
    List<VehicleEntryEntity> findByLicensePlate(String licensePlate);
}