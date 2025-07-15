package DevSGMA_PTC.SGMA_PTC.Repositories.WorksOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorksOrders.WorkOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderEntity, Long> {
    Optional<WorkOrderEntity> findByStatus(String findByStatus);

//    List<> findByStatus(WorkOrder.WorkOrderStatus status);
//    List<WorkOrder> findByAcademicYearAndStatus(WorkOrder.AcademicYear academicYear, WorkOrder.WorkOrderStatus status);
}
