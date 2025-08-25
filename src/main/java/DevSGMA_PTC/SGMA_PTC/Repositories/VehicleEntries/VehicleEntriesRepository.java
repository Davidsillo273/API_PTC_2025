package DevSGMA_PTC.SGMA_PTC.Repositories.VehicleEntries;

import DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries.VehicleEntriesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleEntriesRepository extends JpaRepository<VehicleEntriesEntity, Long> {
    Page<VehicleEntriesEntity> findAll(Pageable pageable);
}
