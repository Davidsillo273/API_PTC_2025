package DevSGMA_PTC.SGMA_PTC.Repositories.VehicleType;
import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Long> {
}
