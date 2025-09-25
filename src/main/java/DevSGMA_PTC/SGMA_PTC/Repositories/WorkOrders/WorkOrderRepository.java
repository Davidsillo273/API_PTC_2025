package DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderEntity, Long > {
    Page<WorkOrderEntity> findAll(Pageable pageable);

    // Buscar órdenes por id de estudiante y estado
    List<WorkOrderEntity> findByVehicleId_StudentId_StudentIdAndStatus(Long studentId, Long status);
    //Opcionales
    //Optional<WorkOrderEntity> findByPlate(String plate);
}
