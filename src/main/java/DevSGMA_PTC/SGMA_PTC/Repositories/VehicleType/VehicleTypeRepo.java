package DevSGMA_PTC.SGMA_PTC.Repositories.VehicleType;
import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepo extends JpaRepository<VehicleTypeEntity, Long> {
}
