package DevSGMA_PTC.SGMA_PTC.Repositories.VehiclesTypes;


import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.vehicleTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VehicleTypeRepository extends JpaRepository<vehicleTypeEntity, Long> {


}
